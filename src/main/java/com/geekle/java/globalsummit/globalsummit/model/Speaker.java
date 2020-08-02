package com.geekle.java.globalsummit.globalsummit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "speakers")

public class Speaker {


    @Id
    private String id;

    @NotBlank
    @Size(max=100)
    private String name;


    @NotNull
    private Date createdAt = new Date();




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }



    public Speaker(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Speaker() {
    }
}
