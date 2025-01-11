package pillihuaman.com.pe.basebd.user.dao;

import org.bson.types.ObjectId;
import pillihuaman.com.pe.basebd.config.BaseMongoRepository;
import pillihuaman.com.pe.basebd.user.User;
import pillihuaman.com.pe.basebd.common.MyJsonWebToken;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends BaseMongoRepository<User> {

    List<User> findUserByMail(String mail);

    List<User> findUserName(String mail);

    User saveUser(User request, MyJsonWebToken jwt);

    List<User> findLastUser();

    List<User> findUserById(ObjectId id);

    Optional<User> findByEmail(String mail);

    int getLastIdUser();

    List<User> listByStatus(boolean status);
}
