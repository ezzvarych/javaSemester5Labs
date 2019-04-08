package lab1jdbc_hibernate.services;

import lab1jdbc_hibernate.models.Director;

public interface DirectorService extends AbstractService<Director> {
    Director getByName(String name);
}
