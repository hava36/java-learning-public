package main.service;

import java.util.List;
import java.util.Optional;

public interface Service<Entity> {

    Entity add(Entity v);
    Optional<Entity> delete(long id);
    void deleteAll();
    Optional<Entity> findById(long id);
    List<Entity> getAll();
    Entity update(Entity v);
    List<Entity> update(List<Entity> v);

}
