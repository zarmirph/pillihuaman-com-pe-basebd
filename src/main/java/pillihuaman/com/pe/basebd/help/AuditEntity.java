package pillihuaman.com.pe.basebd.help;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;

public class AuditEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @BsonProperty(value = "_id")
    private ObjectId id;
    private ObjectId codUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSXXX")
    private Date dateRegister;

    private ObjectId codUserUpdate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSXXX")
    private Date dateUpdate;
    private String mail;
    private String mailUpdate;
    public String getMail() {
        return mail;
    }

    public String getMailUpdate() {
        return mailUpdate;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setMailUpdate(String mailUpdate) {
        this.mailUpdate = mailUpdate;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ObjectId getCodUser() {
        return codUser;
    }

    public void setCodUser(ObjectId codUser) {
        this.codUser = codUser;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public ObjectId getCodUserUpdate() {
        return codUserUpdate;
    }

    public void setCodUserUpdate(ObjectId codUserUpdate) {
        this.codUserUpdate = codUserUpdate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public AuditEntity() {
    }

    public static class Builder {
        private AuditEntity instance;

        public Builder() {
            instance = new AuditEntity();
        }
        public Builder codUser(ObjectId codUser) {
            instance.codUser = codUser;
            return this;
        }
        public Builder dateRegister(Date dateRegister) {
            instance.dateRegister = dateRegister;
            return this;
        }
        public Builder codUserUpdate(ObjectId codUserUpdate) {
            instance.codUserUpdate = codUserUpdate;
            return this;
        }
        public Builder dateUpdate(Date dateUpdate) {
            instance.dateUpdate = dateUpdate;
            return this;
        }

        public Builder mail(String mail) {
            instance.mail = mail;
            return this;
        }
        public Builder mailUpdate(String mailUpdate) {
            instance.mailUpdate = mailUpdate;
            return this;
        }
        public AuditEntity build() {
            return instance;
        }
    }

}
