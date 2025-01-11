package pillihuaman.com.pe.basebd.control.dao;

import pillihuaman.com.pe.basebd.user.User;
import pillihuaman.com.pe.basebd.common.MyJsonWebToken;
import pillihuaman.com.pe.lib.request.ReqControl;
import pillihuaman.com.pe.basebd.config.BaseMongoRepository;
import pillihuaman.com.pe.basebd.control.Control;

import java.util.List;

public interface ControlDAO extends BaseMongoRepository<Control> {

	List<Control> listControl(ReqControl reqControl);
	Control saveControl(Control reqControl, MyJsonWebToken to);
	List<Control> findByUser(User user);

}
