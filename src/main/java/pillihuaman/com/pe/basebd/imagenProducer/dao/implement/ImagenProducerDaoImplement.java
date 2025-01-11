package pillihuaman.com.pe.basebd.imagenProducer.dao.implement;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import pillihuaman.com.pe.basebd.imagenProducer.ImagenFile;
import pillihuaman.com.pe.basebd.config.AbstractMongoRepositoryImpl;
import pillihuaman.com.pe.basebd.help.Constants;
import pillihuaman.com.pe.basebd.imagenProducer.dao.ImagenProducerDAO;

@Component
public class ImagenProducerDaoImplement extends AbstractMongoRepositoryImpl<ImagenFile> implements ImagenProducerDAO {
    ImagenProducerDaoImplement() {
        DS_WRITE = Constants.DW;
        COLLECTION = Constants.COLLECTION_IMAGEN_FILES;

    }
    //AbstractMongoDBRepositoryImpl<Product> implements ProductSupportDAO

    @Override
    public Class<ImagenFile> provideEntityClass() {
        // TODO Auto-generated method stub
        return ImagenFile.class;
    }


    @Override
    public List<ImagenFile> getImagen(String idImagen) {
        com.mongodb.client.MongoCollection<ImagenFile> collection = getCollection("files.files", this.provideEntityClass());
        List<Document> pipeline = Arrays.asList(new Document()
                .append("$lookup", new Document()
                        .append("from", "files.chunks")
                        .append("localField", "_id")
                        .append("foreignField", "files_id")
                        .append("as", "file")), new Document()
                .append("$match", new Document()
                        .append("_id", new ObjectId(idImagen))));
        int cantTotalRegistros = collection.aggregate(pipeline).into(new ArrayList<ImagenFile>()).size();
        List<ImagenFile> comprobantes = collection.aggregate(pipeline)
                .into(new ArrayList<ImagenFile>());
        return comprobantes;
    }

    @Override
    public byte[] getImagenInputStream(String idImagen) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Bson querdy = null;

            getGridFSBucket("files");
            com.mongodb.client.gridfs.GridFSBucket gridFSBucket = getGridFSBucket("files"); // MongoClient("files.files", "files");
            querdy = new Document().append("_id", new ObjectId(idImagen));
            com.mongodb.client.gridfs.model.GridFSFile lst = gridFSBucket.find(querdy).first();
            if (java.util.Objects.nonNull(lst)) {
                gridFSBucket.downloadToStream(lst.getId(), baos);
                return baos.toByteArray();
            } else {
                return null;
            }
        } catch (Exception e) {
            StringWriter printStackTrace = new StringWriter();
            e.printStackTrace(new PrintWriter(printStackTrace));
            //throw e;
        }
        return null;
    }

    @Override
    public List<ImagenFile> getTopImagen(int page, int perPage, String idDetail) {
        int skip = 0;

        if (page == 1) {
            skip = 0;
        } else {
            if (page > 0) {
                skip = (perPage * (page - 1));
            }
        }
        com.mongodb.client.MongoCollection<ImagenFile> collection = getCollection("files.files", this.provideEntityClass());
        //Created with NoSQLBooster, the essential IDE for MongoDB - https://nosqlbooster.com
        List<Document> pipeline = Arrays.asList(new Document()
                .append("$match", new Document()
                        .append("$and", Arrays.asList(new Document()
                                .append("metadata.idDetail", new Document()
                                        .append("$eq", new ObjectId(idDetail)))))), new Document()
                .append("$lookup", new Document()
                        .append("from", "files.chunks")
                        .append("localField", "_id")
                        .append("foreignField", "files_id")
                        .append("as", "file")), new Document()
                .append("$sort", new Document()
                        .append("metadata.countRanking", -1)), new Document()
                .append("$skip", skip), new Document()
                .append("$limit", perPage));

        int cantTotalRegistros = collection.aggregate(pipeline).into(new ArrayList<ImagenFile>()).size();
        List<ImagenFile> lstImagenFile = collection.aggregate(pipeline)
                .into(new ArrayList<ImagenFile>());
        return lstImagenFile;
    }

    @Override
    public void saveClickCountImagen(ImagenFile imFile) {

        com.mongodb.client.MongoCollection<ImagenFile> collection = getCollection("files.files", this.provideEntityClass());
        List<ImagenFile> getLasCountImagen = getLastCountImagen(imFile.getId().toString());
        if (!getLasCountImagen.isEmpty()) {
            int netxCount = getLasCountImagen.get(0).getMetadata().getClickCount() + 1;
            collection.updateOne(Filters.and(Filters.eq("_id", imFile.getId())), Updates.set("metadata" + "." + "clickCount", netxCount));
        }
    }

    @Override
    public List<ImagenFile> getLastCountImagen(String id) {
        com.mongodb.client.MongoCollection<ImagenFile> collection = getCollection("files.files", this.provideEntityClass());

        List<Document> pipeline = Arrays.asList(new Document()
                .append("$match", new Document()
                        .append("_id", new ObjectId(id))), new Document()
                .append("$project", new Document()
                        .append("metadata.clickCount", 1)));
        List<ImagenFile> lstImagenFile = collection.aggregate(pipeline)
                .into(new ArrayList<ImagenFile>());
        return lstImagenFile;
    }
}
