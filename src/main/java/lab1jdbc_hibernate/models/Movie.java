package lab1jdbc_hibernate.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;


@Data
@EqualsAndHashCode(exclude = "id")
@Entity(name = "Movies")
@Table(name = "Movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE)
    private Director director;

    private Integer length;

    private Integer year;

    @Column(name = "short_descr")
    private String shortDescr;

    public Movie() {}

    /**
     * Constructor for unique tests
     * @param title
     */
    public Movie(String title) {
        this.title = title;
    }

    public Movie(String title, Director director, int length, int year, String shortDescr) {
        this.title = title;
        this.director = director;
        this.length = length;
        this.year = year;
        this.shortDescr = shortDescr;
    }
}
