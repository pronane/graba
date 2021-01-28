package com.graba.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.graba.Application;
import com.graba.entity.Customer;
import com.graba.entity.Manager;


@SpringBootTest( classes = Application.class)
public class MultiRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Test
    public void testGetCustomers() {
        List<Customer> target = new ArrayList<>();
        customerRepository.findAll().forEach(target::add);
        Assert.assertEquals(2, target.size());
    }
    @Test
    public void testGetCompanies() {
        List<Manager> target = new ArrayList<>();
        managerRepository.findAll().forEach(target::add);
        Assert.assertEquals(2, target.size());
    }

}