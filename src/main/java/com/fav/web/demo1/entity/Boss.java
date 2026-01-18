package com.fav.web.demo1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "boss")
@NoArgsConstructor
public class Boss {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "id")
    private Long id;

    @Column(name = "first_name")
    @NotNull(message = "First name is required")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last name is required")
    private String lastName;

    @Column(name = "email")
    @NotNull(message = "Email is required")
    private String email;

    @Column(name = "password")
    private Integer password;

    public Boss(String firstName, String lastName, String email, Integer password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @OneToMany(mappedBy = "boss", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Workers> workers = new java.util.ArrayList<>();

    public void addWorker(Workers worker){
        workers.add(worker);
        worker.setBoss(this);
    }
    public void removeWorker(Workers worker){
        workers.remove(worker);
        worker.setBoss(null);
    }

}
