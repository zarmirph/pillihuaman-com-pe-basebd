package pillihuaman.com.pe.basebd.system.dao;

import pillihuaman.com.pe.basebd.common.MyJsonWebToken;
import pillihuaman.com.pe.basebd.config.BaseMongoRepository;
import pillihuaman.com.pe.basebd.system.System;

import java.util.List;

public interface SystemDAO extends BaseMongoRepository<System> {

	List<System> listSystem(int page, int pageSize ,System req);
	System saveSystem(System req, MyJsonWebToken to);
	System systemById(Object id);
	boolean deleteSystem(Object id,MyJsonWebToken jwt);
}
