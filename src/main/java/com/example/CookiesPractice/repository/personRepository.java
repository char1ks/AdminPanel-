package com.example.CookiesPractice.repository;

import com.example.CookiesPractice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface personRepository extends JpaRepository<Person,Integer> {
}
