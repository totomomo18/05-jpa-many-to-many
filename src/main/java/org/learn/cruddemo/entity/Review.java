package org.learn.cruddemo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "review")
@NoArgsConstructor
@Setter
@Getter
@ToString
@AllArgsConstructor
public class Review {


    //define fields

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "comment", nullable = true, length = 256)
    private String comment;


    //@Column(name = "course_id", nullable = true)
    //private Integer courseId;


    //define constructor
    public Review(String comment)
    {
        this.comment = comment;
    }


    /*

    public Review()
    {
    }


    //define getters and setters

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public Integer getCourseId()
    {
        return courseId;
    }

    public void setCourseId(Integer courseId)
    {
        this.courseId = courseId;
    }

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("Review{");
        sb.append("id=").append(id);
        sb.append(", comment='").append(comment).append('\'');
        sb.append('}');
        return sb.toString();
    }

     */
}
