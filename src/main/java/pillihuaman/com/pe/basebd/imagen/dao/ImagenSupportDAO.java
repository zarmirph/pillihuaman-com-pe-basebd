package pillihuaman.com.pe.basebd.imagen.dao;


import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import pillihuaman.com.pe.basebd.imagen.Imagen;
import pillihuaman.com.pe.basebd.config.BaseMongoRepository;
import pillihuaman.com.pe.basebd.common.ProductStock;
import pillihuaman.com.pe.basebd.imagen.DetailImage;

public interface ImagenSupportDAO extends BaseMongoRepository<Imagen> {
	Document saveImagenHeader(Imagen  request);
	List<Imagen> getCorrelativeImagen(Imagen request);

	default ObjectId saveImagenFile(DetailImage detail) throws Exception {
		return null;
	}

	List<Imagen> getTopImagen(int page ,int perpage);
	void countImagenClickEventSave(String idDetail);
	List<Imagen> getLastCountImagenRank(String id);
	 ProductStock getStockProduct(int idProduct);
	 ObjectId saveImagenStockFile(DetailImage detail) throws Exception;
}
