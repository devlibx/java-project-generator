package ${package}.persistence.mysql.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import ${package}.persistence.dao.IUserDao;
import ${package}.persistence.mysql.dao.UserDaoImpl;

public class PersistenceMySqlModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IUserDao.class).to(UserDaoImpl.class).in(Scopes.SINGLETON);
    }
}
