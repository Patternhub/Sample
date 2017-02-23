package com.eliindia.serivce;

import org.springframework.stereotype.Service;

import com.eliindia.model.User;


@Service
public interface UserService {
  public User saveUser(User user);

  public boolean deleteUser(long id);

  public boolean updateUser(User user);

  public User getUserInfo(long id);


}
