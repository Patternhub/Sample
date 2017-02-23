package com.eliindia.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eliindia.model.User;

@Repository
//@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long>{

}
