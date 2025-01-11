package pillihuaman.com.pe.basebd.config;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.stereotype.Component;

@Component
public class MongoDBClient {


    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MongoDBClient.class);
    private static Map<String, MongoDatabase> databases;

    private final MongoDBConfig mongoDBConfig;

    @Autowired
    public MongoDBClient(MongoDBConfig mongoDBConfig) {
        this.mongoDBConfig = mongoDBConfig;
        initializeDatabases();
    }

    private void initializeDatabases() {
        CodecRegistry defaultCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        databases = new HashMap<String, MongoDatabase>();
        LOGGER.info("###############################################");


        for (MongoDBConexion c : mongoDBConfig.getConexiones()) {

            org.bson.codecs.pojo.PojoCodecProvider.Builder builder = PojoCodecProvider.builder();
            for (String p : c.getPojos()) {
                builder.register(p);
            }
            CodecProvider pojoCodecProvider = builder.build();
            CodecRegistry pojoCodecRegistry = fromRegistries(defaultCodecRegistry, fromProviders(pojoCodecProvider));

            databases.put(c.getDsname(), (new MongoDBConnectionFactory(c)).getClient().getDatabase(c.getDatabase()).withCodecRegistry(pojoCodecRegistry));
            LOGGER.info(">> " + c.getDsname() + " : " + c);
        }
        LOGGER.info(">> clientes : " + databases);
        LOGGER.info("###############################################");

    }
    //static {

    //}

    public static MongoCollection<Document> getCollection(String datasource, String colname) {

        MongoCollection<Document> collection = databases.get(datasource).getCollection(colname);
        return collection;
    }

    public static <T> MongoCollection<T> getCollection(String datasource, String colname, Class<T> t) {
        MongoCollection<T> collection = databases.get(datasource).getCollection(colname, t);
        return collection;
    }

    public static void loadServerScripts(String datasource) {
        BasicDBObject command = new BasicDBObject("eval", "db.loadServerScripts()");
        databases.get(datasource).runCommand(command);
    }

    public static Document runCommand(String datasource, String command) {
        BasicDBObject cmd = new BasicDBObject("eval", command);
        Document document = databases.get(datasource).runCommand(cmd);
        return document;
    }

    public static <T> T runCommand(String datasource, String command, Class<T> t) {
        BasicDBObject cmd = new BasicDBObject("eval", command);
        T document = databases.get(datasource).runCommand(cmd, t);
        return document;
    }

    public static GridFSBucket getGridFSBucket(String datasource, String bucketName) {
        return GridFSBuckets.create(databases.get(datasource), bucketName)
                .withReadPreference(databases.get(datasource).getReadPreference())
                .withWriteConcern(databases.get(datasource).getWriteConcern());
    }

    public static <T> List<T> findAll(String datasource, String colname, Class<T> t) {
        MongoCollection<T> collection = getCollection(datasource, colname, t);
        List<T> objetos = collection.find().into(new ArrayList<T>());
        return objetos;
    }

    public static MongoDatabase getDataBaseByDataSource(String datasource) {
        return databases.get(datasource);
    }


}