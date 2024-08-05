package banquemisr.challenge05.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = "USERS")
@Data
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Task> tasks;

    @JsonManagedReference
    @OneToOne(mappedBy = "user")
    private UserDetails userDetails;
}
