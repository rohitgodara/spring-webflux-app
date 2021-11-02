package com.catapi.repository;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.catapi.entity.Assessments;


@Repository
public interface AssessmentRepository extends ReactiveSortingRepository<Assessments, UUID>{

}
