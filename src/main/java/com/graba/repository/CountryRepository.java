package com.graba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graba.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

}
