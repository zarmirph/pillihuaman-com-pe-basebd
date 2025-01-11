package pillihuaman.com.pe.basebd.product.dao;

import org.bson.types.ObjectId;
import pillihuaman.com.pe.basebd.config.BaseMongoRepository;
import pillihuaman.com.pe.basebd.common.ProductStock;
import pillihuaman.com.pe.lib.request.ImagenDetail;

public interface StockSupportDAO extends BaseMongoRepository<ProductStock> {

	 boolean saveStock(ProductStock request) throws Exception;
	ObjectId saveImagenFileOfStock(ImagenDetail detail) throws Exception;
}
