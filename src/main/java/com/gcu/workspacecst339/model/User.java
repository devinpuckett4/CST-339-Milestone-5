package com.gcu.workspacecst339.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("users")
public class User {

    /** Primary key */
    @Id
    private Long id;

    /** Username for login. */
    private String username;

    /** User password  */
    private String password;

    /** Maps to column "first_name". */
    @Column("first_name")
    private String firstName;

    /** Maps to column "last_name". */
    @Column("last_name")
    private String lastName;

    /** Contact email. */
    private String email;

    /** No-arg constructor  */
    public User() {}

    /** Convenience constructor for manual object creation */
    public User(Long id, String username, String password, String firstName, String lastName, String email) {
        this.id = id; this.username = username; this.password = password;
        this.firstName = firstName; this.lastName = lastName; this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}