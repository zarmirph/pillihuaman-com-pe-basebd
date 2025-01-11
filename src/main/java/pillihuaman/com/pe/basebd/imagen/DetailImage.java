package pillihuaman.com.pe.basebd.imagen;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class DetailImage implements Serializable {
	private static final long serialVersionUID = 1L;
	@BsonProperty(value = "_id")
	private ObjectId id;
	private int idHeadImagen;

	private int idImagen;
	@BsonProperty(value = "name")
	private String name;
	private int countRanking;

	private int clickCount;

	private int idType;

	@BsonProperty(value = "files")
	private byte[] files;


	private ObjectId idDetail;

	private String uniqueKeyHash;
	@BsonProperty(value = "_object_id")
	private ObjectId _objectId;

	private  int index;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public int getIdHeadImagen() {
		return idHeadImagen;
	}

	public void setIdHeadImagen(int idHeadImagen) {
		this.idHeadImagen = idHeadImagen;
	}

	public int getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(int idImagen) {
		this.idImagen = idImagen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public int getIdType() {
		return idType;
	}

	public void setIdType(int idType) {
		this.idType = idType;
	}

	public byte[] getFiles() {
		return files;
	}

	public void setFiles(byte[] files) {
		this.files = files;
	}

	public ObjectId getIdDetail() {
		return idDetail;
	}

	public void setIdDetail(ObjectId idDetail) {
		this.idDetail = idDetail;
	}

	public String getUniqueKeyHash() {
		return uniqueKeyHash;
	}

	public void setUniqueKeyHash(String uniqueKeyHash) {
		this.uniqueKeyHash = uniqueKeyHash;
	}

	public ObjectId get_objectId() {
		return _objectId;
	}

	public void set_objectId(ObjectId _objectId) {
		this._objectId = _objectId;
	}

	public DetailImage() {
	}

}