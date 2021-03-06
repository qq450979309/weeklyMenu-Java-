package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.User;
import com.tool.Encryption;
import com.service.UserService;
import com.entity.Character;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao = null;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getStaffByUid(String uid) {
		return userDao.queryStaff(uid);
	}

	@Override
	public User checkPassword(String uid,String upassword) {
		User user = userDao.queryByUid(uid.trim());
		// 不存在用户
		if (user == null)
			return null;
		// 密码相同，使用MD5
		System.out.println("密码验证");
//		if (staff.getSpassword().equals(Encryption.getMD5Encryption(upassword)))
		if (user.getUpassword().equals(upassword))
			return user;

		return null;
	}

	@Override
	public boolean addtUser(User user) {
		boolean flag=false;
		try {
			flag=(userDao.registUser(user)==1)?true:false;
		} catch (Exception e) {
			System.out.println(e);
		}
		return flag;
	}

	@Override
	public boolean ifExistCharacter(Integer userId) {
		boolean flag=false;
		try {
			flag=(userDao.ifExistCharacter(userId)!=0)?true:false;
		} catch (Exception e) {
			System.out.println("“查询是否存在于喜好表”出错了");
		}
		return flag;
	}

	@Override
	public boolean addCharacter(Character character) {
		boolean flag=false;
		try {
			flag=(userDao.insertCharacter(character)==1)?true:false;
		} catch (Exception e) {
			System.out.println(e);
		}
		return flag;
	}

	@Override
	public Character getCharacter(Integer userId) {
		Character character = userDao.getCharacter(userId);
		// 不存在用户
		if (character == null) {
			return null;
		} else {
			return character;
		}
	}

	@Override
	public boolean updateCharacter(Character character) {
		boolean flag=false;
		try {
			flag=(userDao.updateCharacter(character)==1)?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
