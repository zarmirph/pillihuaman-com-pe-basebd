package pillihuaman.com.pe.basebd.config;
import java.util.Optional;
public enum ConfigDefaults {
	READ_PREFERENCE("secondaryPreferred"),
	WRITE_CONCERN("w1");
	
	private String config;
	
	private ConfigDefaults(String config) {
		this.config = config;
	}
	
	public String get(String config) {
		return Optional.ofNullable(config).orElse(this.config);
	}

}
