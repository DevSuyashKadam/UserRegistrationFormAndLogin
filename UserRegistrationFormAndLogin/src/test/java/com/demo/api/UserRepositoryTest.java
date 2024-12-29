package com.demo.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.demo.api.model.User;
import com.demo.api.repo.UserRegistrationandloginRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace =Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRegistrationandloginRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		User user=new User();
		user.setEmail("Lisakelly.com");
		user.setPassword("ftff@11515");
		user.setFirstName("Lissa");
		user.setLastName("Kelly");
		User savedUser=repo.save(user);
		
		User existUser=entityManager.find(User.class, savedUser.getId());
		
		assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
	}
	
	
	@Test
	public void testFindByUerByEmail() {
		
		String email="abcsuyashkadammiraj83@gmail.com";
		User user=repo.findByEmail(email);
		assertThat(user).isNotNull();
	}
}
