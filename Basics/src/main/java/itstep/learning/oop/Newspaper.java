package itstep.learning.oop;

import java.time.LocalDate;
public class Newspaper extends Literature {
    public LocalDate releaseDate;

    public Newspaper(LocalDate releaseDate, String title) {
        this.releaseDate = releaseDate;
        super.setTitle(title);
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return super.getTitle() + " --> Date of release: " + this.releaseDate;
    }
}
