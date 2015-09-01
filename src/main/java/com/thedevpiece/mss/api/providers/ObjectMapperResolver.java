package com.thedevpiece.mss.api.providers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thedevpiece.mss.util.ObjectMapperFactory;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class ObjectMapperResolver implements ContextResolver<ObjectMapper> {
    @Override
    public ObjectMapper getContext(Class<?> type) {
        return ObjectMapperFactory.get();
    }
}