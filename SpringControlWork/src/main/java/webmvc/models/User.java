package webmvc.models;

import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class User {

    @NotNull
    @Size(min = 5, max = 15)
    private String userName;

    @NotNull
    @Email(message = "email address is not valid")
    private String email;

    @NotNull
    private Gender gender;

    @NotNull
    private Boolean emailSubscription;

    @NotNull
    private String password;

    private String country;

}

