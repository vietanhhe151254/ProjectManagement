package com.example.projectmanagement.service;

import com.example.projectmanagement.entities.Employee;
import com.example.projectmanagement.entities.Language;

import java.util.List;

public interface LanguageService {
    List<Language> listAll();

    void saveOrUpdateLanguage(Language language);

    void deleteLanguage(Language language);

    Language findById(Integer id);
}
