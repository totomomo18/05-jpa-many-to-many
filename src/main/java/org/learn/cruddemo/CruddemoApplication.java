package org.learn.cruddemo;


import lombok.extern.slf4j.Slf4j;
import org.learn.cruddemo.dao.AppDao;
import org.learn.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@Slf4j
public class CruddemoApplication {


    public static void main(String[] args)
    {
        SpringApplication.run(CruddemoApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(AppDao appDao)
    {
        log.warn("Start commandLineRunner ...");

        return runner -> {


            //createCourseAndStudents(appDao);
            //findCourseAndStudents(appDao);
            //findStudentAndCourses(appDao);
            //addMoreCoursesForStudent(appDao);
            //deleteCourse(appDao);
            deleteStudent(appDao);

        };


    }

    private void deleteStudent(AppDao appDao)
    {
    log.warn("Start deleteStudent...");
    int theId = 1;
    log.warn("delete Student with id: " + theId);
    appDao.deleteStudentById(theId);
    log.warn("End deleteStudent...");

    }

/*
    private void deleteStudentAndCoursesByStudentId(AppDao appDao)
    {
        log.warn("Start deleteStudentAndCoursesByStudentId...");
        int theId = 2;
        log.warn("delete Student with id: " + theId);
        appDao.deleteStudentAndCoursesByStudentId(theId);
        log.warn("End deleteStudentAndCoursesByStudentId...");
    }

 */
/*
    private void deleteCourseAndStudentsByCourseId(AppDao appDao)
    {
        log.warn("Start deleteCourseAndStudentsByCourseId...");
        int theId = 10;
        log.warn("delete Course with id: " + theId);
        appDao.deleteCourseAndStudentsByCourseId(theId);
        log.warn("End deleteCourseAndStudentsByCourseId...");
    }

 */

    private void findStudentAndCourses(AppDao appDao)
    {
    log.warn("Start findStudentAndCourses...");

    int theId = 2;

    log.warn("find Student with id: " + theId);

    Student tempStudent = appDao.findStudentAndCoursesByStudentId(theId);

    log.warn("Loaded student: " + tempStudent);
    log.warn("Courses: " + tempStudent.getCourses());

    log.warn("End findStudentAndCourses...");

    }

    private void findCourseAndStudents(AppDao appDao)
    {

    log.warn("Start findCourseAndStudents...");

    int theId = 10;

    log.warn("find Course with id: " + theId);
    Course tempCourse = appDao.findCourseAndStudentsByCourseId(theId);

    log.warn("Loaded course: " + tempCourse);
    log.warn("Students: " + tempCourse.getStudents());

    log.warn("End findCourseAndStudents...");

    }

    private void addMoreCoursesForStudent(AppDao appDao)
    {
        log.warn("Start addMoreCoursesForStudent...");

         int theId = 2;

        //find  a student
        log.warn("find Student with id: " + theId);
        Student tempStudent = appDao.findStudentAndCoursesByStudentId(theId);

        //create more courses
        Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
        Course tempCourse2 = new Course("Atari 2600 - Game Development");

        //add course to the student
        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        log.warn("Updating student: " + tempStudent);
        log.warn("Courses: " + tempStudent.getCourses());

        //update the student
        appDao.update(tempStudent);

        log.warn("Done!");

        log.warn("End addMoreCoursesForStudent...");

    }


    private void createCourseAndStudents(AppDao appDao)
    {
     log.warn("Start createCourseAndStudents...");

     //create a course

     //Course tempCourse = new Course(""Pacman - How to Score One Million Points"");

     Course tempCourse = Course.builder().title("Pacman - How to Score One Million Points").build();

     //create students

     //Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
     //Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");


     Student tempStudent1 =Student.builder().firstName("John").lastName("Doe").email("john@luv2code.com").build();
     Student tempStudent2 =Student.builder().firstName("Mary").lastName("Public").email("mary@luv2code.com").build();

     //add students to the course
     tempCourse.addStudent(tempStudent1);
     tempCourse.addStudent(tempStudent2);

     //save the course and the associated students

     log.warn("Saving the course:" + tempCourse);
     log.warn("associated students: " + tempCourse.getStudents());

     appDao.save(tempCourse);

     log.warn("Done!");

     log.warn("End createCourseAndStudents...");

    }


    private void deleteCourseAndReviews(AppDao appDao)
    {
        log.warn("Start deleteCourseAndReviews...");

		int theId = 10;

        log.warn("Deleting the course id: " + theId);

        appDao.deleteCourseById(theId);

        log.warn("Done!");

        log.warn("End deleteCourseAndReviews...");
    }

    private void findCourseAndReviewsByCourseId(AppDao appDao)
    {
        log.warn("Start findCourseAndReviewsByCourseId...");

        int theId = 10;

        log.warn("Finding the course id: " + theId);

        Course theCourse = appDao.findCourseAndReviewsByCourseId(theId);

        if (theCourse == null)
        {
            log.warn("No course found with id: " + theId);
        } else
        {
            log.warn("Course: " + theCourse);
            log.warn("Course reviews: " + theCourse.getReviews());
        }


        log.warn("Done!");

        log.warn("End findCourseAndReviewsByCourseId...");
    }


    private void createCourseAndReviews(AppDao appDao)
    {
        log.warn("Start createCourseAndReviews...");

        //create a course
        Course tempCourse = new Course("Pacman - How To Score One Million Points");

        //add some reviews
        tempCourse.addReview(new Review("Great course ... loved it!"));
        tempCourse.addReview(new Review("Cool course, job well done!"));
        tempCourse.addReview(new Review("What a dumb course, You are an idiot!"));

        //save the course ... and leverage the cascade all
        log.warn("Saving the course...");
        log.warn("Course: " + tempCourse);
        log.warn("Course reviews: " + tempCourse.getReviews());

        appDao.save(tempCourse);

        //Course tempCourse2=new Course("Test course");
        //appDao.save(tempCourse2);

        log.warn("Done!");

        log.warn("End createCourseAndReviews...");

    }

    private void deleteCourse(AppDao appDao)
    {
        log.warn("Start deleteCourse...");
        int theId = 10;

        log.warn("Finding the course id: " + theId);


        log.warn("Deleting course id: " + theId);
        appDao.deleteCourseById(theId);

        log.warn("Done!");
        log.warn("End deleteCourse...");
    }

    private void updateCourse(AppDao appDao)
    {
        log.warn("Start updateCourse...");
        int theId = 10;

        log.warn("Finding the course id: " + theId);

        Course tempCourse = appDao.findCourseById(theId);


        if (tempCourse == null)
        {
            log.warn("Can't find the course with id: " + theId);

        } else
        {
            log.warn("Updating course id: " + theId);
            tempCourse.setTitle("Enjoy the Simple Things");
            appDao.update(tempCourse);


        }

        log.warn("Done!");
        log.warn("End updateCourse...");
    }

    private void updateInstructor(AppDao appDao)
    {
        log.warn("Start updateInstructor...");
        int theId = 1;

        log.warn("Finding the instructor id: " + theId);

        Instructor tempInstructor = appDao.findInstructorById(theId);


        if (tempInstructor == null)
        {
            log.warn("Can't find the instructor with id: " + theId);

        } else
        {
            log.warn("Updating instructor id: " + theId);
            tempInstructor.setLastName("TESTER");
            appDao.update(tempInstructor);


        }

        log.warn("Done!");
        log.warn("End updateInstructor...");
    }

    private void findInstructorWithCoursesJoinFetch(AppDao appDao)
    {
        log.warn("Start findInstructorWithCoursesJoinFetch...");
        int theId = 1;

        log.warn("Finding the instructor id: " + theId);

        Instructor tempInstructor = appDao.findInstructorByIdJoinFetch(theId);


        if (tempInstructor == null)
        {
            log.warn("Can't find the instructor with id: " + theId);

        } else
        {


            log.warn("tempInstructor: " + tempInstructor);
            log.warn("the associated InstructorDetail only: " + tempInstructor.getInstructorDetail());

            try
            {
                List courses = tempInstructor.getCourses();
                log.warn("the associated Courses only: " + courses);
            } catch (Exception e)
            {
                log.warn("No associated Courses found for course with id: " + theId);
            }


        }

        log.warn("Done!");

        log.warn("End findInstructorWithCoursesJoinFetch...");

    }


    private void findInstructorWithCourses(AppDao appDao)
    {
        log.warn("Start testInstructorWithCourses...");

        int theId = 1;

        log.warn("Finding the instructor id: " + theId);
        Instructor tempInstructor = appDao.findInstructorById(theId);
        //Instructor tempInstructor=appDao.findInstructorByIdWithCourses(theId);

        if (tempInstructor == null)
        {
            log.warn("Can't find the instructor with id: " + theId);

        } else
        {

            List<Course> courses = appDao.findCoursesByInstructorId(theId);

            tempInstructor.setCourses(courses);

            log.warn("tempInstructor: " + tempInstructor);
            log.warn("the associated InstructorDetail only: " + tempInstructor.getInstructorDetail());

            try
            {
                //List courses = tempInstructor.getCourses();
                log.warn("the associated Courses only: " + courses);
            } catch (Exception e)
            {
                log.warn("No associated Courses found for course with id: " + theId);
            }


        }

        log.warn("Done!");

        log.warn("End testInstructorWithCourses...");

    }


    private void createInstructorWithCourses(AppDao appDao)
    {

        log.warn("Start createInstructorWithCourses...");

		/*
		Instructor tempInstructor = new Instructor("Susan",
				                                   "Public",
				                                   "susan.public@luv2code.com");

        */


        Instructor tempInstructor = Instructor.builder().firstName("Susan")
                .lastName("Public")
                .email("susan.public@luv2code.com")
                .build();


        //create the instructor detail


/*
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com",
																	 "Video Games!");

*/

        InstructorDetail tempInstructorDetail = InstructorDetail.builder()
                .youtubeChannel("http://www.youtube.com")
                .hobby("Video Games")
                .build();


        //associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        //create some courses
        Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
        Course tempCourse2 = new Course("The Pinball Masterclass");

        //add courses to instructor
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);


        //save the instructor
        //
        // Note this will also save the instructor detail object
        // because the CascadeType.ALL
        //
        log.warn("Before Saving the instructor: " + tempInstructor);
        log.warn("Before Saving the instructor the courses: " + tempInstructor.getCourses());
        appDao.save(tempInstructor);
        log.warn("After Saving the instructor: " + tempInstructor);
        log.warn("After Saving the instructor the courses: " + tempInstructor.getCourses());

        log.warn("Done!");

        log.warn("End createInstructorWithCourses...");
    }


    private void deleteInstructorDetail(AppDao appDao)
    {
        log.warn("Start deleteInstructorDetail...");

        int theId = 1;

        log.warn("delete instructorDetail id: " + theId);

        appDao.deleteInstructorDetailById(theId);

        log.warn("Done!");

        log.warn("End deleteInstructorDetail...");

    }

    private void findInstructorDetail(AppDao appDao)
    {
        log.warn("Start findInstructorDetail...");
        int theId = 2;

        log.warn("find InstructorDetail id: " + theId);

        InstructorDetail tempInstructorDetail = appDao.findInstructorDetailById(theId);

        if (tempInstructorDetail == null)
        {
            log.warn("Can't find the instructorDetail with id: " + theId);
        } else
        {
            //print the instructorDetail
            log.warn("tempInstructorDetail: " + tempInstructorDetail);

            //print the associated Instructor
            log.warn("tempInstructorDetail.getInstructor(): " + tempInstructorDetail.getInstructor());
        }

        log.warn("Done");
        log.warn("End findInstructorDetail...");
    }

    private void deleteInstructor(AppDao appDao)
    {
        log.warn("Start deleteInstructor...");

        int theId = 1;

        log.warn("delete instructor id: " + theId);

        appDao.deleteInstructorById(theId);

        log.warn("Done!");

        log.warn("End deleteInstructor...");
    }

    private void findInstructor(AppDao appDao)
    {
        log.warn("Start findInstructor...");

        int theId = 1;

        log.warn("find Instructor id: " + theId);
        Instructor tempInstructor = appDao.findInstructorById(theId);
        if (tempInstructor == null)
        {
            log.warn("Can't find the instructor with id: " + theId);

        } else
        {
            log.warn("tempInstructor: " + tempInstructor);
            log.warn("the associated InstructorDetail only: " + tempInstructor.getInstructorDetail());
            log.warn("the associated Courses only: " + tempInstructor.getCourses());
        }

        log.warn("End findInstructor...");
    }

    private void createInstructors(AppDao appDao)
    {
        log.warn("Start createInstructor ...");


        //create the instructors

		/*

		Instructor tempInstructor1 = new Instructor("Chad",
				                                   "Darby",
				                                   "darby@luv2code.com");

       */


        Instructor tempInstructor1 = Instructor.builder().firstName("Chad")
                .lastName("Darby")
                .email("darby@example.com")
                .build();


        //create the instructor detail

		/*

		InstructorDetail tempInstructorDetail1 = new InstructorDetail("http://www.luv2code.com/youtube",
																	 "Luv 2 code !!!");
         */

        InstructorDetail tempInstructorDetail1 = InstructorDetail.builder()
                .youtubeChannel("http://www.luv2code.com/youtube")
                .hobby("Luv 2 code!!!")
                .build();


        //associate the objects
        tempInstructor1.setInstructorDetail(tempInstructorDetail1);

        //save the instructor
        //
        // Note this will also save the instructor detail object
        // because the CascadeType.ALL
        //
        log.warn("Before Saving the instructor1: " + tempInstructor1);
        appDao.save(tempInstructor1);
        log.warn("After Saving the instructor1: " + tempInstructor1);


		/*
		Instructor tempInstructor2 = new Instructor("Madhu",
				"Patel",
				"madue@example.com");
        */


        Instructor tempInstructor2 = Instructor.builder().firstName("Madhu")
                .lastName("Patel")
                .email("madue@example.com")
                .build();


        //create the instructor detail

		/*
		InstructorDetail tempInstructorDetail2 = new InstructorDetail("http://www.luv2code.com/youtube",
																	 "Luv 2 code !!!");
        */


        InstructorDetail tempInstructorDetail2 = InstructorDetail.builder()
                .youtubeChannel("http://www.luv2code.com/youtube")
                .hobby("Play Guitar!!!")
                .build();

        //associate the objects
        tempInstructor2.setInstructorDetail(tempInstructorDetail2);

        //save the instructor
        //
        // Note this will also save the instructor detail object
        // because the CascadeType.ALL
        //
        log.warn("Before Saving the instructo2: " + tempInstructor2);
        appDao.save(tempInstructor2);
        log.warn("After Saving the instructor2: " + tempInstructor2);

        log.warn("Done!");

        log.warn("End createInstructor ...");

    }

}
