package webmvc.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="book")
@Entity
@Data
public class Book implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String name;
    @Column
    private String genre;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id")
    private Author author;
}
