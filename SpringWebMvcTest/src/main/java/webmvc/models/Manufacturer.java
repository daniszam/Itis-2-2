package webmvc.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "manufacturer_jpa")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    @Size(min = 5, max = 15)
    private String name;


    @OneToMany(mappedBy = "manufacturer", fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private Set<Product> products;

}
