package pillihuaman.com.pe.basebd.config;

import java.util.Objects;

public class Nodo {
	private String host;

    private int port;

    public Nodo() {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Nodo nodo = (Nodo) o;
        return port == nodo.port &&
                Objects.equals(host, nodo.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
    	sb.append("Nodo{");
    	sb.append("host='").append(this.host).append("'");
    	sb.append("port=").append(this.port);
    	sb.append("}");
    	return sb.toString();
    }
}
