package pillihuaman.com.pe.basebd.product.dao.implement;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.stereotype.Component;
import pillihuaman.com.pe.basebd.product.Color;
import pillihuaman.com.pe.basebd.product.Size;
import pillihuaman.com.pe.basebd.product.dao.ProductSupportDAO;
import pillihuaman.com.pe.basebd.config.AbstractMongoRepositoryImpl;
import pillihuaman.com.pe.basebd.help.AuditEntity;
import pillihuaman.com.pe.basebd.help.Constants;
import pillihuaman.com.pe.lib.commons.Utils;
import pillihuaman.com.pe.basebd.common.ProductStock;
import pillihuaman.com.pe.lib.request.ImagenDetail;
import pillihuaman.com.pe.basebd.product.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ProductSupportDaoImplement extends AbstractMongoRepositoryImpl<Product> implements ProductSupportDAO {
    ProductSupportDaoImplement() {
        DS_WRITE = Constants.DW;
        // DS_READ = Constants.DR;
        COLLECTION = Constants.COLLECTION_PRODUCT;

    }

    @Override
    public boolean SaveProduct(Product request) {
        Document doc = new Document();
        Document docAud = new Document();
        AuditEntity aud = new AuditEntity();

        request.setAuditEntity(aud);
        doc.put("description", request.getDescription());
        doc.put("name", request.getName());
        doc.put("idUser", request.getIdUser());
        doc.put("auditEntity", docAud);
        save(doc);
        return true;
    }

    @Override
    public List<Product> getCorrelativeProduct(Product request) {

        MongoCollection<Product> collection = getCollection(this.collectionName, Product.class);
        Document query = new Document();
        Document sort = new Document().append("id_product", -1);
        List<Product> lisProduct = collection.find(query, Product.class).sort(sort).limit(1)
                .into(new ArrayList<Product>());
        return lisProduct;
    }

    @Override
    public Class<Product> provideEntityClass() {
        // TODO Auto-generated method stub
        return Product.class;
    }

    @Override
    public List<Product> getallProductbyUser(Product request) {
        MongoCollection<Product> collection = getCollection(this.collectionName, Product.class);
        Document query = new Document().append("idUser", request.getIdUser());
        List<Product> lisProduct = collection.find(query, Product.class)
                .into(new ArrayList<Product>());
        return lisProduct;
    }

    @Override
    public boolean saveStock(ProductStock request) throws IOException {
        Document doc = new Document();
        Document docAud = new Document();
        AuditEntity aud = new AuditEntity();


        doc.put("idProduct", request.getIdProduct());
        doc.put("expirationDate", request.getExpirationDate());
        doc.put("creationDate", request.getCreationDate());
        doc.put("stock.idProduct", request.getIdProduct());
        doc.put("stock.size", request.getStock().getSize());
        if (Objects.nonNull(request.getStock())) {
            if (Objects.nonNull(request.getStock().getSize())) {
                if (Objects.nonNull(request.getStock().getSize())) {
                    for (Size si :
                            request.getStock().getSize()) {

                        if (Objects.nonNull(si.getColor())) {
                            for (Color co :
                                    si.getColor()) {
                                if (Objects.nonNull(co)) {

                                    for (ImagenDetail deta :
                                            co.getListImagen()) {
                                        Utils.convertBase64ImagenToByteArray(deta.getValue());


                                    }
                                }

                            }

                        }
                    }


                }

            }

        }



        request.setAuditEntity(aud);
        doc.put("auditEntity", docAud);
        save(doc);
        return true;
    }
}
