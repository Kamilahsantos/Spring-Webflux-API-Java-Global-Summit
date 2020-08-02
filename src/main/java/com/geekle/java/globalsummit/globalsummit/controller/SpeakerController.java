package com.geekle.java.globalsummit.globalsummit.controller;


import com.geekle.java.globalsummit.globalsummit.exception.SpeakerNotFoundException;
import com.geekle.java.globalsummit.globalsummit.model.Speaker;
import com.geekle.java.globalsummit.globalsummit.repository.SpeakerRepository;
import com.geekle.java.globalsummit.globalsummit.response.ErrorResponse;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class SpeakerController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping("/speakers")
    public Flux<Speaker> getAllSpeakers() {
        return speakerRepository.findAll();
    }

    @PostMapping("/speakers")
    public Mono<Speaker> createSpeaker(@Valid @RequestBody Speaker speaker) {
        return speakerRepository.save(speaker);
    }

    @GetMapping("/speakers/{id}")
    public Mono<ResponseEntity<Speaker>> getSpeakersById(@PathVariable(value = "id") String speakerId) {
        return speakerRepository.findById(speakerId)
                .map(savedSpeaker-> ResponseEntity.ok(savedSpeaker))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/speakers/{id}")
    public Mono<ResponseEntity<Speaker>> updateSpeaker(@PathVariable(value = "id") String speakerId,
                                                       @Valid @RequestBody Speaker speaker) {
        return speakerRepository.findById(speakerId)
                .flatMap(existingSpeaker -> {
                    existingSpeaker.setName(speaker.getName());
                    return speakerRepository.save(existingSpeaker);
                })
                .map(updateSpeaker -> new ResponseEntity<>(updateSpeaker, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/speakers/{id}")
    public Mono<ResponseEntity<Void>> deleteSpeaker(@PathVariable(value = "id") String speakerId) {

        return speakerRepository.findById(speakerId)
                .flatMap(existingSpeaker ->
                        speakerRepository.delete(existingSpeaker)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/stream/speaker", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Speaker> streamAllSpeakers() {
        return speakerRepository.findAll();
    }



    @ExceptionHandler(SpeakerNotFoundException.class)
    public ResponseEntity handleSpeakerNotFoundException(SpeakerNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }


}
