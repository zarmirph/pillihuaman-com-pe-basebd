package pillihuaman.com.pe.basebd.imagen;

import java.io.Serializable;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import pillihuaman.com.pe.basebd.help.AuditEntity;

@Component
public class Imagen implements Serializable {
	private static final long serialVersionUID = 1L;
	@BsonProperty(value = "_id")
	private ObjectId id;
	private String description;
	private String name;
	private int idUser;

	private int idHeadImagen;

	private int idSystem;
	private String idProduct;
	private String uniqueKeyHash;
	private  int countRanking;
	private int clickCount;

	private String idDetail;
	private int  index;

	public String getIdDetail() {
		return idDetail;
	}

	public void setIdDetail(String idDetail) {
		this.idDetail = idDetail;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	private AuditEntity auditEntity;

	public int getCountRanking() {
		return countRanking;
	}

	public void setCountRanking(int countRanking) {
		this.countRanking = countRanking;
	}

	public int getClickCount() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public String getUniqueKeyHash() {
		return uniqueKeyHash;
	}

	public void setUniqueKeyHash(String uniqueKeyHash) {
		this.uniqueKeyHash = uniqueKeyHash;
	}

	public String getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdHeadImagen() {
		return idHeadImagen;
	}

	public void setIdHeadImagen(int idHeadImagen) {
		this.idHeadImagen = idHeadImagen;
	}

	public int getIdSystem() {
		return idSystem;
	}

	public void setIdSystem(int idSystem) {
		this.idSystem = idSystem;
	}

	public AuditEntity getAuditEntity() {
		return auditEntity;
	}

	public void setAuditEntity(AuditEntity auditEntity) {
		this.auditEntity = auditEntity;
	}

	public Imagen() {
	}

}