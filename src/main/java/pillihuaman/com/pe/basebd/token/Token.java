package pillihuaman.com.pe.basebd.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import pillihuaman.com.pe.basebd.user.User;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@Component
@Document(collection = "token")
public class Token implements Serializable  {

  private static final long serialVersionUID = 1L;
  @BsonProperty(value = "_id")
  public String token;
  public TokenType tokenType = TokenType.BEARER;
  public boolean revoked;
  public boolean expired;
  public User user;

  public Token() {

  }
}
