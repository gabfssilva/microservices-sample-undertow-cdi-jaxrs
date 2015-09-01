package com.thedevpiece.mss.domain.entities;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@Entity
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Double value;

    public Product() {
    }

    private Product(Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setDescription(builder.description);
        setValue(builder.value);
    }

    public static Builder newProduct() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public static final class Builder {
        private String id;
        private String name;
        private String description;
        private Double value;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder value(Double value) {
            this.value = value;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
