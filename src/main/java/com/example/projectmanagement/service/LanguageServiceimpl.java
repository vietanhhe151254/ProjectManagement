package com.example.projectmanagement.service;

import com.example.projectmanagement.entities.Employee;
import com.example.projectmanagement.entities.Language;
import com.example.projectmanagement.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceimpl implements LanguageService {
    @Autowired
    LanguageRepository languageRepository;
    @Override
    public List<Language> listAll() {
        return languageRepository.findAll();
    }

    @Override
    public void saveOrUpdateLanguage(Language language) {
        languageRepository.save(language);
    }

    @Override
    public void deleteLanguage(Language language) {
        languageRepository.delete(language);
    }

    @Override
    public Language findById(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()) {
            return new Language();
        }
        return optionalLanguage.get();
    }
}
