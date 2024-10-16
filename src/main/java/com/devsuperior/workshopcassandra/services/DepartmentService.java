package com.devsuperior.workshopcassandra.services;

import com.devsuperior.workshopcassandra.model.dto.DepartmentDTO;
import com.devsuperior.workshopcassandra.model.entities.Department;
import com.devsuperior.workshopcassandra.repositories.DepartmentRepository;
import com.devsuperior.workshopcassandra.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDTO> findAll() {
        List<Department> list = departmentRepository.findAll();
        return list.stream().map(DepartmentDTO::new).collect(Collectors.toList());
    }

    public DepartmentDTO findById(UUID id) {
        Department entity = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new DepartmentDTO(entity);
    }

    public DepartmentDTO insert(DepartmentDTO dto) {
        Department entity = new Department();
        entity.setId(UUID.randomUUID());
        entity.setName(dto.getName());
        entity = departmentRepository.save(entity);
        return new DepartmentDTO(entity);
    }

    public DepartmentDTO update(UUID id, DepartmentDTO dto) {
        Department entity = departmentRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        entity.setName(dto.getName());
        entity = departmentRepository.save(entity);
        return new DepartmentDTO(entity);
    }

    public void deleteById(UUID id) {
        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found");
        }

        departmentRepository.deleteById(id);
    }

}
