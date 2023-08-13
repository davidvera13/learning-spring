package spring.learning.jdbc.hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="administrator")
@ToString
@Getter @Setter @NoArgsConstructor
public class Administrator {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    public Administrator(
            String firstName,
            String lastName,
            String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
