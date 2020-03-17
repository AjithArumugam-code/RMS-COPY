package com.project.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Post;
import com.project.domain.PublicPost;

/**
 * Service for processing Public related posts.
 * <p>
 * For a complete reference to Spring JDBC and JdbcTemplate see
 * http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/jdbc.html
 * <p>
 * For transactions, see
 * http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/html/transaction.html
 */
@Service("publicService")
@Transactional
public class PublicService implements GenericService {

	protected static Logger logger = Logger.getLogger("service");

	// We'll be calling SQL statements. SimpleJdbcTemplate is a perfect tool.
	private SimpleJdbcTemplate jdbcTemplate;

	@Resource(name = "bulletinDataSource")
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	public List<Post> getAll() {
		try {
			logger.debug("Retrieving all public posts");

			// Prepare SQL statement
			String sql = "select id, date, message from public_post";

			// Map SQL result to a Java object
			RowMapper<Post> mapper = new RowMapper<Post>() {
				public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
					Post post = new PublicPost();
					post.setId(rs.getLong("id"));
					post.setDate(rs.getDate("date"));
					post.setMessage(rs.getString("message"));
					return post;
				}
			};

			// Run query then return result
			return jdbcTemplate.query(sql, mapper);

		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

}