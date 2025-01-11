package pillihuaman.com.pe.basebd.parameter.dao.implement;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import pillihuaman.com.pe.basebd.common.MyJsonWebToken;
import pillihuaman.com.pe.basebd.parameter.Parameter;
import pillihuaman.com.pe.basebd.config.AbstractMongoRepositoryImpl;
import pillihuaman.com.pe.basebd.help.Constants;
import pillihuaman.com.pe.basebd.parameter.dao.ParameterRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParameterDaoImplement extends AbstractMongoRepositoryImpl<Parameter> implements ParameterRepository {
    ParameterDaoImplement() {
        DS_WRITE = Constants.DW;
        // DS_READ = Constants.DR;
        COLLECTION = Constants.COLLECTION_PARAMETER;
    }

    @Override
    public Class<Parameter> provideEntityClass() {
        // TODO Auto-generated method stub
        return Parameter.class;
    }

    @Override
    public Parameter saveParemeter(Parameter request, MyJsonWebToken tok) {
        Document doc = new Document();
        //request.setAuditEntity( Util.InsertAuditEntity(tok));
        doc.put("idCode", request.getIdCode());
        doc.put("name", request.getName());
        doc.put("description", request.getDescription());
        doc.put("parameterItems", request.getParameterItems());
       // doc.put("auditEntity", Util.insertAuditEntity(tok));

        Document docs = save(doc);

        return  Parameter.builder().id((ObjectId) docs.get("_id")).build();
    }

    @Override
    public List<Parameter> getParameterByIdCode(Parameter request) {
        MongoCollection<Parameter> collection = getCollection(this.collectionName, Parameter.class);
        Document query = new Document().append("idCode", request.getIdCode());
        List<Parameter> lisParameter = collection.find(query, Parameter.class)
                .into(new ArrayList<Parameter>());
        return lisParameter;
    }

    @Override
    public List<Parameter> getParameterByName(String name) {
        MongoCollection<Parameter> collection = getCollection(this.collectionName, Parameter.class);
        Document query = new Document().append("name", name);
        List<Parameter> lisParameter = collection.find(query, Parameter.class)
                .into(new ArrayList<Parameter>());
        return lisParameter;
    }


}
