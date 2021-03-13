package tech.talci.productservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.talci.productservice.model.Product;
import tech.talci.productservice.respository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public void createProduct(Product product) {
        this.productRepository.save(product);
    }
}
