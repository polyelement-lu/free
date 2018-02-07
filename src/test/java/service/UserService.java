package service;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.free.common.BaseQuery;
import com.free.model.User;
import com.free.query.UserQuery;
import com.free.service.IUserService;

import base.BaseTest;
import tk.mybatis.mapper.entity.Condition;

public class UserService extends BaseTest{
	@Autowired
	private IUserService userService;
	@Test
	public void testName() throws Exception {
		List<Map<String, Object>> userInfo = userService.getUserInfo();
		for (Map<String, Object> map : userInfo) {
			System.out.println("测试:"+map.get("name"));
		}
//		List<User> all = userService.getAll();
//		for (User user : all) {
//			System.out.println("测试:"+user.getName());
//		}
	}
	@Test
	public void testName1() throws Exception {
		Condition condition = new Condition(User.class);
		condition.createCriteria().andCondition("name=2341 and email='qwer'");
		int deleteByCondition = userService.deleteByCondition(condition);
		System.out.println(deleteByCondition);
	}
}
