import "errors"

// File is a collection of bytes representing a file.
type File []byte

type Rsync struct {
	files   []File
	targets []*Rsync
}

func (r *Rsync) AddTarget(t *Rsync) {
	r.targets = append(r.targets, t)
}

func (r *Rsync) AddFile(file File) int {
	r.files = append(r.files, file)
	for _, t := range r.targets {
		t.sendAddFile(file)
	}
	return len(r.files) - 1
}

func (r *Rsync) UpdateFile(id int, file File) error {
	if id < 0 || id >= len(r.files) {
		return errors.New("invalid file id")
	}
	// update and send diff
	for _, t := range r.targets {
		t.sendUpdateFile(id, file)
	}
	return nil
}

func (r *Rsync) DeleteFile(id int) error {
	if id < 0 || id >= len(r.files) {
		return errors.New("invalid file id")
	}
	r.files[id] = nil
	for _, t := range r.targets {
		t.sendDeleteFile(id)
	}
	return nil
}

func (r *Rsync) GetFile(id int) File {
	if id < 0 || id >= len(r.files) {
		return nil
	}
	return r.files[id]
}

// rpc calls

func (r *Rsync) sendAddFile(f File) {
	r.AddFile(f)
}

func (r *Rsync) sendDeleteFile(id int) {
	_ = r.DeleteFile(id)
}

func (r *Rsync) sendUpdateFile(id int, file File) {
	// TODO: implement the incremental or changes update
	for i, b := range file {
		if i >= len(r.files[id]) {
			r.files[id] = append(r.files[id], b)
		}
		if r.files[id][i] != b {
			r.sendByte(id, i, b)
		}
	}
	r.files[id] = r.files[id][:len(file)]
}

func (r *Rsync) sendByte(id, index int, b byte) {
	r.files[id][index] = b
}