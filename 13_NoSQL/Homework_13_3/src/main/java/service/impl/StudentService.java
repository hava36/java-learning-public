package service.impl;

import dao.Dao;
import dao.searcher.SearchHandler;
import java.util.List;
import model.Student;
import service.Service;

public class StudentService implements Service<Student> {

  private final Dao<Student> studentDao;

  public StudentService(Dao<Student> studentDao) {
    this.studentDao = studentDao;
  }

  @Override
  public void add(Student student) {
    studentDao.add(student);
  }

  @Override
  public void addAll(List<Student> students) {
    studentDao.addAll(students);
  }

  @Override
  public List<Student> readAll() {
    return studentDao.readAll();
  }

  @Override
  public List<Student> read(SearchHandler<Student> handler) {
    return studentDao.read(handler);
  }

}
