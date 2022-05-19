package com.example.projectmanagement.repository;

import com.example.projectmanagement.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
}
