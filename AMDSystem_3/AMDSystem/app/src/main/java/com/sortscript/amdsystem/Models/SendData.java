package com.sortscript.amdsystem.Models;

public class SendData {
    String country;
    String quality;
    double publications;
    int Score;
    int faculty_quality;
    int patent;

    public SendData() {
    }

    public SendData(String country, String quality, double publications, int score, int faculty_quality, int patent) {
        this.country = country;
        this.quality = quality;
        this.publications = publications;
        Score = score;
        this.faculty_quality = faculty_quality;
        this.patent = patent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public double getPublications() {
        return publications;
    }

    public void setPublications(double publications) {
        this.publications = publications;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getFaculty_quality() {
        return faculty_quality;
    }

    public void setFaculty_quality(int faculty_quality) {
        this.faculty_quality = faculty_quality;
    }

    public int getPatent() {
        return patent;
    }

    public void setPatent(int patent) {
        this.patent = patent;
    }
}
