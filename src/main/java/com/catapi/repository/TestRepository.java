package com.catapi.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.catapi.entity.Test;

import reactor.core.publisher.Mono;

/**
 * @author rohit.godara
 *
 */
@Repository
public interface TestRepository extends ReactiveSortingRepository<Test,UUID> {

	Mono<Test> findByCode(String code);

}
