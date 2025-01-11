package pillihuaman.com.pe.basebd.common;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import pillihuaman.com.pe.basebd.product.Stock;
import pillihuaman.com.pe.basebd.help.AuditEntity;

import java.io.Serializable;
import java.util.Date;

public class ProductStock implements Serializable {
    private static final long serialVersionUID = 1L;
    @BsonId
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId _id;
    @BsonId
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId idProduct;
    private Date expirationDate;
    private Date creationDate;
    private int state;

    private Stock stock;
    private AuditEntity auditEntity;


    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

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

    public AuditEntity getAuditEntity() {
        return auditEntity;
    }

    public void setAuditEntity(AuditEntity auditEntity) {
        this.auditEntity = auditEntity;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
