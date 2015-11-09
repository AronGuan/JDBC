package pers.aron;

import org.junit.Test;

public class TestJDBC {

	JdbcDao dao = new JdbcDao();
	
/*	@Test
	public void insertUser(){
		User user = new User(3,"stell","f");
		User user1 = dao.insertUser(user);
		System.out.println(user1);
	}*/
	
	@Test
	public void updateUser(){
		User user = new User(3,"jonn","f");
		dao.updateUser(user);
	}
	
	@Test
	public void deleteUser(){
		dao.deleteUser(3);
	}
	
	@Test
	public void getUserByID(){
		System.out.println(dao.getUserById(2));
	}
	
	@Test
	public void getAllUser(){
		System.out.println(dao.getAllUsers());
	}
}
