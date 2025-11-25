package com.restful_webservices.dto;

import java.util.List;

public class AuthorDto {
    private Integer id;
    private String name;
    private String biography;
    private List<String> bookTitles;

    public AuthorDto() {
    }

    public AuthorDto(String name, String biography) {
        this.name = name;
        this.biography = biography;
    }

    public AuthorDto(String name, String biography, List<String> bookTitles) {
        this.name = name;
        this.biography = biography;
        this.bookTitles = bookTitles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<String> getBookTitles() {
        return bookTitles;
    }

    public void setBookTitles(List<String> bookTitles) {
        this.bookTitles = bookTitles;
    }

    @Override
    public String toString() {
        return "AuthorDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", biography='" + biography + '\'' +
                ", bookTitles=" + bookTitles +
                '}';
    }
}
