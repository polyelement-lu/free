package service;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.free.model.Permission;
import com.free.model.User;
import com.free.service.IUserService;
import com.free.utils.MD5Util;

import base.BaseTest;
import tk.mybatis.mapper.entity.Condition;

public class UserService extends BaseTest{
	@Autowired
	private IUserService userService;

	@Test
	public void testName() throws Exception {
		String ssString="qweasd@zxc_123";
		System.out.println(MD5Util.encrypt(ssString));
		System.out.println(MD5Util.decrypt(ssString));
	}
	@Test
	public void testName1() throws Exception {
		Condition condition = new Condition(User.class);
		condition.createCriteria().andCondition("name=2341 and email='qwer'");
		int deleteByCondition = userService.deleteByCondition(condition);
		System.out.println(deleteByCondition);
	}
	
	@Test
	public void testName4() throws Exception {
		List<Permission> permissionByUserIdTest = userService.getPermissionByUserId(1);
		for (Permission permission : permissionByUserIdTest) {
			System.err.println("ddd:"+permission.getCode());
		}
	}
}
