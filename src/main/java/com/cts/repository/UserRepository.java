package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cts.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUserName(String username);
	public User findByUserId(int userId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE user SET password=?1 WHERE user_name=?2",nativeQuery=true)
	public int changePassword(String password,String username);
}
