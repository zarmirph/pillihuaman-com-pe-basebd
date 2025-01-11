package pillihuaman.com.pe.basebd.config;



import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public class Credenciales {

    private String usuario;

    @JsonSerialize(using = PasswordSerializer.class)
    private char[] clave;

    public Credenciales() {
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Credenciales that = (Credenciales) o;
        return Objects.equals(usuario, that.usuario) ;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(usuario);
        result = 31 * result;// + Arrays.hashCode(clave);
        return result;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(final String usuario) {
        this.usuario = usuario;
    }

    public char[] getClave() {
        return clave;
    }

    public void setClave(final char[] clave) {
        this.clave = clave;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
    	sb.append("Miembro{");
    	sb.append("usuario='").append(this.usuario).append("'");
    	sb.append("clave=").append("******");
    	sb.append("}");
    	return sb.toString();
    }
}
