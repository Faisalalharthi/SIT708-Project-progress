package com.sortscript.amdsystem.Models;

public class Recommendation {

    public String country;
    public String institution;
    public String patents;
    public String publications;
    public String quality_of_education;
    public String quality_of_faculty;
    public String score;
    public String world_rank;

    public Recommendation(String country, String institution, String patents, String publications, String quality_of_education, String quality_of_faculty, String score, String world_rank) {
        this.country = country;
        this.institution = institution;
        this.patents = patents;
        this.publications = publications;
        this.quality_of_education = quality_of_education;
        this.quality_of_faculty = quality_of_faculty;
        this.score = score;
        this.world_rank = world_rank;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getPatents() {
        return patents;
    }

    public void setPatents(String patents) {
        this.patents = patents;
    }

    public String getPublications() {
        return publications;
    }

    public void setPublications(String publications) {
        this.publications = publications;
    }

    public String getQuality_of_education() {
        return quality_of_education;
    }

    public void setQuality_of_education(String quality_of_education) {
        this.quality_of_education = quality_of_education;
    }

    public String getQuality_of_faculty() {
        return quality_of_faculty;
    }

    public void setQuality_of_faculty(String quality_of_faculty) {
        this.quality_of_faculty = quality_of_faculty;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getWorld_rank() {
        return world_rank;
    }

    public void setWorld_rank(String world_rank) {
        this.world_rank = world_rank;
    }
}
