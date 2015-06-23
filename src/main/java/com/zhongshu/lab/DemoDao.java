package com.zhongshu.lab;

import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.DefaultMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.Transaction;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(DefaultMapper.class)
public abstract class DemoDao {
	
	  @SqlUpdate("insert into demo (id, name) values (:id, :name)")
	  public abstract void create(@Bind("id") int id, @Bind("name") String name);

	  @SqlUpdate("insert into demo (name) values (:name)")
	  public abstract void create(@Bind("name") String name);

	  @SqlUpdate("insert into demo (id,name) values (:s.id, :s.name)")
	  public abstract void create(@BindBean("s") Demo s);
	  
	  @SqlUpdate("update demo set name = :name where id = :id")
	  public abstract void update(@Bind("id") int id, @Bind("name") String name);

	  @SqlUpdate("update demo set name = :s.name where id = :s.id")
	  public abstract void update(@BindBean("s") Demo s);

	  @SqlQuery("select name from demo where id = :id")
	  public abstract String findNameById(@Bind("id") int id);

	  @SqlQuery("select * from demo where id = :id")
	  public abstract Map<String,Object> findById(@Bind("id") int id);
	  
	  @SqlQuery("select * from demo")
	  public abstract List<Map<String,Object>> list();
	  
	  @Transaction
	  public void updateOrInsert(int id, String name) {
		  if (findById(id) == null)
			  create(id,name);
		  else
			  update(id,name);
	  }
}
