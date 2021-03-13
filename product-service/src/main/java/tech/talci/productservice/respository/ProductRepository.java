package tech.talci.productservice.respository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.talci.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
