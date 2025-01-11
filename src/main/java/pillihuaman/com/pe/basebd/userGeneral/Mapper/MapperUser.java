package pillihuaman.com.pe.basebd.userGeneral.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pillihuaman.com.pe.basebd.user.User;
import pillihuaman.com.pe.lib.request.ReqUser;
import pillihuaman.com.pe.lib.response.RespUser;

import java.util.List;

public interface MapperUser {
    MapperUser INSTANCE = Mappers.getMapper(MapperUser.class);

    @Mapping(source = "idUser", target = "id")
    @Mapping(source = "alias", target = "alias")
    @Mapping(source = "idSystem", target = "idSystem")
    @Mapping(source = "mail", target = "mail")
    @Mapping(source = "mobilPhone", target = "mobilPhone")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "username", target = "userName")
    @Mapping(source = "apiPassword", target = "apiPassword")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "salPassword", target = "salPassword")
    @Mapping(source = "typeDocument", target = "typeDocument")
    @Mapping(source = "numTypeDocument", target = "numTypeDocument")
    User reqUserToUser(ReqUser reqUser);
    @Mapping(source = "id", target = "idUser")
    @Mapping(source = "alias", target = "alias")
    @Mapping(source = "idSystem", target = "idSystem")
    @Mapping(source = "mail", target = "mail")
    @Mapping(source = "mobilPhone", target = "mobilPhone")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "apiPassword", target = "apiPassword")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "salPassword", target = "salPassword")
    @Mapping(source = "enabled", target = "enabled")
    @Mapping(source = "role.id", target = "idRol")
    RespUser userToRespUser(User user);

    List<RespUser> usersToRespUsers(List<User> users);
    @Mapping(source = "idUser", target = "id")
    @Mapping(source = "alias", target = "alias")
    @Mapping(source = "idSystem", target = "idSystem")
    @Mapping(source = "mail", target = "mail")
    @Mapping(source = "mobilPhone", target = "mobilPhone")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "userName", target = "userName")
    @Mapping(source = "apiPassword", target = "apiPassword")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "salPassword", target = "salPassword")
    @Mapping(source = "enabled", target = "enabled")
    @Mapping(source = "idRol", target = "role.id")
    User respUserToUser(RespUser respUser);

}
