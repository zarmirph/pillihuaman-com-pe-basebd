package pillihuaman.com.pe.basebd.config;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoDBConnectionFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBConnectionFactory.class);

    private MongoDBConexion mongoDBConexion;

    public MongoDBConnectionFactory(final MongoDBConexion mongoDBConexion) {
        this.mongoDBConexion = mongoDBConexion;
    }

    public MongoClient getClient() {
        LOGGER.info("Creando el cliente mongodb ... [" + mongoDBConexion.getDsname() +"]");
        
        final Credenciales configCredenciales = mongoDBConexion.getCredenciales();

        final MongoCredential credenciales = MongoCredential.createCredential(
                configCredenciales.getUsuario(),
                mongoDBConexion.getDatabase(),
                configCredenciales.getClave());
        
        final MongoClient client = MongoClients.create(
                MongoClientSettings.builder()
                        .credential(credenciales)
                        .readPreference(ReadPreference.valueOf(ConfigDefaults.READ_PREFERENCE.get(mongoDBConexion.getReadpreference())))
                        .writeConcern(WriteConcern.valueOf(ConfigDefaults.WRITE_CONCERN.get(mongoDBConexion.getWriteconcern())))
                        .applyToConnectionPoolSettings(
                        		builder -> builder
                        			.maxSize(PoolParams.MAX_POOL_SIZE.get(mongoDBConexion.getPoolconfig().getMaxPoolSize()))
                        			.minSize(PoolParams.MIN_POOL_SIZE.get(mongoDBConexion.getPoolconfig().getMinPoolSize()))
                        		)
                        .applyToClusterSettings(builder -> builder.hosts(getServers()))
                        .build()
        );
        LOGGER.info("cliente mongodb creado OK");
        return client;
    }

    private List<ServerAddress> getServers() {
        final List<Nodo> nodos = mongoDBConexion.getNodos();
        return nodos.stream()
                .map(nodo -> {
                    final ServerAddress serverAddress = new ServerAddress(nodo.getHost(), nodo.getPort());
                    return serverAddress;
                })
                .collect(Collectors.toList());
    }
}
