package com.thedevpiece.mss;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
@ApplicationPath("/api/*")
public class JerseyApp extends ResourceConfig {
    public JerseyApp() {
        packages(true, "com.thedevpiece.mss.api");
    }
}