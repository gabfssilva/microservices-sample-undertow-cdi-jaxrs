package com.thedevpiece.mss;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import org.glassfish.jersey.servlet.ServletContainer;
import org.jboss.weld.environment.servlet.Listener;

import javax.servlet.ServletException;

import static io.undertow.servlet.Servlets.listener;
import static io.undertow.servlet.Servlets.servlet;

/**
 * @author Gabriel Francisco - gabfssilva@gmail.com
 */
public class Application {
    private static Undertow server;

    public static void main(String[] args) throws ServletException {
        startContainer(9090);
    }

    public static void stopContainer(){
        server.stop();
    }

    public static void startContainer(int port) throws ServletException {
        DeploymentInfo servletBuilder = Servlets.deployment();

        servletBuilder
                .setClassLoader(Application.class.getClassLoader())
                .setContextPath("/mss")
                .setDeploymentName("mss.war")
                .addListeners(listener(Listener.class))
                .addServlets(servlet("jerseyServlet", ServletContainer.class)
                        .setLoadOnStartup(1)
                        .addInitParam("javax.ws.rs.Application", JerseyApp.class.getName())
                        .addMapping("/api/*"));

        DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
        manager.deploy();
        PathHandler path = Handlers.path(Handlers.redirect("/mss"))
                .addPrefixPath("/mss", manager.start());

        server =
                Undertow
                        .builder()
                        .addHttpListener(port, "localhost")
                        .setHandler(path)
                        .build();

        server.start();
    }
}