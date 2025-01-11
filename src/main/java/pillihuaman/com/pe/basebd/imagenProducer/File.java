package pillihuaman.com.pe.basebd.imagenProducer;

import org.bson.types.ObjectId;

public class File {
    private ObjectId _id;
    private ObjectId files_id;
    private byte[] data;
    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public ObjectId getFiles_id() {
        return files_id;
    }

    public void setFiles_id(ObjectId files_id) {
        this.files_id = files_id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


}
