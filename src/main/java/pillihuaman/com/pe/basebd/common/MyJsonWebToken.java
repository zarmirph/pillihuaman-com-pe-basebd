package pillihuaman.com.pe.basebd.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.jboss.resteasy.jwt.JsonWebToken;
import pillihuaman.com.pe.lib.response.ResponseUser;

import java.io.Serializable;
@Getter
@Data
@Builder
@AllArgsConstructor
public class MyJsonWebToken extends JsonWebToken implements Serializable {

    private static long serialVersionUID = 1L;


    private ResponseUser user;
    private Aplication aplication;


    public MyJsonWebToken() {
        super();
        user = new ResponseUser();
        aplication = new Aplication();


    }


    public static class Aplication {

        public ObjectId getAplicationID() {
            return aplicationID;
        }

        public void setAplicationID(ObjectId aplicationID) {
            this.aplicationID = aplicationID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMultiSession() {
            return multiSession;
        }

        public void setMultiSession(String multiSession) {
            this.multiSession = multiSession;
        }

        private ObjectId aplicationID;
        private String name;
        private String multiSession;

    }


    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    /**
     * @param serialversionuid the serialversionuid to set
     */
    public static void setSerialversionuid(long serialversionuid) {
        serialVersionUID = serialversionuid;
    }


    /**
     * @param idUser the idUser to set
     */


    /**
     * @param aplicationID the aplicationID to set
     */
    public void setAplicationID(ObjectId aplicationID) {
        this.aplication.aplicationID = aplicationID;
    }


    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.aplication.name = name;
    }

    /**
     * @param multiSession the multiSession to set
     */
    public void setMultiSession(String multiSession) {
        this.aplication.multiSession = multiSession;
    }

    public void setUser(ResponseUser user) {
        this.user = user;
    }

    public void setAplication(Aplication aplication) {
        this.aplication = aplication;
    }

}
