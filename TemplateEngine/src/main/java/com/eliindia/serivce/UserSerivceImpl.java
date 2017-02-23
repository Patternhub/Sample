package com.eliindia.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eliindia.model.User;
import com.eliindia.repository.UserRepository;

@Component
public class UserSerivceImpl implements UserService {

  /*
   * This is service will take care of controller data and sent data to repository
   */
  UserRepository userRepo;

  @Autowired
  public UserSerivceImpl(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public User saveUser(User user) {
    return userRepo.save(user);
  }

  @Override
  public boolean deleteUser(long id) {
    userRepo.delete(id);
    return true;
  }

  @Override
  public boolean updateUser(User user) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public User getUserInfo(long id) {
    User user = userRepo.findOne(id);
    return user;
  }



}
