package com.geekle.java.globalsummit.globalsummit.exception;


public class SpeakerNotFoundException  extends RuntimeException {

    public SpeakerNotFoundException(String speakerId) {
        super("Speaker not found with id " + speakerId);
    }
}
