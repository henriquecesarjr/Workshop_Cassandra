package com.devsuperior.workshopcassandra.controllers;

import com.devsuperior.workshopcassandra.model.dto.DepartmentDTO;
import com.devsuperior.workshopcassandra.services.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> findAll() {
        List<DepartmentDTO> list = departmentService.findAll();
        return ResponseEntity.ok(list);
    }
}
