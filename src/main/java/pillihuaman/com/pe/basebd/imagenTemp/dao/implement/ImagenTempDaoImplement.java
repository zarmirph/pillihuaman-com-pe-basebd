package pillihuaman.com.pe.basebd.imagenTemp.dao.implement;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;

import pillihuaman.com.pe.basebd.imagenTemp.ImagenTemp;
import pillihuaman.com.pe.basebd.imagenTemp.dao.ImagenTempDAO;
import pillihuaman.com.pe.basebd.config.AbstractMongoRepositoryImpl;
import pillihuaman.com.pe.basebd.help.Constants;

@Component
public class ImagenTempDaoImplement extends AbstractMongoRepositoryImpl<ImagenTemp> implements ImagenTempDAO {
	ImagenTempDaoImplement() {
		DS_WRITE = Constants.DW;
		COLLECTION = Constants.COLLECTION_IMAGEN_TEMP_FILES;

	}
	// AbstractMongoDBRepositoryImpl<Product> implements ProductSupportDAO

	@Override
	public Class<ImagenTemp> provideEntityClass() {
		// TODO Auto-generated method stub
		return ImagenTemp.class;
	}

	@Override
	public ObjectId saveImagenFile(ImagenTemp detail) throws Exception {
		InputStream iss = new ByteArrayInputStream(detail.getFiles());
		ByteArrayInputStream arrayIn = inputStreamToArrayInputStream(iss);

		GridFSBucket gridFSFilesBucket = getGridFSBucket("imagenTemp");
		Document doc = new Document();
		doc.put("id_temp", detail.getIdTemp());
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
	public byte[] getImagenInputStream(String idImagen) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			Bson querdy = null;
			getGridFSBucket("imagenTemp");
			com.mongodb.client.gridfs.GridFSBucket gridFSBucket = getGridFSBucket("imagenTemp");
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
			// throw e;
		}
		return null;
	}

}
