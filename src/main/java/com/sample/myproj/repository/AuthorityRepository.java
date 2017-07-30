package com.sample.myproj.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sample.myproj.entities.Authority;

@RepositoryRestResource(exported = false)
public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Long> {
}
