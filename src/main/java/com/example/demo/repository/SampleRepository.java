package com.example.demo.repository;

import com.example.demo.Entity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface SampleRepository extends CrudRepository<Entity, String> {
    Entity findByKey(String key);
}
