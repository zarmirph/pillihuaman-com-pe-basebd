package pillihuaman.com.pe.basebd.help;

import org.bson.Document;
import pillihuaman.com.pe.basebd.common.MyJsonWebToken;


import java.util.Date;
import java.util.Objects;

public class Util {

    public static Document insertAuditEntity (MyJsonWebToken obj) {
        Document doc = new Document();
        if(!Objects.isNull(obj)){
            if (!Objects.isNull(obj.getUser()) && !Objects.isNull(obj.getUser().getId()) && !Objects.isNull(obj.getUser().getMail())) {

                doc.put("codUser", obj.getUser().getId());
                doc.put("dateRegister", new Date());
                doc.put("email", obj.getUser().getMail());
            }}else{
            doc.put("dateRegister", new Date());
        }
        return doc;        }


    public static Document updateAuditEntity(MyJsonWebToken obj) {
        Document doc = new Document();
        if(!Objects.isNull(obj)){
        if (!Objects.isNull(obj.getUser()) && !Objects.isNull(obj.getUser().getId()) && !Objects.isNull(obj.getUser().getMail())) {
            doc.put("codUserUpdate", obj.getUser().getId());
            doc.put("dateUpdate", new Date());
            doc.put("mailUpdate", obj.getUser().getMail());
        }}else{
        doc.put("dateUpdate", new Date());
    }
        return doc;
    }
}





