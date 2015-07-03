package com.zhongshu.lab;

import org.skife.jdbi.v2.DBI;

import com.zhongshu.lab.AppConfig;
import com.zhongshu.lab.dao.PostDao;
import com.zhongshu.lab.resource.PostResource;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Hello world!
 *
 */
public class App extends Application<AppConfig>
{
    public static void main( String[] args ) throws Exception
    {
    	if (args.length == 0)
    		new App().run("server", "server.yml");
    	else
    		new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
    	bootstrap.addBundle(new AssetsBundle("/assets/", "/"));  
	    bootstrap.addBundle(new MigrationsBundle<AppConfig>() {
	        @Override
	        public DataSourceFactory getDataSourceFactory(AppConfig configuration) {
	            return configuration.database;
	       }
	    });
    }
    
	@Override
	public void run(AppConfig configuration, Environment env) throws Exception {
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(env, configuration.database, "database");
		final PostDao dao = jdbi.onDemand(PostDao.class);
		
		env.jersey().register(new PostResource(dao));
		
	}
}
