package com.zhongshu.lab;

import java.util.List;

import javax.ws.rs.core.UriInfo;

import io.dropwizard.jersey.params.IntParam;

public class SqlUtil {
	
	public static String where(UriInfo uriInfo, List<String> fields) {
		
		boolean first = true;
		StringBuffer sb = new StringBuffer();
		for (String key : uriInfo.getQueryParameters().keySet())
		{
			if (fields.contains(key)) {
				if (!first)
					sb.append(" AND ");
				else
					sb.append("WHERE ");
				sb.append(key + "=" + uriInfo.getQueryParameters().getFirst(key));
				first = false;
			}
		}
		return sb.toString();
	}


	public static String order(String field, String asc) {
		if (!field.matches("[a-zA-Z0-9_]+"))
			field = "id";
		
		return "order by " + field + " " + asc;
	}

	public static String limit(IntParam perpage, IntParam page) {
		int limit = perpage.get();
		int offset = page.get() < 1 ? 0 : (page.get() - 1) * limit;
		return "limit " + limit + " offset " + offset;
	}
}
