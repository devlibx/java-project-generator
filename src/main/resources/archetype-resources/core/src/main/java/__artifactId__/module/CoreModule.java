#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.module;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import ${package}.${artifactId}.service.IUserService;
import ${package}.${artifactId}.service.UserService;

public class CoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IUserService.class).to(UserService.class).in(Scopes.SINGLETON);
    }
}
