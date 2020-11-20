/**
 * 
 */
package com.practice.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.demo.model.User;

/**
 * @author Richa
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email); 
	
	User findByNameAndEmail(String name, String email);

}
