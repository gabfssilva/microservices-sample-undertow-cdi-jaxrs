package com.thedevpiece.mss.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.thedevpiece.mss.producers.qualifiers.InjectableProperties;
import com.thedevpiece.mss.producers.qualifiers.Property;
import com.thedevpiece.mss.util.ObjectMapperFactory;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class ApplicationProducer {
    @Produces
    public ObjectMapper objectMapper() {
        return ObjectMapperFactory.get();
    }

    @Produces
    @ApplicationScoped
    public Datastore datastore(@Property("mongodb.uri") String mongoDbUri) throws Exception {
        MongoClientURI uri = new MongoClientURI(mongoDbUri);
        final MongoClient mongoClient = new MongoClient(uri);

        final Morphia morphia = new Morphia();
        morphia.mapPackage("com.thedevpiece.mss.domain.entities");

        final Datastore datastore = morphia.createDatastore(mongoClient, "mss");
        datastore.ensureIndexes();
        return datastore;
    }

    @Produces
    @Property(value = "")
    public String property(@InjectableProperties Map<String, String> properties, InjectionPoint injectionPoint){
        final Property property = injectionPoint.getAnnotated().getAnnotation(Property.class);
        return properties.get(property.value());
    }

    @ApplicationScoped
    @Produces
    @InjectableProperties
    public Map<String, String> properties() throws IOException {
        Map<String, String> map = new HashMap<>();
        final ResourceBundle bundle = ResourceBundle.getBundle("application");
        bundle.keySet().forEach(key -> map.put(key, bundle.getString(key)));
        return map;
    }
}
