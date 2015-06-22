package com.zhongshu.lab;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class AppConfig extends Configuration {
	@Valid
    @NotNull
    public DataSourceFactory database = new DataSourceFactory();

}
