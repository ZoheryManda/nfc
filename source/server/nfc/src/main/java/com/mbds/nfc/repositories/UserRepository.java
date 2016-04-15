package com.mbds.nfc.repositories;

import com.mbds.nfc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findByUserName(String userName);

    User findByUserNameAndPassword(String userName, String password);

}
