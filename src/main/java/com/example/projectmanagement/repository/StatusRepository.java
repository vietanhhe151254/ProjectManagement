package com.example.projectmanagement.repository;

import com.example.projectmanagement.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository  extends JpaRepository<Status,Integer> {
}
