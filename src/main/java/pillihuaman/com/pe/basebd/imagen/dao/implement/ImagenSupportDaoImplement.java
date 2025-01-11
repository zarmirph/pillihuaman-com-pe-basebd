package pillihuaman.com.pe.basebd.imagen.dao.implement;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import pillihuaman.com.pe.basebd.config.AbstractMongoRepositoryImpl;
import pillihuaman.com.pe.basebd.help.Constants;
import pillihuaman.com.pe.basebd.common.ProductStock;
import pillihuaman.com.pe.basebd.imagen.DetailImage;
import pillihuaman.com.pe.basebd.imagen.Imagen;
import pillihuaman.com.pe.basebd.imagen.dao.ImagenSupportDAO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class ImagenSupportDaoImplement extends AbstractMongoRepositoryImpl<Imagen> implements ImagenSupportDAO {
	ImagenSupportDaoImplement() {
		DS_WRITE = Constants.DW;
		// DS_READ = Constants.DR;
		COLLECTION = Constants.COLLECTION_IMAGEN;

	}

	@Override
	public Class<Imagen> provideEntityClass() {
		// TODO Auto-generated method stub
		return Imagen.class;
	}

	@Override
	public ObjectId saveImagenFile(DetailImage detail) throws Exception {

		InputStream iss = new ByteArrayInputStream(detail.getFiles());
		ByteArrayInputStream arrayIn = inputStreamToArrayInputStream(iss);

		GridFSBucket gridFSFilesBucket = getGridFSBucket("files");
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
		return fileId;
	}

	@Override
	public ObjectId saveImagenStockFile(DetailImage detail) throws Exception {

		InputStream iss = new ByteArrayInputStream(detail.getFiles());
		ByteArrayInputStream arrayIn = inputStreamToArrayInputStream(iss);

		GridFSBucket gridFSFilesBucket = getGridFSBucket("files");
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
		return fileId;
	}

	public static ByteArrayInputStream inputStreamToArrayInputStream(InputStream inStream) throws IOException {
		byte[] buffer = new byte[1024];
		int bytesRead;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		while ((bytesRead = inStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}

		return new ByteArrayInputStream(outputStream.toByteArray());
	}


	@Override
	public Document saveImagenHeader(Imagen request) {
		Document doc = new Document();
		Document docAud = new Document();
		doc.put("description", request.getDescription());
		doc.put("name", request.getName());
		doc.put("idUser", request.getIdUser());
		doc.put("idHeadImagen", request.getIdHeadImagen());
		doc.put("idProduct", request.getIdProduct());
		doc.put("countRanking", 0);
		doc.put("clickCount", 0);
		docAud.put("fecRegis", new Date());
		doc.put("auditEntity", docAud);
		return save(doc);
	}

	@Override
	public List<Imagen> getCorrelativeImagen(Imagen request) {
		MongoCollection<Imagen> collection = getCollection(this.collectionName, Imagen.class);
		Document query = new Document();
		Document sort = new Document().append("idHeadImagen", -1);
		List<Imagen> lisProduct = collection.find(query, Imagen.class).sort(sort).limit(1)
				.into(new ArrayList<Imagen>());
		return lisProduct;
	}
	@Override
	public List<Imagen> getTopImagen(int page ,int perpage) {

		Document quer=new Document();
		int skip=0;

		if(page==1) {
			skip=0;
		}else {
			if(page>0) {
				skip= (perpage*(page-1));
			}
		}
		List<Document> pipeline = Arrays.asList(new Document()
				.append("$sort", new Document()
						.append("clickCount", -1)), new Document()
				.append("$skip", skip), new Document()
				.append("$limit", perpage));

		MongoCollection<Imagen> collection = getCollection(this.collectionName, Imagen.class);

		//int cantTotalRegistros = collection.aggregate(pipeline).into(new ArrayList<Imagen>()).size();
		List<Imagen> lisImg	=	collection.aggregate(pipeline).into(new ArrayList<Imagen>());
		return lisImg;
	}

	@Override
	public ProductStock getStockProduct(int idProduct) {

		/*Document quer=new Document();


		MongoCollection<ProductStock> collection = getCollection(this.collectionName, ProductStock.class);

		//int cantTotalRegistros = collection.aggregate(pipeline).into(new ArrayList<Imagen>()).size();
		List<ProductStock> proStock	=	collection.aggregate(pipeline).into(new ArrayList<ProductStock>());
		return proStock.get(0);*/
		return null;
	}

	@Override
	public void countImagenClickEventSave(String imFile) {

		com.mongodb.client.MongoCollection<Imagen> collection = getCollection(this.collectionName, this.provideEntityClass());
		List<Imagen> getLaslstImagen= getLastCountImagenRank(imFile);
		if (!getLaslstImagen.isEmpty()) {
			int netxCount = getLaslstImagen.get(0).getClickCount() + 1;
			collection.updateOne(Filters.and(Filters.eq("_id", new ObjectId(imFile))), Updates.set("clickCount", netxCount));
		}
	}
	@Override
	public List<Imagen> getLastCountImagenRank(String id) {
		com.mongodb.client.MongoCollection<Imagen> collection = getCollection(this.collectionName, this.provideEntityClass());

		List<Document> pipeline = Arrays.asList(new Document()
				.append("$match", new Document()
						.append("_id", new ObjectId(id))), new Document()
				.append("$project", new Document()
						.append("clickCount", 1)));
		List<Imagen> lstImagen = collection.aggregate(pipeline)
				.into(new ArrayList<Imagen>());
		return lstImagen;
	}

	}
