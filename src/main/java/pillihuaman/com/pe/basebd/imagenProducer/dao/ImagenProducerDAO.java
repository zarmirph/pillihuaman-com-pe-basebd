package pillihuaman.com.pe.basebd.imagenProducer.dao;

import java.util.List;

import pillihuaman.com.pe.basebd.imagenProducer.ImagenFile;
import pillihuaman.com.pe.basebd.config.BaseMongoRepository;

public interface ImagenProducerDAO extends BaseMongoRepository<ImagenFile> {
	List<ImagenFile> getImagen(String idImagen);
	
	byte[] getImagenInputStream(String idImagen);
	List<ImagenFile> getTopImagen(int page,int perPage,String idDetail);
	List<ImagenFile> getLastCountImagen(String id);

	void  saveClickCountImagen(ImagenFile imFile);
}
