package userDaoService;

import model.UserInfo;


public interface UserDaoService {
	public UserInfo check(String userName,String userPassword) throws Exception;
	public boolean regist(String userName,String userPassword) throws Exception;
	public UserInfo login(String userName,String userPassword) throws Exception;

}
