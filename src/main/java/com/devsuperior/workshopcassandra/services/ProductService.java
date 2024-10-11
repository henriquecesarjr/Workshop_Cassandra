package com.devsuperior.workshopcassandra.services;

import com.devsuperior.workshopcassandra.model.dto.ProductDTO;
import com.devsuperior.workshopcassandra.model.entities.Product;
import com.devsuperior.workshopcassandra.repositories.ProductRepository;
import com.devsuperior.workshopcassandra.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO findById(UUID id) {
        Product entity = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new ProductDTO(entity);
    }

    public List<ProductDTO> findByDepartment(String department) {
        List<Product> list;
        if ("".equals(department)) {
            list = productRepository.findAll();
        } else {
            list = productRepository.findByDepartment(department);
        }


        return list.stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    public List<ProductDTO> findByDescription(String text) {

        List<Product> list;
        if ("".equals(text)) {
            list = productRepository.findAll();
        } else {
            list = productRepository.findByDescription("%"+text+"%");
        }

        return list.stream().map(ProductDTO::new).collect(Collectors.toList());
    }
}
