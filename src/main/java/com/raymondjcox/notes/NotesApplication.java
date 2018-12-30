package com.raymondjcox.notes;

import org.jdbi.v3.core.Jdbi;

import com.raymondjcox.notes.resources.NotesResource;
import com.raymondjcox.notes.db.NoteDAO;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class NotesApplication extends Application<NotesConfiguration> {

    public static void main(final String[] args) throws Exception {
        new NotesApplication().run(args);
    }

    @Override
    public String getName() {
        return "Notes";
    }

    @Override
    public void initialize(final Bootstrap<NotesConfiguration> bootstrap) {
        bootstrap.addBundle(new MigrationsBundle<NotesConfiguration>() {
        	@Override
			public DataSourceFactory getDataSourceFactory(NotesConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
        });
    }

    @Override
    public void run(final NotesConfiguration configuration,
                    final Environment environment) {
    	/*
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
            environment.healthChecks().register("template", healthCheck);
		*/

	    final JdbiFactory factory = new JdbiFactory();
	    final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
	    final NoteDAO notesDAO = jdbi.onDemand(NoteDAO.class);
	    environment.jersey().register(new NotesResource(notesDAO));	
    }
}