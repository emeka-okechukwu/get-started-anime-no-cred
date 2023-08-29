package com.emekaokechukwu.getstartedanime.model;

import java.util.List;

public class Movie {
    private int id;
    private String title;
    private String synopsis;
    private String year;
    private List<String> genres;
    private List<String> cast;
    private double rating;
    private int runtime;
    private String posterImage;
    private String backdropImage;
    private String link;

    public Movie(int id, String title, String synopsis, String year, List<String> genres, List<String> cast, double rating, int runtime, String posterImage, String backdropImage, String link) {
        this.id = id;
        this.title = title;
        this.synopsis = synopsis;
        this.year = year;
        this.genres = genres;
        this.cast = cast;
        this.rating = rating;
        this.runtime = runtime;
        this.posterImage = posterImage;
        this.backdropImage = backdropImage;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getBackdropImage() {
        return backdropImage;
    }

    public void setBackdropImage(String backdropImage) {
        this.backdropImage = backdropImage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", year='" + year + '\'' +
                ", genres=" + genres +
                ", cast=" + cast +
                ", rating=" + rating +
                ", runtime=" + runtime +
                ", posterImage='" + posterImage + '\'' +
                ", backdropImage='" + backdropImage + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}