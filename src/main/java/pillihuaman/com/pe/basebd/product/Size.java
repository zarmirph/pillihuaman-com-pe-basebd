package pillihuaman.com.pe.basebd.product;





import org.bson.types.ObjectId;
import pillihuaman.com.pe.lib.commons.Parameters;


import java.util.List;

public class Size  {
    private ObjectId id;
    private Parameters parameter;
    List<Color> color;

    public List<Color> getColor() {
        return color;
    }

    public void setColor(List<Color> color) {
        this.color = color;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Parameters getParameter() {
        return parameter;
    }

    public void setParameter(Parameters parameter) {
        this.parameter = parameter;
    }
}
