package org.example.spring.employee.service;

import org.example.spring.employee.dao.EmployeeDao;
import org.example.spring.employee.entity.EmployeeEntity;
import org.example.spring.employee.entity.enums.StatusEnum;
import org.example.spring.employee.model.EmployeeModel;
import org.example.spring.employee.model.request.EmployeeCollectionQueryParams;
import org.springframework.core.convert.ConversionService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Named
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private ConversionService conversionService;

    @Inject
    private EmployeeDao employeeDao;

    @Override
    public List<EmployeeModel> getEmployees(EmployeeCollectionQueryParams queryParams) {
        List<EmployeeEntity> employeeEntities = employeeDao.getEmployees(queryParams.getName(), queryParams.getCity(), queryParams.getEmployeeType(), queryParams.getSortingOrder(), queryParams.getColumn(), queryParams.getLimit(), queryParams.getOffset());
        return employeeEntities
                .stream()
                .map(e -> conversionService.convert(e, EmployeeModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeModel getEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeDao.getEmployeeById(id);
        return conversionService.convert(employeeEntity, EmployeeModel.class);
    }

    @Override
    public EmployeeModel createEmployee(EmployeeModel employee) {
        EmployeeEntity employeeEntity = conversionService.convert(employee, EmployeeEntity.class);
        Objects.requireNonNull(employeeEntity);
        long time = System.currentTimeMillis();
        employeeEntity.setTimeCreated(time);
        employeeEntity.setTimeUpdated(time);
        EmployeeEntity savedEntity = employeeDao.saveOrUpdate(employeeEntity);
        return conversionService.convert(savedEntity, EmployeeModel.class);
    }

    @Override
    public void updateEmployee(Long id, EmployeeModel employee) {
        EmployeeEntity employeeEntity = conversionService.convert(employee, EmployeeEntity.class);
        Objects.requireNonNull(employeeEntity);
        employeeEntity.setId(id);
        employeeEntity.setTimeUpdated(System.currentTimeMillis());
        employeeDao.saveOrUpdate(employeeEntity);
    }

    @Override
    public void deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeDao.getEmployeeById(id);
        employeeEntity.setStatus(StatusEnum.INACTIVE);
        employeeEntity.setTimeUpdated(System.currentTimeMillis());
        employeeDao.saveOrUpdate(employeeEntity);
    }
}
