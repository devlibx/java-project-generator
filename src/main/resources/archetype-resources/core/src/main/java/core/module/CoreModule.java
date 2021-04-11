package ${package}.core.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import ${package}.core.service.IUserService;
import ${package}.core.service.UserService;

public class CoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IUserService.class).to(UserService.class).in(Scopes.SINGLETON);
    }
}
