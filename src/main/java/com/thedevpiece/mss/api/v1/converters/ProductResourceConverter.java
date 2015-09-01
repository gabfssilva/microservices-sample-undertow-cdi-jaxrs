package com.thedevpiece.mss.api.v1.converters;

import com.thedevpiece.mss.api.v1.resources.ProductResource;
import com.thedevpiece.mss.domain.entities.Product;

import javax.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

import static com.thedevpiece.mss.api.v1.resources.ProductResource.newProductResource;
import static java.util.stream.Collectors.toList;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationScoped
public class ProductResourceConverter {
    public ProductResource convert(Product product){
        return newProductResource()
                    .id(product.getId())
                    .description(product.getDescription())
                    .value(product.getValue())
                    .name(product.getName())
                .build();
    }

    public List<ProductResource> convert(List<Product> products){
        return products.stream().map(this::convert).collect(toList());
    }
}
