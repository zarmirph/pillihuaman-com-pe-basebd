package pillihuaman.com.pe.basebd.product;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;
    @BsonId
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId _id;
    private List<Size> size;
    //private List<Color> color;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }


    public List<Size> getSize() {
        return size;
    }
    public void setSize(List<Size> size) {
        this.size = size;
    }
}