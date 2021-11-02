package com.catapi.repository;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.catapi.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author rohit.godara
 *
 */
@Repository
public interface UserRepository extends ReactiveSortingRepository<User, UUID> {

	Mono<User> findByName(String name);

	Flux<User> findAllBy(Pageable pageable);

	Mono<User> findByUserName(String userName);

}
