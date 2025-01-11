package pillihuaman.com.pe.basebd.config;


import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public  class ConfigMain {

	@Autowired
	private  AppProperties appProperties ;

	@Autowired
	private MongoDBConfig mongoDBConfig;

	public MongoClient getConexion() {
		ConnectionString connectionString = new ConnectionString(
				"mongodb://" +
						mongoDBConfig.getConexiones().get(0).getNodos().get(0).getHost() + ":" +
						mongoDBConfig.getConexiones().get(0).getNodos().get(0).getPort()
		);

		// Create a MongoClient using the connection string
		MongoClient mongoClient = MongoClients.create(connectionString);

		// mongoClient = new MongoClient(appProperties.getHost(), Integer.parseInt(appProperties.getPort()));

		return mongoClient;

	}

	public MongoDatabase getDataBases(String databaseName, MongoClient mongoClient) {
		MongoDatabase database = mongoClient.getDatabase(databaseName);
		return database;

	}

	@SuppressWarnings("rawtypes")
	public MongoCollection getcollection(String collectionName, MongoDatabase dataBase) {
		MongoCollection collection = dataBase.getCollection(collectionName);

		return collection;

	}

	ConfigMain(){}


}
