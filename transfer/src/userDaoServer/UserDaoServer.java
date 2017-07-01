package userDaoServer;

import java.util.ArrayList;
import java.util.List;

import model.userInfo;
import userDaoService.UserDaoService;
import utils.JdbcUtils;

public class UserDaoServer implements UserDaoService{

	@Override
	public userInfo check(String userName, String userPassword)
			throws Exception {
		// TODO Auto-generated method stub
		JdbcUtils jdbcUtils=new JdbcUtils();
		jdbcUtils.getConnection();
		String sql="select username,userpass from userinfo where username=?";
		List<Object> params=new ArrayList<Object>();
		params.add("userName");
		userInfo user=new userInfo();
		user=jdbcUtils.findSimpleRefResult(sql, params, userInfo.class);
		System.out.println(user.getUsername());
		return user;
	}

}
