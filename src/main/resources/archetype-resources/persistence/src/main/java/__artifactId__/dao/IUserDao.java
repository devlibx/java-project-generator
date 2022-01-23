#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.dao;

import ${package}.${artifactId}.domain.User;
import java.util.Optional;

public interface IUserDao {
    void persist(User user);

    Optional<User> findById(Long id);
}
