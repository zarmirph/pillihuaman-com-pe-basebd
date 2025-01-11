package pillihuaman.com.pe.basebd.imagenTemp;

import java.io.Serializable;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import pillihuaman.com.pe.basebd.help.AuditEntity;

@Component
public class ImagenTemp implements Serializable {
	private static final long serialVersionUID = 1L;
	@BsonProperty(value = "_id")
	private ObjectId id;
	@BsonProperty(value = "id_temp")
	private String idTemp;

	private AuditEntity auditEntity;

	@BsonProperty(value = "files")
	private byte[] files;

	@BsonProperty(value = "name")
	private String name;
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public byte[] getFiles() {
		return files;
	}



	public void setFiles(byte[] files) {
		this.files = files;
	}



	public ObjectId getId() {
		return id;
	}



	public void setId(ObjectId id) {
		this.id = id;
	}



	public String getIdTemp() {
		return idTemp;
	}



	public void setIdTemp(String idTemp) {
		this.idTemp = idTemp;
	}



	public AuditEntity getAuditEntity() {
		return auditEntity;
	}



	public void setAuditEntity(AuditEntity auditEntity) {
		this.auditEntity = auditEntity;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public ImagenTemp() {
	}

}