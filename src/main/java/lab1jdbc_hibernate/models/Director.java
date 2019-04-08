package lab1jdbc_hibernate.models;

/*import lombok.Data;
import lombok.Getter;
import lombok.Setter;*/

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
//@EqualsAndHashCode(exclude = "id")
@ToString(exclude = "movies")
@Entity(name = "Directors")
public class Director {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "director", fetch = FetchType.EAGER)
    private Set<Movie> movies;

    public Director() {}

    public Director(Integer id) {
        this.id = id;
    }

    public Director(String name) {
        this.name = name;
    }

    public Director(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /*@Override
    public String toString() {
        return "Director{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }*/
}
