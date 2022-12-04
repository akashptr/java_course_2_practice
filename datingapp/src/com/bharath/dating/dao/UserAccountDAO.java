package com.bharath.dating.dao;

import java.util.List;

import com.bharath.dating.model.UserAccount;

public interface UserAccountDAO {

	void save(UserAccount userAccount);
	
	UserAccount findById(int id);
	
	List<UserAccount> findAll();
	
	List<UserAccount> findMatches(int age,String city);
	
	void delete(int id);

}
