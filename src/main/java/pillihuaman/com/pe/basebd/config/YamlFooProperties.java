package pillihuaman.com.pe.basebd.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@ConfigurationProperties(prefix = "mongodb")
@PropertySource(value = "classpath:config.yml", factory = YamlPropertySourceFactory.class)
public class YamlFooProperties {
	public List<MongoDBConexion> conexiones;

	YamlFooProperties(List<MongoDBConexion> lst) {
		this.conexiones = lst;
	}
}
