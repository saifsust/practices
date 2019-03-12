package org.woadec.materialdesign.recycleviewTest;

public class Movie {

    private long movieId;
    private String movieTitle;
    private String movieLink;
    private String description;
    private String year;


    public Movie() {
    }

    public Movie(String movieTitle, String movieLink, String year,String description) {
        this.movieTitle = movieTitle;
        this.movieLink = movieLink;
        this.year = year;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }


    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieLink() {
        return movieLink;
    }

    public void setMovieLink(String movieLink) {
        this.movieLink = movieLink;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieTitle='" + movieTitle + '\'' +
                ", movieLink='" + movieLink + '\'' +
                ", description='" + description + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
