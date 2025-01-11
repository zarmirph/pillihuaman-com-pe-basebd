package pillihuaman.com.pe.basebd.config;

public enum PoolParams {
	MAX_POOL_SIZE(500),
	MIN_POOL_SIZE(0),
	MAX_IDLE_TIME_MS(900000),
	WAIT_QUEUE_MULTIPLE(10),
	WAIT_QUEUE_TIMEOUT_MS(60000);
	
	private int param;
	
	private PoolParams(int param) {
		this.param = param;
	}
	
	public int get(int configParam) {
		if (configParam == 0) {
			return this.param;
		} else {
			return configParam;
		}
	}
}
