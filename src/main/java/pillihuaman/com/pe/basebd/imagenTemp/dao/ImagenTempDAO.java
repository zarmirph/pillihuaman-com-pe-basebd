package pillihuaman.com.pe.basebd.imagenTemp.dao;

import org.bson.types.ObjectId;

import pillihuaman.com.pe.basebd.config.BaseMongoRepository;
import pillihuaman.com.pe.basebd.imagenTemp.ImagenTemp;

public interface ImagenTempDAO extends BaseMongoRepository<ImagenTemp> {
	ObjectId saveImagenFile(ImagenTemp detail) throws Exception;
	byte[] getImagenInputStream(String idImagen);
}
