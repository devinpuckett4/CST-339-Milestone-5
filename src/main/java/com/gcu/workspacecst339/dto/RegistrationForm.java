package com.gcu.workspacecst339.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistrationForm {
    @NotBlank(message="{NotBlank.registrationForm.firstName}")
    private String firstName;

    @NotBlank(message="{NotBlank.registrationForm.lastName}")
    private String lastName;

    @Email(message="{Email.registrationForm.email}")
    private String email;

    @Size(min=4, message="{Size.registrationForm.username}")
    private String username;

    @Size(min=6, message="{Size.registrationForm.password}")
    private String password;

    private String confirmPassword;

    public String getFirstName(){return firstName;}
    public void setFirstName(String v){firstName=v;}
    public String getLastName(){return lastName;}
    public void setLastName(String v){lastName=v;}
    public String getEmail(){return email;}
    public void setEmail(String v){email=v;}
    public String getUsername(){return username;}
    public void setUsername(String v){username=v;}
    public String getPassword(){return password;}
    public void setPassword(String v){password=v;}
    public String getConfirmPassword(){return confirmPassword;}
    public void setConfirmPassword(String v){confirmPassword=v;}
}