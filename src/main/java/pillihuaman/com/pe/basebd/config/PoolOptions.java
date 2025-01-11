package pillihuaman.com.pe.basebd.config;

import java.io.Serializable;

public class PoolOptions implements Serializable {

	private static final long serialVersionUID = -5909944405498165963L;
	private int maxPoolSize;
	private int minPoolSize;
	private int maxIdleTimeMS;
	private int waitQueueMultiple;
	private int waitQueueTimeoutMS;
	public int getMaxPoolSize() {
		return maxPoolSize;
	}
	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}
	public int getMinPoolSize() {
		return minPoolSize;
	}
	public void setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
	}
	public int getMaxIdleTimeMS() {
		return maxIdleTimeMS;
	}
	public void setMaxIdleTimeMS(int maxIdleTimeMS) {
		this.maxIdleTimeMS = maxIdleTimeMS;
	}
	public int getWaitQueueMultiple() {
		return waitQueueMultiple;
	}
	public void setWaitQueueMultiple(int waitQueueMultiple) {
		this.waitQueueMultiple = waitQueueMultiple;
	}
	public int getWaitQueueTimeoutMS() {
		return waitQueueTimeoutMS;
	}
	public void setWaitQueueTimeoutMS(int waitQueueTimeoutMS) {
		this.waitQueueTimeoutMS = waitQueueTimeoutMS;
	}
	
	@Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
    	sb.append("PoolOptions{");
    	sb.append("maxPoolSize=").append(this.maxPoolSize);
    	sb.append("minPoolSize=").append(this.minPoolSize);
    	sb.append("maxIdleTimeMS=").append(this.maxIdleTimeMS);
    	sb.append("waitQueueMultiple=").append(this.waitQueueMultiple);
    	sb.append("waitQueueTimeoutMS=").append(this.waitQueueTimeoutMS);
    	sb.append("}");
    	return sb.toString();
    }
}
