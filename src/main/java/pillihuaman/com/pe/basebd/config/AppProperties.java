package pillihuaman.com.pe.basebd.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AppProperties {
 
 private String port;
 private String host;
 private String database;

	public void setPort(String port) {
	this.port = port;
}

	public void setHost(String host) {
	this.host = host;
}

	public void setDatabase(String database) {
	this.database = database;
}

 
}