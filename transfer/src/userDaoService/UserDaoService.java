package userDaoService;

import model.userinfo;


public interface UserDaoService {
	public userinfo check(String userName,String userPassword) throws Exception;
	public boolean regist(String userName,String userPassword) throws Exception;
	public userinfo login(String userName,String userPassword) throws Exception;

}
