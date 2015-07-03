package com.zhongshu.lab.dao;

import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.DefaultMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.Transaction;
import org.skife.jdbi.v2.sqlobject.customizers.Define;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.stringtemplate.UseStringTemplate3StatementLocator;

import com.zhongshu.lab.model.Post;

@UseStringTemplate3StatementLocator("~")
@RegisterMapper(DefaultMapper.class)
public abstract class PostDao {
	
	  @SqlUpdate("insert into post (id,name,content,create_at,view_count) values (:s.id, :s.name, :s.content, :s.create_at, :s.view_count)")
	  public abstract void create(@BindBean("s") Post s);
	  
	  @SqlUpdate("update post set name = :s.name, content = :s.content, view_count = :s.view_count, create_at = :s.create_at where id = :s.id")
	  public abstract void update(@BindBean("s") Post s);

	  @SqlQuery("select * from post where id = :id")
	  public abstract Map<String,Object> findById(@Bind("id") int id);
	  
	  @SqlQuery("select * from post <where> <order> <page>")
	  public abstract List<Map<String,Object>> list(@Define("where") String where, @Define("order") String order, @Define("page") String page);

	  @SqlQuery("select count(*) from post <where>")
	  public abstract long count(@Define("where") String where);

}
