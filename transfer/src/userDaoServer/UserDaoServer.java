package userDaoServer;

import java.util.ArrayList;
import java.util.List;

import model.userinfo;
import userDaoService.UserDaoService;
import utils.JdbcUtils;

public class UserDaoServer implements UserDaoService{

	@Override
	public userinfo check(String userName, String userPassword)
			throws Exception {
		// TODO Auto-generated method stub
		JdbcUtils jdbcUtils=new JdbcUtils();
		jdbcUtils.getConnection();
		String sql="select * from userinfo where username = ? ";
		List<Object> params=new ArrayList<Object>();
		params.add(userName);
		userinfo user;
		user=jdbcUtils.findSimpleRefResult(sql, params, userinfo.class);
		return user;
	}

	@Override
	public boolean regist(String userName, String userPassword)
			throws Exception {
		// TODO Auto-generated method stub
		JdbcUtils jdbcUtils=new JdbcUtils();
		jdbcUtils.getConnection();
		String sql = "insert into userinfo values (null, ?, ?)";
		List<Object> params = new ArrayList<Object>();
		params.add(userName);
		params.add(userPassword);
		boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
		return flag;
	}

	@Override
	public userinfo login(String userName, String userPassword) throws Exception {
		// TODO Auto-generated method stub
		JdbcUtils jdbcUtils=new JdbcUtils();
		jdbcUtils.getConnection();
		String sql="select * from userinfo where username = ? and userpass = ?";
		List<Object> params=new ArrayList<Object>();
		params.add(userName);
		params.add(userPassword);
		userinfo user;
		user=jdbcUtils.findSimpleRefResult(sql, params, userinfo.class);
		return user;
	}
}
