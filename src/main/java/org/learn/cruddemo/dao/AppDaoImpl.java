package org.learn.cruddemo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.learn.cruddemo.entity.Course;
import org.learn.cruddemo.entity.Instructor;
import org.learn.cruddemo.entity.InstructorDetail;
import org.learn.cruddemo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Slf4j
public class AppDaoImpl implements AppDao {

    //define field for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public AppDaoImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor)
    {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId)
    {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId)
    {
        // retrieve the instructor
        Instructor theInstructor = entityManager.find(Instructor.class, theId);

        if(theInstructor== null)
        {
            log.warn("No instructor found for delete with id: "+theId);
        }
        else
        {

            //get the courses
            List<Course> theCourses = theInstructor.getCourses();

            // break the associations for all the courses for the instructor
            for(Course theCourse : theCourses)
            {
                theCourse.setInstructor(null);
            }

            // delete the instructor
            entityManager.remove(theInstructor);


            // delete the instructor
            entityManager.remove(theInstructor);
        }

    }


    @Override
    public InstructorDetail findInstructorDetailById(int theId)
    {
        return entityManager.find(InstructorDetail.class, theId);
    }

    /**
     * @param theId
     */
    @Override
    public void deleteInstructorDetailById(int theId)
    {
        InstructorDetail theInstructorDetail = entityManager.find(InstructorDetail.class, theId);
        if(theInstructorDetail== null)
        {
            log.warn("No instructor detail found for delete with id: "+theId);
        }
        else
        {
            entityManager.remove(theInstructorDetail);
        }

    }

    /**
     * @param theId
     * @return
     */
    @Override
    @Transactional
    public Instructor findInstructorByIdWithCourses(int theId)
    {
        Instructor theInstructor = entityManager.find(Instructor.class, theId);
        List<Course> theCourses = theInstructor.getCourses();


        /*

        //String dataQuery = " from Instructor i left join fetch Course c where i.id = :id";
        //String dataQuery = " from Instructor i left join Course c on i.id = c.instructorId where i.id = :id";
        //String dataQuery = " from Instructor i left join fetch Course c on i.id = c.instructor.id where i.id = :id";
        //String dataQuery = " from Instructor i left join fetch Course c where i.id = :id";
        //String dataQuery = "select i from Instructor i left join fetch i.courses c where i.id = :id";
        String dataQuery = " from Instructor i left join i.courses c where i.id = :id";

        TypedQuery<Instructor> query = entityManager.createQuery(dataQuery,Instructor.class);

        query.setParameter("id", theId);

        Instructor theInstructor =  query.getSingleResult();

         */

        log.warn("Dao tempInstructor: " + theInstructor);
        log.warn("Dao the associated InstructorDetail only: " + theInstructor.getInstructorDetail());

        try
        {
            List courses = theInstructor.getCourses();
            log.warn("Dao the associated Courses only: " + courses);
        }
        catch(Exception e)
        {
            log.warn("Dao no associated Courses found for course with id: "+theId,e);
        }



        return theInstructor;
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId)
    {
       TypedQuery<Course> query = entityManager.createQuery(" from Course c where c.instructor.id = :id",Course.class);
       query.setParameter("id", theId);

       List<Course> theCourses =    query.getResultList();

       return theCourses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId)
    {

        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i " +
                                                                         " left join fetch i.instructorDetail id " +
                                                                         " left join fetch i.courses c " +
                                                                         " where i.id = :data"
                                                                         ,Instructor.class);

        /*
        TypedQuery<Instructor> query = entityManager.createQuery(" from Instructor i  " +
                                                                 " left join i.courses c " +
                                                                 " where i.id = :data"
                                                                 ,Instructor.class);

         */


        query.setParameter("data", theId);

        Instructor instructor =  query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor theInstructor)
    {
        entityManager.merge(theInstructor);
    }

    /**
     * @param theCourse
     */
    @Override
    @Transactional
    public void update(Course theCourse)
    {
        entityManager.merge(theCourse);
    }

    /**
     * @param theId
     * @return
     */
    @Override

    public Course findCourseById(int theId)
    {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId)
    {
        // retrieve the course
        Course theCourse = entityManager.find(Course.class, theId);

        if(theCourse== null)
        {
            log.warn("No course found for delete with id: " + theId);
        }
        else
        {
            // delete the course
            entityManager.remove(theCourse);
        }

    }

    /**
     * @param theCourse
     */
    @Override
    @Transactional
    public void save(Course theCourse)
    {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId)
    {
         //create the query
        TypedQuery<Course> query = entityManager.createQuery(" from Course c" +
                                                             " join fetch c.reviews" +
                                                             " where c.id = :id"
                                                             ,Course.class);
        query.setParameter("id", theId);

        //Execute the query
        Course course = null;
        try
        {
            course = query.getSingleResult();
        } catch (NoResultException e)
        {
            course = null;
        }

        return course;
    }

    @Override
    @Transactional
    public void save(Student theStudent)
    {
        entityManager.persist(theStudent);
    }


    @Override
    public Course findCourseAndStudentsByCourseId(int theId)
    {
        // create the query
        TypedQuery<Course> query = entityManager.createQuery(" from Course c" +
                                                             " join fetch c.students" +
                                                             " where c.id = :id"
                                                            ,Course.class);
        query.setParameter("id", theId);

        //Execute the query
        Course course = null;
        try
        {
            course = query.getSingleResult();
        }
        catch (NoResultException e)
        {
            course = null;
        }

        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId)
    {

        //Create the query
        TypedQuery<Student> query = entityManager.createQuery(" from Student s" +
                        " join fetch s.courses c" +
                        " where s.id = :id"
                        , Student.class);

        query.setParameter("id", theId);

        //Execute the query
        Student student = null;
        try
        {
            student = query.getSingleResult();
        }
        catch (NoResultException e)
        {
            student = null;
        }
        return student;


    }

    @Override
    @Transactional
    public void update(Student tempStudent)
    {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId)
    {
        Student theStudent = entityManager.find(Student.class, theId);
        if(theStudent== null)
        {
            log.warn("No student found for delete with id: " + theId);
        }
        else
        {
            // delete the student
            entityManager.remove(theStudent);
        }

    }

/*
    @Override
    @Transactional
    public void deleteCourseAndStudentsByCourseId(int theId)
    {
        Course theCourse = entityManager.find(Course.class, theId);
        List<Student> theStudents = theCourse.getStudents();
        for(Student theStudent : theStudents)
        {
            theStudent.getCourses().remove(theCourse);
        }
        entityManager.remove(theCourse);
    }



    @Override
    @Transactional
    public void deleteStudentAndCoursesByStudentId(int theId)
    {
        Student theStudent = entityManager.find(Student.class, theId);
        List<Course> theCourses = theStudent.getCourses();
        for(Course theCourse : theCourses)
        {
            theCourse.getStudents().remove(theStudent);
        }
        entityManager.remove(theStudent);
    }
*/

}
