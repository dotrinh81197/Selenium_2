package com.auto.model;


import lombok.Data;

@Data
public class Repository {

    String repositoryName;

    public Repository() {
        this.repositoryName = "SampleRepository";
    }

    public Repository(String repositoryName) {
        this.repositoryName = repositoryName;
    }

}