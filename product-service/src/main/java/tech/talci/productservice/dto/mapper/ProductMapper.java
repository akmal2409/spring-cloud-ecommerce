package tech.talci.productservice.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.talci.productservice.dto.ProductDto;
import tech.talci.productservice.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto mapToDto(Product product);

    @Mapping(target = "id", ignore = true)
    Product mapToProduct(ProductDto productDto);
}
