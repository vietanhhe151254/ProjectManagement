package com.example.projectmanagement.service;

import com.example.projectmanagement.entities.Status;
import com.example.projectmanagement.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StatusServiceImpl implements StatusService{
    @Autowired
    StatusRepository statusRepository;
    @Override
    public List<Status> listAll() {
        return statusRepository.findAll();
    }


}
