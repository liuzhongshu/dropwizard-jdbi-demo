package com.zhongshu.lab;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/demo")
@Produces(value = MediaType.APPLICATION_JSON)
public class DemoResource {

	private DemoDao demoDao;
	public DemoResource(DemoDao dao) {
		demoDao = dao;
	}

	@GET
    public List<Map<String,Object>> list() {
        return demoDao.list();
    }
	
	@GET
	@Path("/{id:[0-9]+?}")
    public Map getById(@PathParam("id") int id) {
        return demoDao.findById(id);
    }

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    public void create(Demo entity) throws Exception {
		demoDao.create(entity);
    }
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
    public void update(Demo entity) throws Exception {
		demoDao.update(entity);
    }

}
