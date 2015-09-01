package com.thedevpiece.mss.domain;

import com.thedevpiece.mss.domain.entities.Product;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationScoped
public class ProductRepository {
    @Inject
    private Datastore datastore;

    public Product save(Product product){
        final Key<Product> productKey = datastore.save(product);
        product.setId((String) productKey.getId());
        return product;
    }

    public Product find(String id){
        return datastore.createQuery(Product.class).filter("_id ==", new ObjectId(id)).get();
    }

    public Product delete(String id){
        return datastore.findAndDelete(datastore.createQuery(Product.class).filter("_id ==", new ObjectId(id)));
    }

    public List<Product> findAll(){
        return datastore.find(Product.class).asList();
    }

    public Product update(String id, Product product){
        final UpdateOperations<Product> updateOperations = datastore.createUpdateOperations(Product.class).set("name", product.getName()).set("description", product.getDescription()).set("value", product.getValue());
        final Query<Product> query = datastore.createQuery(Product.class).filter("_id ==", new ObjectId(id));
        datastore.update(query, updateOperations);
        return query.get();
    }
}
