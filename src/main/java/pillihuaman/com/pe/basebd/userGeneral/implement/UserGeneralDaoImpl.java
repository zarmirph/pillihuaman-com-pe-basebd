package pillihuaman.com.pe.basebd.userGeneral.implement;

import java.util.List;
import java.util.Objects;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import pillihuaman.com.pe.basebd.security.PasswordUtils;
import pillihuaman.com.pe.basebd.userGeneral.Mapper.MapperUser;
import pillihuaman.com.pe.lib.request.ReqUser;
import pillihuaman.com.pe.lib.response.RespBase;
import pillihuaman.com.pe.lib.response.RespUser;
import pillihuaman.com.pe.basebd.user.User;
import pillihuaman.com.pe.basebd.user.dao.UserRepository;
import pillihuaman.com.pe.basebd.userGeneral.UserGeneralRepositoy;

@Component
public class UserGeneralDaoImpl implements UserGeneralRepositoy {
	@Autowired
	private UserRepository userRepository;

	@Override
	public RespBase<RespUser> getUserByMail(String mail) {

		RespBase<RespUser> respo = new RespBase<RespUser>();
		try {
			List<User> lis = userRepository.findUserByMail(mail);
			RespUser  res = MapperUser.INSTANCE.userToRespUser(lis.stream().findFirst().get());
			respo.setPayload(res);

		} catch (Exception e) {
			respo.getStatus().setSuccess(Boolean.FALSE);
			respo.getStatus().getError().getMessages().add(e.getMessage());
		}

		return respo;
	}

	@Override
	public RespBase<RespUser> getUserByUserName(String username) {
		RespBase<RespUser> respo = new RespBase<RespUser>();

		try {

			List<User> listaUsuario = userRepository.findUserName(username);
			if (listaUsuario != null && listaUsuario.size() > 0) {
				//respo.setPayload(ConvertClass.UserTblToUserDTO(listaUsuario.get(0)));

			}

		} catch (Exception e) {
			respo.getStatus().setSuccess(Boolean.FALSE);
			respo.getStatus().getError().getMessages().add(e.getMessage());
		}
		return respo;
	}

	public int getLastIdUser() {
		int id = 0;
		try {
			MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
			MongoDatabase database = mongoClient.getDatabase("gamachicas");
			MongoCollection<Document> collection = database.getCollection("user");
			Document sort = new Document().append("_id", -1);
			Document lis = collection.find().sort(sort).first();
			if (Objects.nonNull(lis)) {
				id = (int) lis.get("idUser");
			}
			mongoClient.close();
		} catch (MongoException e) {
			id = 0;
		}
		return id;
	}

	@Override
	public RespBase<RespUser> lastUser(ReqUser request) {
		RespBase<RespUser> response = new RespBase<RespUser>();
		List<User> listaUsuario = userRepository.findLastUser();
		if (listaUsuario != null && listaUsuario.size() > 0) {
			//response.setPayload(ConvertClass.UserTblToUserDTO(listaUsuario.get(0)));

		}
		return response;
	}

	@Override
	public RespBase<RespUser> registerUser(ReqUser request) {
		RespBase<RespUser> response = new RespBase<RespUser>();
		try {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			User tbl =null;// new User();
			tbl =null;// ConvertClass.userDtoToUserTbl(request);

			String salt = PasswordUtils.getSalt(30);
			String mySecurePassword = PasswordUtils.generateSecurePassword(request.getPassword(), salt);
			String codeString = bCryptPasswordEncoder.encode(mySecurePassword);

			System.out.println("salt   " + salt);
			System.out.println("Api Password   " + mySecurePassword);
			System.out.println("Password   " + codeString);

			//tbl.setApi_password(mySecurePassword);
			//tbl.setSal_password(salt);
		//	tbl.setPassword(codeString);
			List<User> list = userRepository.findLastUser();
			/*if (list != null && list.size() > 0) {
				tbl.setId_user(list.get(0).getId_user() + 1);
				userRepository.saveUser(tbl);
			} else {
				tbl.setId_user(1);
				userRepository.saveUser(tbl);
			}*/

			response.getStatus().setSuccess(Boolean.TRUE);
			//response.setPayload(new RespUser());
		} catch (Exception e) {

			response.getStatus().setSuccess(Boolean.FALSE);
			throw e;

			// response.getStatus().getError().getMessages().add(e.getMessage());
		}

		return response;
	}

}
