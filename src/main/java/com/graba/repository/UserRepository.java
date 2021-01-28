package com.graba.repository;

import javax.persistence.MappedSuperclass;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.graba.entity.User;

@NoRepositoryBean
@MappedSuperclass
public interface UserRepository<T extends User> extends CrudRepository<T, Long> {

    T findByEmail(String email);
}