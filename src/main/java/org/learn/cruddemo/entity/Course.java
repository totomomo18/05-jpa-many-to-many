package org.learn.cruddemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@NoArgsConstructor
@Setter
@Getter
@Builder
@AllArgsConstructor

public class Course {

    //define our fields

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = true, length = 128)
    private String title;


    @ManyToOne(cascade = {CascadeType.PERSIST,
                          CascadeType.MERGE,
                          CascadeType.REFRESH,
                          CascadeType.DETACH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;




    //@Column(name = "instructor_id", nullable = true)
    //private Integer instructorId;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}
    )
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))

    private List<Student> students;

    public Course(String title)
    {
        this.title = title;
    }
/*
    public Course()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Instructor getInstructor()
    {
        return instructor;
    }

    public void setInstructor(Instructor instructor)
    {
        this.instructor = instructor;
    }
*/


    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("Course{");
        sb.append("title='").append(title).append('\'');
        sb.append('}');
        return sb.toString();
    }


    public void addReview(Review theReview)
    {
        if (reviews == null)
        {
            reviews = new ArrayList<>();
        }

        reviews.add(theReview);
    }

    public void addStudent(Student theStudent)
    {
        if (students == null)
        {
            students = new ArrayList<>();
        }
        students.add(theStudent);
    }

}
