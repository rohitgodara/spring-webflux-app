package com.catapi.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import com.catapi.entity.Role;

import reactor.core.publisher.Mono;

/**
 * @author rohit.godara
 *
 */
public interface RoleRepository extends ReactiveSortingRepository<Role, UUID> {

	Mono<Role> findByName(String name);

}
