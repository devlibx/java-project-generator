#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.base;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import ${package}.${artifactId}.base.PersistenceTestState.PersistenceTestContext;
import ${package}.${artifactId}.module.PersistenceModule;
import io.gitbub.devlibx.easy.helper.ApplicationContext;
import io.gitbub.devlibx.easy.helper.metrics.IMetrics;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class PersistenceBaseTestCase extends TestCase {
    protected PersistenceTestContext ${artifactId}TestContext;
    protected Injector injector;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        ${artifactId}TestContext = PersistenceTestState.instance().getContext();
        ${artifactId}TestContext.setLongs(new HashMap<>());
        ${artifactId}TestContext.setUuids(new HashMap<>());
        ${artifactId}TestContext.setStrings(new HashMap<>());
        setupLogging();
        setupPersistenceTestContext();
        setupGuice();
        setupDatabase();
        bootstrapDatabase();
    }

    protected void setupPersistenceTestContext() {
    }

    private void setupLogging() {
    }

    private void setupGuice() {
        injector = Guice.createInjector(getModuleList());
        ApplicationContext.setInjector(injector);
    }

    protected void setupDatabase() {
    }

    protected void bootstrapDatabase() {
    }

    protected List<AbstractModule> getModuleList() {
        List<AbstractModule> modules = new ArrayList<>();
        modules.add(new PersistenceModule());
        modules.add(new AbstractModule() {
            @Override
            protected void configure() {
                bind(IMetrics.class).to(IMetrics.NoOpMetrics.class);
            }
        });
        return modules;
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }
}
