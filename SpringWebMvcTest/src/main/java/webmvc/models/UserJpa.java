package webmvc.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "user_jpa")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id = -1;

    @NotNull
    @Column(unique = true)
    @Size(min = 5, max = 15)
    private String userName;

    @NotNull
    @Email(message = "email address is not valid")
    @Column
    private String email;


    @NotNull
    @Column
    private String password;

}

