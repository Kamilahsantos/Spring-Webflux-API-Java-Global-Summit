package com.geekle.java.globalsummit.globalsummit.repository;

import com.geekle.java.globalsummit.globalsummit.model.Speaker;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepository extends ReactiveMongoRepository<Speaker, String> {


}
