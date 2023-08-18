package spring.learning.jdbc.hibernate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="instructors")
@ToString
@Getter @Setter
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

    // ONE instructor has MANY courses
    // We reference instructor on course table with:
    // -> instructor property
    // -> instructor_id database column
    // note: we don't want to delete course if we delete instructor
    // Exception in thread "main" org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role: spring.learning.jdbc.hibernate.entity.Instructor.courses: could not initialize proxy - no Session
    //	at org.hibernate.collection.spi.AbstractPersistentCollection.throwLazyInitializationException(AbstractPersistentCollection.java:634)
    //	at org.hibernate.collection.spi.AbstractPersistentCollection.withTemporarySessionIfNeeded(AbstractPersistentCollection.java:217)
    //	at org.hibernate.collection.spi.AbstractPersistentCollection.initialize(AbstractPersistentCollection.java:613)
    //	at org.hibernate.collection.spi.AbstractPersistentCollection.read(AbstractPersistentCollection.java:136)
    //	at org.hibernate.collection.spi.PersistentSet.toString(PersistentSet.java:300)
    //	at java.base/java.lang.StringConcatHelper.stringOf(StringConcatHelper.java:453)
    //	at spring.learning.jdbc.hibernate.entity.Instructor.toString(Instructor.java:14)
    //	at java.base/java.lang.String.valueOf(String.java:4215)
    //	at java.base/java.io.PrintStream.println(PrintStream.java:1047)
    //	at spring.learning.jdbc.hibernate.App01Create.main(App01Create.java:70)
    // System.out.println(storedInstructor);
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy="instructor",
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    // @ToString.Exclude // if lazy ...
    private Set<Course> courses;

    public Instructor() {
        this.courses = new HashSet<>();
    }

    public Instructor(String firstName, String lastName, String email) {
        this.courses = new HashSet<>();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // convenient methods
    public void addCourse(Course course) {
        if(courses == null)
            courses = new HashSet<>();
        courses.add(course);
        course.setInstructor(this);
    }

    // to check if works ...
    public void removeCourse(Course course) {
        if(courses != null) {
            courses.remove(course);
        }
    }
}
