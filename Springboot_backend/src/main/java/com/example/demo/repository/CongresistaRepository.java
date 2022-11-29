package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Congresista;

@Repository
public interface CongresistaRepository extends JpaRepository<Congresista, Long> {

}
