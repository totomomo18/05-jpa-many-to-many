package org.learn.cruddemo.dao;

import org.learn.cruddemo.entity.Course;
import org.learn.cruddemo.entity.Instructor;
import org.learn.cruddemo.entity.InstructorDetail;
import org.learn.cruddemo.entity.Student;

import java.util.List;


public interface AppDao {

    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

     void deleteInstructorDetailById(int theId);

    Instructor findInstructorByIdWithCourses(int theId);
    public List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update(Instructor theInstructor);

    void update(Course theCourse);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);

    void save(Student theStudent);

    Course findCourseAndStudentsByCourseId(int theId);

    Student findStudentAndCoursesByStudentId(int theId);

    void update(Student tempStudent);

    //void deleteCourseAndStudentsByCourseId(int theId);
    //void deleteStudentAndCoursesByStudentId(int theId);

    void deleteStudentById(int theId);

}
