package com.graba.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graba.entity.Brand;

public interface BrandService extends JpaRepository<Brand, Long>{

}
