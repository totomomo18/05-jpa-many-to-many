package org.learn.cruddemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

//annotate the class as an entity and map to db table
@Entity
@Table(name = "instructor")


@NoArgsConstructor
@Setter
@Getter
//@ToString
@Builder
@AllArgsConstructor


public class Instructor {

    //define the fields
    //annotate the fields with db columns names

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;




    @OneToMany(mappedBy = "instructor", cascade = {CascadeType.PERSIST,
                                                     CascadeType.MERGE,
                                                     CascadeType.REFRESH,
                                                     CascadeType.DETACH}

    //,fetch = FetchType.EAGER
    ,fetch = FetchType.LAZY
    )

    private List<Course> courses;





/*
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
                                                   CascadeType.MERGE,
                                                   CascadeType.REFRESH,
                                                   CascadeType.DETACH})
    //@JoinColumn(name = "instructor_id",referencedColumnName = "id")
    @JoinColumn(name = "instructor_id")
    private List<Course> courses;
*/




    //create constructors
    public Instructor(String firstName, String lastName, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

/*

    public Instructor()
    {
    }

    //generate getters and setters methods

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail()
    {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail)
    {
        this.instructorDetail = instructorDetail;
    }

 */

    //add convenience methods for bi-directional relationships
    public void add(Course tempCourse)
    {

        if (courses == null)
        {
            courses = new ArrayList<>();
        }
        courses.add(tempCourse);

        tempCourse.setInstructor(this);

    }


    //generate toString() method
    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("Instructor{");

        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        //sb.append(", instructorDetail=").append(instructorDetail);


        sb.append('}');
        return sb.toString();
    }




}
