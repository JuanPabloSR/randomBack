package com.mentoring35.listarandom.model;

import java.util.List;

public class RequestDTO {

    private String id;
    private List<List<String>> randomList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<List<String>> getRandomList() {
        return randomList;
    }

    public void setRandomList(List<List<String>> randomList) {
        this.randomList = randomList;
    }
}
