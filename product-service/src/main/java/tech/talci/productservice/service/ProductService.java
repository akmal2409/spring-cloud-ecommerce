package tech.talci.productservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.talci.productservice.dto.ProductDto;
import tech.talci.productservice.dto.mapper.ProductMapper;
import tech.talci.productservice.model.Product;
import tech.talci.productservice.respository.ProductRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public void createProduct(ProductDto productDto) {
        Product convertedProduct = productMapper.mapToProduct(productDto);

        Product savedProduct = productRepository.save(convertedProduct);
        log.debug("Product with ID " + savedProduct.getId() + " was created");
    }
}
