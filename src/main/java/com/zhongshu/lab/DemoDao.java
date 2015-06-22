package com.zhongshu.lab;

import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.DefaultMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.Transaction;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(DefaultMapper.class)
public abstract class DemoDao {
	
	  @SqlUpdate("create table demod (id int primary key, name varchar(100))")
	  public abstract void createTable();
	  
	  @SqlUpdate("insert into demod (id, name) values (:id, :name)")
	  public abstract void insert(@Bind("id") int id, @Bind("name") String name);

	  @SqlQuery("select name from demo where id = :id")
	  public abstract String findNameById(@Bind("id") int id);

	  @SqlQuery("select * from demo where id = :id")
	  public abstract Map<String,Object> findById(@Bind("id") int id);
	  
	  @SqlQuery("select * from demo")
	  public abstract List<Map<String,Object>> list();
	  
	  @Transaction
	  public void updateOrInsert(int id, String name) {
		  
	  }
}
