package com.team4.service.user;

import com.team4.model.User;
import com.team4.service.IService;

public interface IUserService extends IService<User> {
boolean checkLogin (String username, String password);
}
