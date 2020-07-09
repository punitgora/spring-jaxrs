package org.example.spring.employee.resource;

import org.example.spring.employee.model.EmployeeModel;
import org.example.spring.employee.model.request.EmployeeCollectionQueryParams;
import org.example.spring.employee.service.EmployeeService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import java.util.List;

@Named
public class EmployeeResourceImpl implements EmployeeResource {

    @Inject
    private EmployeeService employeeService;

    @Override
    public Response getEmployees(EmployeeCollectionQueryParams queryParams) {
        List<EmployeeModel> employees = employeeService.getEmployees(queryParams);
        return Response.ok().entity(employees).build();
    }

    @Override
    public Response getEmployee(Long id) {
        EmployeeModel employee = employeeService.getEmployee(id);
        return Response.ok().entity(employee).build();
    }

    @Override
    public Response createEmployee(EmployeeModel employeeRequest) {
        EmployeeModel employee = employeeService.createEmployee(employeeRequest);
        return Response.status(Response.Status.CREATED).entity(employee).build();
    }

    @Override
    public Response updateEmployee(Long id, EmployeeModel employeeRequest) {
        employeeService.updateEmployee(id, employeeRequest);
        return Response.noContent().build();
    }

    @Override
    public Response deleteEmployee(Long id) {
        employeeService.deleteEmployee(id);
        return Response.noContent().build();
    }

}
