package com.zhongshu.lab.resource;

import io.dropwizard.jersey.params.IntParam;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.zhongshu.lab.SqlUtil;
import com.zhongshu.lab.dao.PostDao;
import com.zhongshu.lab.model.Post;

@Path("/posts")
@Produces(value = MediaType.APPLICATION_JSON)
public class PostResource {

	private PostDao demoDao;
	public PostResource(PostDao dao) {
		demoDao = dao;
	}

	@GET
    public List<Map<String,Object>> list(@Context UriInfo uriInfo, @Context HttpServletResponse response,
    		@DefaultValue("id")  @QueryParam("_sortField") String orderBy,
            @DefaultValue("asc") @QueryParam("_sortDir")   String order,
            @DefaultValue("20")  @QueryParam("_perPage")   IntParam perpage,
            @DefaultValue("0")   @QueryParam("_page")      IntParam page) 
    {
		response.setHeader("X-Total-Count", "" + demoDao.count(SqlUtil.where(uriInfo, Arrays.asList("name", "body"))));
        return demoDao.list(SqlUtil.where(uriInfo, Arrays.asList("name", "body")), 
        		SqlUtil.order(orderBy, order), SqlUtil.limit(perpage, page));
    }
	
	@GET
	@Path("/{id:[0-9]+?}")
    public Map getById(@PathParam("id") int id) {
        return demoDao.findById(id);
    }

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    public void create(Post entity) throws Exception {
		demoDao.create(entity);
    }
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id:[0-9]+?}")
    public void update(Post entity) throws Exception {
		demoDao.update(entity);
    }

}
