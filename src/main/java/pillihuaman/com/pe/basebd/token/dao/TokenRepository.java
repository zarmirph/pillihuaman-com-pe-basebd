package pillihuaman.com.pe.basebd.token.dao;

import pillihuaman.com.pe.basebd.config.BaseMongoRepository;
import pillihuaman.com.pe.basebd.token.Token;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends BaseMongoRepository<Token> {

    List<Token> findAllValidTokenByUser(Object id);

    Optional<Token> findByToken(String token);
}
