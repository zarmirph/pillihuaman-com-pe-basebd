package pillihuaman.com.pe.basebd.product.dao;

import java.io.IOException;
import java.util.List;

import pillihuaman.com.pe.basebd.config.BaseMongoRepository;
import pillihuaman.com.pe.basebd.common.ProductStock;
import pillihuaman.com.pe.basebd.product.Product;

public interface ProductSupportDAO extends BaseMongoRepository<Product> {
	boolean  SaveProduct(Product request);
	List<Product> getCorrelativeProduct(Product request);
	List<Product> getallProductbyUser (Product request);

	 boolean saveStock(ProductStock request) throws IOException;
}
