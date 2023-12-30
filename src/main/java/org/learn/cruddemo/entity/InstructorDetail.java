package org.learn.cruddemo.entity;


import jakarta.persistence.*;
import lombok.*;

//annotate the class as an entity and map to db table
@Entity
@Table(name = "instructor_detail")


@NoArgsConstructor
@Getter
@Setter
//@ToString
@Builder
@AllArgsConstructor


public class InstructorDetail {

    //define the fields
    //annotate the fields with db columns names

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;

    @Column(name = "hobby")
    private String hobby;

    @OneToOne(mappedBy = "instructorDetail", cascade = CascadeType.ALL)
    //@Transient
    private Instructor instructor;

    //create constructors

    public InstructorDetail(String youtubeChannel, String hobby)
    {
        this.youtubeChannel = youtubeChannel;
        this.hobby = hobby;
    }

    /*

    public InstructorDetail()
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

    public String getYoutubeChannel()
    {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel)
    {
        this.youtubeChannel = youtubeChannel;
    }

    public String getHobby()
    {
        return hobby;
    }

    public void setHobby(String hobby)
    {
        this.hobby = hobby;
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


    //generate toString() method

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("InstructorDetail{");

        sb.append("id=").append(id);
        sb.append(", youtubeChannel='").append(youtubeChannel).append('\'');
        sb.append(", hobby='").append(hobby).append('\'');

        //sb.append(", instructor=").append(instructor);
        sb.append('}');
        return sb.toString();
    }
}
