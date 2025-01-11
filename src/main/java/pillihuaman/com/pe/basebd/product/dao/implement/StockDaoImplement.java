package pillihuaman.com.pe.basebd.product.dao.implement;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pillihuaman.com.pe.basebd.product.Color;
import pillihuaman.com.pe.basebd.product.Size;
import pillihuaman.com.pe.basebd.config.AbstractMongoRepositoryImpl;
import pillihuaman.com.pe.basebd.help.AuditEntity;
import pillihuaman.com.pe.basebd.help.Constants;
import pillihuaman.com.pe.lib.commons.Utils;
import pillihuaman.com.pe.basebd.common.ProductStock;
import pillihuaman.com.pe.basebd.imagen.DetailImage;
import pillihuaman.com.pe.basebd.imagen.dao.ImagenSupportDAO;
import pillihuaman.com.pe.lib.request.ImagenDetail;
import pillihuaman.com.pe.basebd.product.dao.StockSupportDAO;

import java.util.Objects;

@Component
public class StockDaoImplement extends AbstractMongoRepositoryImpl<ProductStock> implements StockSupportDAO {
    @Autowired
    private ImagenSupportDAO imagenSupportDAO;

    StockDaoImplement() {
        DS_WRITE = Constants.DW;
        // DS_READ = Constants.DR;
        COLLECTION = Constants.COLLECTION_STOCK;

    }

    @Override
    public Class<ProductStock> provideEntityClass() {
        // TODO Auto-generated method stub
        return ProductStock.class;
    }

    @Override
    public boolean saveStock(ProductStock request) throws Exception {
        Document doc = new Document();
        Document docAud = new Document();
        AuditEntity aud = new AuditEntity();

        doc.put("idProduct", request.getIdProduct());
        doc.put("expirationDate", request.getExpirationDate());
        doc.put("creationDate", request.getCreationDate());
        doc.put("stock.idProduct", request.getIdProduct());
        doc.put("stock.size", request.getStock().getSize());
        doc.put("state", 1);
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
                                        //saveImagenFileOfStock(deta);
                                        DetailImage detIm = new DetailImage();
                                        detIm.setName(deta.getName());
                                        detIm.setClickCount(0);
                                        detIm.setIndex(0);
                                        detIm.setCountRanking(0);
                                        detIm.setIdDetail(co.getIdProduct());
                                        // detIm.setIdHeadImagen(co.getIdProduct());
                                        detIm.setFiles(Utils.convertBase64ImagenToByteArray(deta.getValue()));
                                        imagenSupportDAO.saveImagenFile(detIm);
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

    @Override
    public ObjectId saveImagenFileOfStock(ImagenDetail detail) throws Exception {
       /* InputStream iss = new ByteArrayInputStream(detail.getFiles());
        ByteArrayInputStream arrayIn = Utils.inputStreamToArrayInputStream(iss);

        GridFSBucket gridFSFilesBucket = getGridFSBucket("stockFiles");
        Document doc = new Document();
        doc.put("idImagen", detail.getIdImagen());
        doc.put("idHeadImagen", detail.getIdHeadImagen());
        doc.put("name", detail.getName());
        doc.put("countRanking", detail.getCountRanking());
        doc.put("clickCount", detail.getClickCount());
        doc.put("idDetail", detail.getIdDetail());
        doc.put("index", detail.getIndex());
        GridFSUploadOptions options = new GridFSUploadOptions().metadata(doc);
        ObjectId fileId = gridFSFilesBucket.uploadFromStream(detail.getName(), arrayIn, options);
        iss.close();
        arrayIn.close();
        arrayIn = null;
        iss = null;
        return fileId;*/
        return null;
    }


}
