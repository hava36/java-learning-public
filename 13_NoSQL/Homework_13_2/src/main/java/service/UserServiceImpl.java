package service;

import dao.Dao;
import model.User;

import java.util.List;

public class UserServiceImpl implements Service<String, User> {

    private Dao<String, User> dao;

    public UserServiceImpl(Dao<String, User> dao) {
        this.dao = dao;
    }

    @Override
    public void add(User user) {
        dao.add(user);
    }

    @Override
    public void update(User user) {
        dao.update(user);
    }

    @Override
    public void delete(User user) {
        dao.delete(user);
    }

    @Override
    public User get(String login) {
        return dao.get(login);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

}
