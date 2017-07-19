package userDaoServer;

import java.util.ArrayList;
import java.util.List;

import model.UserInfo;
import userDaoService.UserDaoService;
import utils.JdbcUtils;

public class UserDaoServer implements UserDaoService{

	@Override
	public UserInfo check(String userName, String userPassword)
			throws Exception {
		// TODO Auto-generated method stub
		JdbcUtils jdbcUtils=new JdbcUtils();
		jdbcUtils.getConnection();
		String sql="select * from userinfo where username = ? ";
		List<Object> params=new ArrayList<Object>();
		params.add(userName);
		UserInfo user;
		user=jdbcUtils.findSimpleRefResult(sql, params, UserInfo.class);
		return user;
	}

	@Override
	public boolean regist(String userName, String userPassword)
			throws Exception {
		// TODO Auto-generated method stub
		JdbcUtils jdbcUtils=new JdbcUtils();
		jdbcUtils.getConnection();
		String sql = "insert into userinfo values (null, ?, ?,\"\",\"\")";
		List<Object> params = new ArrayList<Object>();
		params.add(userName);
		params.add(userPassword);
		boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
		return flag;
	}

	@Override
	public UserInfo login(String userName, String userPassword) throws Exception {
		// TODO Auto-generated method stub
		JdbcUtils jdbcUtils=new JdbcUtils();
		jdbcUtils.getConnection();
		String sql="select * from userinfo where username = ? and password = ?";
		List<Object> params=new ArrayList<Object>();
		params.add(userName);
		params.add(userPassword);
		UserInfo user;
		user=jdbcUtils.findSimpleRefResult(sql, params, UserInfo.class);
		return user;
	}

	@Override
	public boolean modify(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		JdbcUtils jdbcUtils=new JdbcUtils();
		jdbcUtils.getConnection();
		String sql="update userinfo set sex = ?,urlusericon = ? where username = ?";
		List<Object> params=new ArrayList<Object>();
		params.add(userInfo.getSex());
		params.add(userInfo.getUrlusericon());
		params.add(userInfo.getUsername());
		boolean flag=jdbcUtils.updateByPreparedStatement(sql, params);
		
		return flag;
	}
}
