package com.thedevpiece.mss.api.v1.endpoint;

import com.thedevpiece.mss.api.v1.converters.ProductConverter;
import com.thedevpiece.mss.api.v1.converters.ProductResourceConverter;
import com.thedevpiece.mss.api.v1.resources.ProductResource;
import com.thedevpiece.mss.domain.ProductRepository;
import com.thedevpiece.mss.domain.entities.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static com.thedevpiece.mss.api.Envelop.newEnvelop;
import static javax.ws.rs.core.Response.created;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@Produces("application/json")
@Path("/v1/products")
@ApplicationScoped
public class ProductEndpoint {
    @Inject
    private ProductRepository repository;

    @Inject
    private ProductResourceConverter productResourceConverter;

    @Inject
    private ProductConverter productConverter;

    @GET
    public Response findAll(){
        final List<Product> products = repository.findAll();
        final List<ProductResource> resources = productResourceConverter.convert(products);
        return ok(newEnvelop().items(resources).build()).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") @NotNull  String id){
        final Product product = repository.find(id);

        if(product == null){
            return status(404).build();
        }

        return ok(newEnvelop().item(productResourceConverter.convert(product)).build()).build();
    }

    @POST
    public Response create(@NotNull @Valid ProductResource productResource) throws URISyntaxException {
        Product product = repository.save(productConverter.convert(productResource));
        return created(new URI("/api/v1/products/" + product.getId())).entity(newEnvelop().item(productResourceConverter.convert(product)).build()).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@NotNull @Valid ProductResource productResource, @PathParam("id") @NotNull  String id) throws URISyntaxException {
        Product product = repository.update(id, productConverter.convert(productResource));
        return ok(newEnvelop().item(productResourceConverter.convert(product)).build()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") @NotNull  String id) throws URISyntaxException {
        Product product = repository.delete(id);

        if(product == null){
            return status(404).build();
        }

        return ok(newEnvelop().item(productResourceConverter.convert(product)).build()).build();
    }
}
