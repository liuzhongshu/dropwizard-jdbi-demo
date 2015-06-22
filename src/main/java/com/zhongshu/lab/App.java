package com.zhongshu.lab;

import org.skife.jdbi.v2.DBI;

import com.zhongshu.lab.AppConfig;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;

/**
 * Hello world!
 *
 */
public class App extends Application<AppConfig>
{
    public static void main( String[] args ) throws Exception
    {
        new App().run("server","server.yml");
    }

	@Override
	public void run(AppConfig configuration, Environment env) throws Exception {
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(env, configuration.database, "database");
		final DemoDao dao = jdbi.onDemand(DemoDao.class);
		
		env.jersey().register(new DemoResource(dao));
		
	}
}
