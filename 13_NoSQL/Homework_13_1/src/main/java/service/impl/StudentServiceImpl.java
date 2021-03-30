package service.impl;

import dao.CourseDao;
import dao.StudentDao;
import dao.impl.redis.CourseDaoRedisImpl;
import dao.impl.redis.StudentDaoRedisImpl;
import model.Student;
import service.StudentService;
import util.DbConnector;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    private CourseDao courseDao;
    private DbConnector dbConnector;

    public StudentServiceImpl(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
        this.studentDao = new StudentDaoRedisImpl(dbConnector);
        this.courseDao = new CourseDaoRedisImpl(dbConnector);
    }

    @Override
    public void add(Student student) {
        dbConnector.beginTransaction();
        try {
            studentDao.add(student);
            student.getHomeworks().forEach((course, homeworks) -> {
                courseDao.add(course);
            });
            dbConnector.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            dbConnector.rollBackTransaction();
        }
    }

    @Override
    public Student get(Integer id) {
        return studentDao.get(id);
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = studentDao.getAll();
        students.forEach(student -> {

        });
        return studentDao.getAll();
    }

    @Override
    public void remove(Integer id) {
        dbConnector.beginTransaction();
        try {
            studentDao.remove(id);
            dbConnector.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            dbConnector.rollBackTransaction();
        }
    }

}
