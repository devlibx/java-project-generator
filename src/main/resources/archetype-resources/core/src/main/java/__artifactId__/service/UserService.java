#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.service;

import ${package}.persistence.dao.IUserDao;
import ${package}.persistence.domain.User;

import javax.inject.Inject;

public class UserService implements IUserService {
    private final IUserDao userDao;

    @Inject
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUser(Long id) {
        return userDao.findById(id).orElse(null);
    }

    public void persist(User user) {
        userDao.persist(user);
    }
}
