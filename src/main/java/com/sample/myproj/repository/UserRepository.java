package com.sample.myproj.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.sample.myproj.entities.User;

@RepositoryRestResource(exported = false)
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	@PreAuthorize("hasRole('ADMIN')")
	@Query(value = "select * from user u where u.id in (select ua.user_id from user_authority ua where ua.authority_id = (select id from authority where name = ?1)) and u.user_name like CONCAT('%', ?2, '%') /*#pageable*/", 
	countQuery = "select count(*) from user u where u.id in (select ua.user_id from user_authority ua where ua.authority_id = (select id from authority where name = ?1)) and u.user_name like CONCAT('%', ?2, '%') /*#pageable*/", 
	nativeQuery = true)
	public Page<User> getUsersByRoleAndNameContaining(String role, String userName, Pageable p);

	
	@PreAuthorize("hasRole('ADMIN')")
	@Query(value = "select * from user u where u.id in (select ua.user_id from user_authority ua where ua.authority_id = (select id from authority where name = ?1)) /*#pageable*/",
	countQuery = "select count(*) from user u where u.id in (select ua.user_id from user_authority ua where ua.authority_id = (select id from authority where name = ?1)) /*#pageable*/",
	nativeQuery = true)
	public Page<User> getUsersByRole(String role, Pageable p);

	
	public java.lang.Iterable<User> findAll();

	User findByUserName(@Param("userName") String userName);

	User findByContact(@Param("contact") String userName);

}
