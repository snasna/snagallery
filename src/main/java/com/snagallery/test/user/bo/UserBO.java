package com.snagallery.test.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snagallery.test.common.EncryptUtils;
import com.snagallery.test.user.dao.UserDAO;
import com.snagallery.test.user.model.User;

@Service
public class UserBO {

	@Autowired
	private UserDAO userDAO;
	
	public int addUser(String loginId, String password, String name, String email) {
		
		return userDAO.insertUser(loginId, password, name, email);
	}
	
		public boolean isDuplicate(String loginId) {
			
			
			int count = userDAO.selectCountLoginId(loginId);
			if(count == 0) {
				return false;
			} else {
				return true;
			}
		}
		
		public User getUser(String loginId, String password) {
		 	String encryptPassword = EncryptUtils.md5(password);
		 	return userDAO.selectUser(loginId, encryptPassword);
		}
		
		
		public User getUserById(int id) {
			return userDAO.selectUserById(id);
		}
		

	}
