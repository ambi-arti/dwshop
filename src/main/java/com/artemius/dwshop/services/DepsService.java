package com.artemius.dwshop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.artemius.dwshop.entities.Deps;
import com.artemius.dwshop.repositories.DepsRepository;

@Service
public class DepsService {
    @Autowired
    private final DepsRepository DepsRepository;
    
    DepsService(DepsRepository DepsRepository) {
	this.DepsRepository = DepsRepository;
    }
    
    public void insertNewDept(Deps dept) {
	DepsRepository.save(dept);
    }
    
    public List<Deps> selectAllDeps() {
	return DepsRepository.findAll();
    }
}
