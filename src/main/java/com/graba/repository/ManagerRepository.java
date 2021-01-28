package com.graba.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.graba.entity.Manager;

@Repository
@Transactional
public interface ManagerRepository extends UserRepository<Manager>{

}
