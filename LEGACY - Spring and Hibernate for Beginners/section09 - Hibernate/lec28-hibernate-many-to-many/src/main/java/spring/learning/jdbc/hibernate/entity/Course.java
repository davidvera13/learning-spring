package spring.learning.jdbc.hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@ToString
@Getter @Setter @NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    // MANY courses belong to ONE instructor
    // note: do not apply REMOVE on cascade type,
    // we do not plan to remove instructor if we remove a course
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name="instructor_id")
    @ToString.Exclude
    private Instructor instructor;


    @OneToMany(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "course_id")
    @ToString.Exclude
    private Set<Review> reviews;

    public Course(String title) {
        this.title = title;
    }

    public Course(String title, Set<Review> reviews) {
        this.title = title;
        this.reviews = reviews;
    }

    // convenience methods
    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new HashSet<>();
        }
        reviews.add(review);
    }
}
