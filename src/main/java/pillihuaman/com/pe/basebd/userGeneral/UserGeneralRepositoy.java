package pillihuaman.com.pe.basebd.userGeneral;


import pillihuaman.com.pe.lib.request.ReqUser;
import pillihuaman.com.pe.lib.response.RespBase;
import pillihuaman.com.pe.lib.response.RespUser;

public interface UserGeneralRepositoy {
	RespBase<RespUser> getUserByMail(String mail);
	RespBase<RespUser> getUserByUserName(String mail);
	RespBase<RespUser>  lastUser ( ReqUser request);
	RespBase<RespUser>  registerUser ( ReqUser request);

}
