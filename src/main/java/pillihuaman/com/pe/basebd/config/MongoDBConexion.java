package pillihuaman.com.pe.basebd.config;

import java.util.List;

public class MongoDBConexion {

	private Credenciales credenciales;
    private List<Nodo> nodos;
    private String database;
    private String replicaset;
    private String dsname;
    private PoolOptions poolconfig;
    private String readpreference;
    private String writeconcern;
    private List<String> pojos;

	public Credenciales getCredenciales() {
		return credenciales;
	}

	public void setCredenciales(Credenciales credenciales) {
		this.credenciales = credenciales;
	}

	public List<Nodo> getNodos() {
		return nodos;
	}

	public void setNodos(List<Nodo> nodos) {
		this.nodos = nodos;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getReplicaset() {
		return replicaset;
	}

	public void setReplicaset(String replicaset) {
		this.replicaset = replicaset;
	}

	public String getDsname() {
		return dsname;
	}

	public void setDsname(String dsname) {
		this.dsname = dsname;
	}
    
	public PoolOptions getPoolconfig() {
		return poolconfig;
	}

	public void setPoolconfig(PoolOptions poolconfig) {
		this.poolconfig = poolconfig;
	}

	public String getReadpreference() {
		return readpreference;
	}

	public void setReadpreference(String readpreference) {
		this.readpreference = readpreference;
	}

	public String getWriteconcern() {
		return writeconcern;
	}

	public void setWriteconcern(String writeconcern) {
		this.writeconcern = writeconcern;
	}

	public List<String> getPojos() {
		return pojos;
	}

	public void setPojos(List<String> pojos) {
		this.pojos = pojos;
	}

	@Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
    	sb.append("MongoDBConexion{");
    	sb.append("credenciales='").append(this.credenciales).append("'");
    	sb.append("nodos=").append(this.nodos);
    	sb.append("database=").append(this.database);
    	sb.append("dsname=").append(this.dsname);
    	sb.append("poolconfig=").append(this.poolconfig);
    	sb.append("readpreference=").append(this.readpreference);
    	sb.append("writeconcern=").append(this.writeconcern);
    	sb.append("}");
    	return sb.toString();
    }
}