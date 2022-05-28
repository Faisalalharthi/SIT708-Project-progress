package com.sortscript.amdsystem;

public class Recommendation_Model {

    String Key, country, faculty, institute, patent, publication, quality, rank, score;

    public Recommendation_Model() {
    }

    public Recommendation_Model(String key, String country, String faculty, String institute, String patent, String publication, String quality, String rank, String score) {
        Key = key;
        this.country = country;
        this.faculty = faculty;
        this.institute = institute;
        this.patent = patent;
        this.publication = publication;
        this.quality = quality;
        this.rank = rank;
        this.score = score;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getPatent() {
        return patent;
    }

    public void setPatent(String patent) {
        this.patent = patent;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
