package com.example.springbatchdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbatchdemo.entity.Book;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {
}
