#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId};

import com.codahale.metrics.MetricRegistry;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import ${package}.${artifactId}.config.ApplicationConfig;
import ${package}.core.module.CoreModule;
import ${package}.external.module.ExternalServicesModule;
import ${package}.persistence.module.PersistenceModule;
import ${package}.persistence.mysql.module.PersistenceMySqlModule;
import ${package}.resources.UserResource;
import ${package}.resources.proto.UserProtoResource;
import io.dropwizard.setup.Environment;
import io.gitbub.devlibx.easy.helper.ApplicationContext;
import io.gitbub.devlibx.easy.helper.metrics.IMetrics;
import io.github.devlibx.easy.${artifactId}.dropwizard.BaseApplication;
import io.github.devlibx.easy.${artifactId}.dropwizard.healthcheck.ApplicationHealthCheck;
import io.github.devlibx.easy.database.IDatabaseService;
import io.github.devlibx.easy.database.mysql.config.MySqlConfigs;
import io.github.devlibx.easy.database.mysql.module.DatabaseMySQLModule;
import io.github.devlibx.easy.http.module.EasyHttpModule;
import io.github.devlibx.easy.http.util.EasyHttp;
import io.github.devlibx.easy.lock.IDistributedLockService;
import io.github.devlibx.easy.lock.config.LockConfigs;
import io.github.devlibx.easy.metrics.prometheus.PrometheusMetrics;

public class RestApplication extends BaseApplication<ApplicationConfig> {

    public static void main(String[] args) throws Exception {
        new RestApplication().run("server", args[0]);
    }

    /**
     * Enable protocol buffer support
     */
    @Override
    protected boolean enableProtobufSupport() {
        return true;
    }

    @Override
    public void run(ApplicationConfig ${artifactId}licationConfig, Environment environment) throws Exception {
        super.run(${artifactId}licationConfig, environment);

        // Project module
        AbstractModule module = new AbstractModule() {
            @Override
            protected void configure() {
                bind(IMetrics.class).to(PrometheusMetrics.class).in(Scopes.SINGLETON);
                bind(MySqlConfigs.class).toInstance(${artifactId}licationConfig.getMySqlConfigs());
                bind(MetricRegistry.class).toInstance(environment.metrics());
                bind(LockConfigs.class).toInstance(${artifactId}licationConfig.getLocks());
            }
        };

        // Add all other modules
        Injector injector = Guice.createInjector(
                module,
                new CoreModule(),
                new PersistenceModule(),
                new PersistenceMySqlModule(),
                new EasyHttpModule(),
                new ExternalServicesModule(),
                new DatabaseMySQLModule()
        );
        ApplicationContext.setInjector(injector);

        // Setup EasyHttp
        EasyHttp.setup(${artifactId}licationConfig.getEasyHttpConfig());

        IDatabaseService databaseService = injector.getInstance(IDatabaseService.class);
        databaseService.startDatabase();

        // Setup lock service
        IDistributedLockService distributedLockService = injector.getInstance(IDistributedLockService.class);
        distributedLockService.initialize();

        // Register resources
        registerResource(${artifactId}licationConfig, environment);

        // Register prometheus
        registerPrometheus(environment);

        // Setup health checks for ${artifactId}
        injector.getInstance(ApplicationHealthCheck.class).setupHealthChecks(environment);
    }

    private void registerResource(ApplicationConfig ${artifactId}licationConfig, Environment environment) {
        environment.jersey().register(ApplicationContext.getInstance(UserResource.class));
        environment.jersey().register(ApplicationContext.getInstance(UserProtoResource.class));
    }
}
