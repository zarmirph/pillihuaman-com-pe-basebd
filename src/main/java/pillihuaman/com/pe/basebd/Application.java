package pillihuaman.com.pe.basebd;

import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

//@SpringBootApplication
// Package of your basebd module
@ComponentScan("pillihuaman.com.pe.lib") // Package where MongoDBClient is located
@Configuration
public class Application {


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		//app.setDefaultProperties(Collections.singletonMap("server.port", "8090"));
		app.run(args);
	}

	@Bean
	public CodecRegistry pojoCodecRegistry() {
		return fromRegistries(
				MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build())
		);
	}

	

}

