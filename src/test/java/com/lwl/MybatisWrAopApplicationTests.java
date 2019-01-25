package com.lwl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import com.lwl.domain.User;
import com.lwl.domain.UserBook;
import com.lwl.service.IUserBookService;
import com.lwl.service.IUserService;

/**
 * 
 * 通过AOP实现主从库读写分离有2种实现
 * 1. com.lwl.aop  这种是通过注解的形式，在需要读从库的接口实现上，加上注解，然后springAOP在注解上添加切入点，如果发现方法上有这个注解，则切换到从库
 * 2. com.lwl.aop2 这种方式是通过正则表达式 ，在com.lwl.service.* 方法执行的前后，再根据业务需要判断执行方法是否是查询方法，如果是，则切换到从库
 * 
 * 2种方案对比：
 * 	第一种方案更加精确，在指定的接口上添加，范围小，但是工作量大
 * 	第二种方案则不能精确，要约定高于配置，所有的接口名称的前缀要规定好，但是工作量小，改动范围也小
 * 
 * @author lwl
 * @create 2019年1月24日 下午4:38:58
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisWrAopApplicationTests {

	@Autowired
	private IUserService userService;
	
	@Resource
	private IUserBookService userBookService;
	
	/**
	 * 获取数据，获取的数据是从从库里获取的
	 * @author lwl
	 * @create 2019年1月24日 下午1:13:46
	 */
	@Test
	public void findUser() {
		
		List<User> findUser = userService.findUser();
		if(!CollectionUtils.isEmpty(findUser)) {
			System.out.println();
			System.out.println();
			System.out.println("------------------------------------------");
			for (User user : findUser) {
				System.out.println(user.toString());
			}
			System.out.println("------------------------------------------");
			System.out.println();
			System.out.println();
		}
	}
	
	/**
	 * 添加数据，自动添加到主库
	 * @author lwl
	 * @create 2019年1月24日 下午1:14:08
	 */
	@Test
	public void insert() {
		User user = new User("主库111111","女111111","女王大人11111");
		userService.insertUser(user);
	}

	@Test
	public void del() {
		userService.delUser(11);
	}
	
	@Test
	public void upd() {
		User user = new User("主库222","女","女王大人2222");
		user.setId(1);
		userService.updUser(user);
	}

	@Test
	public void findList() {
		
		List<User> findUser = userService.findList();
		if(!CollectionUtils.isEmpty(findUser)) {
			System.out.println();
			System.out.println();
			System.out.println("------------------------------------------");
			for (User user : findUser) {
				System.out.println(user.toString());
			}
			System.out.println("------------------------------------------");
			System.out.println();
			System.out.println();
		}
	}
	
	@Test
	public void addUserBook() {
		UserBook info = new UserBook("天龙八部3", "金庸", 333);
		userBookService.addUserBook(info);
	}
	
	@Test
	public void findUserBook() {
		List<UserBook> books = userBookService.findUserBook();
		if(!CollectionUtils.isEmpty(books)) {
			System.out.println();
			System.out.println();
			System.out.println("------------------------------------------");
			for (UserBook book : books) {
				System.out.println(book.toString());
			}
			System.out.println("------------------------------------------");
			System.out.println();
			System.out.println();
		}
	}
	
	@Test
	public void getUserBook() {
		List<UserBook> books = userBookService.getUserBook();
		if(!CollectionUtils.isEmpty(books)) {
			System.out.println();
			System.out.println();
			System.out.println("------------------------------------------");
			for (UserBook book : books) {
				System.out.println(book.toString());
			}
			System.out.println("------------------------------------------");
			System.out.println();
			System.out.println();
		}
	}
	
	
}

