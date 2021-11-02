package com.catapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.catapi.constants.Message;
import com.catapi.entity.User;
import com.catapi.exception.UserNotFoundException;
import com.catapi.form.UserForm;
import com.catapi.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author rohit.godara
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public Mono<User> register(UserForm userForm) {
		return repository.save(new User(UUID.randomUUID(), userForm.getName(), userForm.getPassword(),
				userForm.getName(), userForm.getUserRole(), true, LocalDate.now(), LocalDate.now(), true));
	}

	public Mono<User> findById(UUID id) {
		return repository.findById(id).doOnSuccess(user -> {
			if (user == null) {
				throw new UserNotFoundException(HttpStatus.NOT_FOUND, Message.NOT_FOUND);
			}
		});
	}

	public Mono<User> findByName(String name) {
		return repository.findByName(name).doOnSuccess(data -> System.out.println("Success : " + data)).log();

	}

	public Flux<List<User>> findAll() {
		return repository.findAll().buffer(2);
	}

	public Mono<Object> findAllUsers(PageRequest pageRequest) {
		return this.repository.findAllBy(pageRequest).collectList().zipWith(this.repository.count())
				.map(t -> new PageImpl<>(t.getT1(), pageRequest, t.getT2()));
	}

}
