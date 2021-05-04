package com.example.onlinebookstore.repository;

import com.example.onlinebookstore.model.dto.LoginDto;
import com.example.onlinebookstore.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	//retrieve a user object from the database by username
	User findByUsername(String username);

//	LoginDto saveLoginDto (User user);
}
