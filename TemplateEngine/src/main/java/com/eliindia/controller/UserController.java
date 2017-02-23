package com.eliindia.controller;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eliindia.model.User;
import com.eliindia.serivce.UserService;

@RestController
@RequestMapping("/userService")
public class UserController {
  /*
   * this Controller will take the person information from the request
   */
  UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping(value = "/addUser", method = RequestMethod.POST)
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    if (null != user){
      user.setUserId(MDC.get("userName"));
      userService.saveUser(user);
    }
    return new ResponseEntity<User>(user, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/getUserInfo/{id}", method = RequestMethod.GET)
  public ResponseEntity<Object> getUser(@PathVariable("id") long id) {
    User user = userService.getUserInfo(id);
    if (null != user) {
      return new ResponseEntity(user, HttpStatus.OK);
    } else {
      return new ResponseEntity("User Not Found", HttpStatus.OK);
    }

  }

  @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
    User user = userService.getUserInfo(id);
    if (null != user) {
      userService.deleteUser(id);
      return new ResponseEntity<String>("User Deleted Successfully", HttpStatus.GONE);
    } else {
      return new ResponseEntity<String>("User Not Found", HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(value = "/updateUserInfo", method = RequestMethod.PUT)
  public ResponseEntity<Object> updateUserInfo(@RequestBody User user) {
    User existingUser = userService.getUserInfo(user.getId());
    if (existingUser == null) {
      return new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND);
    } else {
      User updatedUser=userService.saveUser(user);
      return new ResponseEntity(updatedUser, HttpStatus.OK);
    }
  }


}
