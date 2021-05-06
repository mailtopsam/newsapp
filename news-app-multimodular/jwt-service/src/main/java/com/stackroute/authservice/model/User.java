package com.stackroute.authservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "User")
public class User {

    @Id
    @Email
    @NotEmpty(message = "Email Id/ User Id  is mandatory")
    @Column(name = "email", length = 50)
    private String id;

    @NotEmpty(message = "Password is mandatory")
    @Size(min = 8, message = "Provide a strong password with min length of 8 characters")
    @Column(name = "password")
    private String password;

    @NotEmpty(message = "FirstName may not be empty")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Column(name = "firstname")
    private String firstname;

    @NotEmpty(message = "LastName may not be empty")
    @Size(min = 2, max = 32, message = "Name must be between 2 and 32 characters long")
    @Column(name = "lastname")
    private String lastname;

    @NotNull(message = "Country cannot be blank")
    @Column(name = "country")
    private String country;

    @NotBlank(message = "language field cannot be null")
    @Column(name = "language")
    private String language;

    @NotBlank(message = "Profile image is mandatory")
    @Column(name = "profile_image")
    private String profileImage;

}
