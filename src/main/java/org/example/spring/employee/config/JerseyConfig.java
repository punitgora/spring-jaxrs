package org.example.spring.employee.config;

import org.example.spring.employee.resource.EmployeeResource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.inject.Named;

@Named
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(EmployeeResource.class);
    }
}
