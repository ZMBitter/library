package com.zm.dao;

import java.util.ArrayList;
import java.util.List;

import com.zm.bean.User;

public interface UserDAO {
  //查看用户信息
  List<User> userList = new ArrayList<User>();	
  //登陆。根据用户名和密码查询信息
  User loginUser(String username,String password);
  //根据用户名查询用户信息
  List<User> getUserByName(String username);
  //向用户表插入数据
  boolean addUser(User user);
}
