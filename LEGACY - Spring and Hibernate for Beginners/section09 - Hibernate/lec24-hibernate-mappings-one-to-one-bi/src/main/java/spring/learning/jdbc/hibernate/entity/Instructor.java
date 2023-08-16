package spring.learning.jdbc.hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="instructors")
@ToString
@Getter @Setter @NoArgsConstructor
public class Instructor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // KEY `FK_DETAIL_idx` (`instructor_detail_id`),
    // CONSTRAINT `FK_DETAIL` FOREIGN KEY (`instructor_detail_id`)
    // REFERENCES `instructor_details` (`id`)
    // ON DELETE NO ACTION ON UPDATE NO ACTION
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    @ToString.Exclude
    private InstructorDetail instructorDetail;

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


}
