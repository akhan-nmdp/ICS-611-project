package com.baeldung.spring.config;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebApplicationInitializer implements WebApplicationInitializer
{

    /**
     * Register and configure all Servlet container components necessary to power the web application.
     */
    @Override
    public void onStartup(final ServletContext sc) throws ServletException {
        System.out.println("MyWebApplicationInitializer.onStartup()");

        // Create the 'root' Spring application context
        final AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.scan("com.baeldung.spring.config");
        // root.getEnvironment().setDefaultProfiles("embedded");

        sc.addListener(new ContextLoaderListener(root));

        DispatcherServlet dv = new DispatcherServlet(root);
        
        final ServletRegistration.Dynamic appServlet = sc.addServlet("mvc",dv);
        appServlet.setLoadOnStartup(1);
        final Set<String> mappingConflicts = appServlet.addMapping("/");
        if (!mappingConflicts.isEmpty()) {
            throw new IllegalStateException("'appServlet' could not be mapped to '/' due " + "to an existing mapping. This is a known issue under Tomcat versions " + "<= 7.0.14; see https://issues.apache.org/bugzilla/show_bug.cgi?id=51278");
        }
    }

}
