package com.srp.data.repository;

import org.springframework.stereotype.Repository;

import com.srp.configuration.jpa.SrpRepository;
import com.srp.data.entity.User;

@Repository
public interface UserRepository extends SrpRepository<User, String> {

}
