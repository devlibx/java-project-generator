package ${package}.persistence.dao;

import ${package}.persistence.domain.User;
import java.util.Optional;

public interface IUserDao {
    void persist(User user);

    Optional<User> findById(Long id);
}
