package com.project.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import com.project.domain.Post;

/**
 * A generic service for handling CRUD operations.
 * <p>
 * The method access-control expressions are specified in this interface.
 */
public interface GenericService {

	/**
	 * Inject the datasource for the bulletingapplication
	 */
	public void setDataSource(DataSource dataSource);

	/**
	 * Retrieves all posts.
	 * <p>
	 * Access-control will be evaluated after this method is invoked. filterObject
	 * refers to the returned object list.
	 */
	@PostFilter("hasPermission(filterObject, 'READ')")
	public List<Post> getAll();

}