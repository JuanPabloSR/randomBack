package com.mentoring35.listarandom.repository;


import com.mentoring35.listarandom.collection.Random;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RandomRepository extends ReactiveCrudRepository<Random, String> {

}
