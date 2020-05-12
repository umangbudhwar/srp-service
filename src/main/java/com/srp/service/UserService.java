package com.srp.service;

import com.srp.data.dto.UserDTO;
import com.srp.data.entity.User;

public interface UserService extends BaseService<UserDTO, User, String> {

	User findByUserName(String userName);

}
