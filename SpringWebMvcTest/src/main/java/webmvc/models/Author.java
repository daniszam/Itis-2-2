package webmvc.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="author")
public class Author {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String avatarUrl;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private Set<Book> books;

}