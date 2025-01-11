package pillihuaman.com.pe.basebd.product;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import pillihuaman.com.pe.lib.request.ImagenDetail;

import java.io.Serializable;
import java.util.List;

public class Color implements Serializable {
        private static final long serialVersionUID = 1L;
        @BsonId
        @JsonSerialize(using = ToStringSerializer.class)
        private ObjectId _id;
        private ObjectId idProduct;

        
        private List<ImagenDetail> listImagen;
        public ObjectId get_id() {
                return _id;
        }

        public void set_id(ObjectId _id) {
                this._id = _id;
        }

        public ObjectId getIdProduct() {
                return idProduct;
        }

        public void setIdProduct(ObjectId idProduct) {
                this.idProduct = idProduct;
        }

    public List<ImagenDetail> getListImagen() {
        return listImagen;
    }

    public void setListImagen(List<ImagenDetail> listImagen) {
        this.listImagen = listImagen;
    }
}
