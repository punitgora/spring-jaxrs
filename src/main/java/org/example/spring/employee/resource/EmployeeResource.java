package org.example.spring.employee.resource;


import org.example.spring.employee.model.EmployeeModel;
import org.example.spring.employee.model.request.EmployeeCollectionQueryParams;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/employees")
public interface EmployeeResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    Response getEmployees(@BeanParam EmployeeCollectionQueryParams queryParams);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{emp-id}")
    Response getEmployee(@PathParam(value = "emp-id") Long id);

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    Response createEmployee(EmployeeModel employee);

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("/{emp-id}")
    Response updateEmployee(@PathParam(value = "emp-id") Long id, EmployeeModel employee);

    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/{emp-id}")
    Response deleteEmployee(@PathParam(value = "emp-id") Long id);

}
