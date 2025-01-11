package pillihuaman.com.pe.basebd.imagenProducer;

import org.bson.types.ObjectId;

public class Metadata {
    private int idImagen;
    private int idHeadImagen;
    private String name;
    private int countRanking;
    private int clickCount;

    private int index;

    private ObjectId idDetail;

    public ObjectId getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(ObjectId idDetail) {
        this.idDetail = idDetail;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public int getIdHeadImagen() {
        return idHeadImagen;
    }

    public void setIdHeadImagen(int idHeadImagen) {
        this.idHeadImagen = idHeadImagen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountRanking() {
        return countRanking;
    }

    public void setCountRanking(int countRanking) {
        this.countRanking = countRanking;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }




}
