package com.myapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapp.modelo.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}
