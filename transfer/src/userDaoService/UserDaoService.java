package userDaoService;

import model.userInfo;

public interface UserDaoService {
	public userInfo check(String userName,String userPassword) throws Exception;
}
