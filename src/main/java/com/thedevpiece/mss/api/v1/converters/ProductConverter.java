package com.thedevpiece.mss.api.v1.converters;

import com.thedevpiece.mss.api.v1.resources.ProductResource;
import com.thedevpiece.mss.domain.entities.Product;

import javax.enterprise.context.ApplicationScoped;

import static com.thedevpiece.mss.domain.entities.Product.newProduct;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationScoped
public class ProductConverter {

    public Product convert(ProductResource productResource){
        return newProduct()
                    .description(productResource.getDescription())
                    .value(productResource.getValue())
                    .name(productResource.getName())
                .build();
    }
}
