package service.impl;

import dao.CourseDao;
import dao.impl.redis.CourseDaoRedisImpl;
import model.Course;
import service.CourseService;
import util.DbConnector;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;
    private DbConnector dbConnector;

    public CourseServiceImpl(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
        this.courseDao = new CourseDaoRedisImpl(dbConnector);
    }

    @Override
    public void add(Course course) {
        dbConnector.beginTransaction();
        try {
            courseDao.add(course);
            dbConnector.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            dbConnector.rollBackTransaction();
        }
    }

    @Override
    public Course get(Integer id) {
        return courseDao.get(id);
    }

    @Override
    public List<Course> getAll() {
        return courseDao.getAll();
    }

    @Override
    public void remove(Integer id) {
        dbConnector.beginTransaction();
        try {
            courseDao.remove(id);
            dbConnector.commitTransaction();
        } catch (Exception exception) {
            exception.printStackTrace();
            dbConnector.rollBackTransaction();
        }
    }

}
