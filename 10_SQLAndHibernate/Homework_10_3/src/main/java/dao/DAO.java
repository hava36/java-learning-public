package dao;

import com.sun.istack.NotNull;

import java.util.List;

public interface DAO<Entity, Key> {

    void create(@NotNull Entity entity);
    Entity read(@NotNull Key key);
    List<Entity> readAll();
    void update(@NotNull Entity entity);
    void delete(@NotNull Entity entity);

}
