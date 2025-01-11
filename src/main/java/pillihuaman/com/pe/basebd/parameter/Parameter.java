package pillihuaman.com.pe.basebd.parameter;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
@Builder
@Setter
@Getter
@NoArgsConstructor // Add the no-argument constructor
public class Parameter implements Serializable {
    private static final long serialVersionUID = 1L;

    @BsonProperty(value = "_id")
    private ObjectId id;


    private String idCode;

    private String name;
    private String description;
    private List<String> parameterItems;
    @BsonCreator
    public Parameter(@BsonProperty("_id") ObjectId id,
                     @BsonProperty("idCode") String idCode,
                     @BsonProperty("name") String name,
                     @BsonProperty("description") String description,
                     @BsonProperty("parameterItems") List<String> parameterItems) {
        this.id = id;
        this.idCode = idCode;
        this.name = name;
        this.description = description;
        this.parameterItems = parameterItems;
    }
}