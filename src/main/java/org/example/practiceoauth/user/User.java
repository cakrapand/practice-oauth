package org.example.practiceoauth.user;

import jakarta.persistence.*;
import lombok.Data;
import org.example.practiceoauth.task.Task;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String email;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
}
