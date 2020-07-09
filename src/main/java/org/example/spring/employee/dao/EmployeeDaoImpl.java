package org.example.spring.employee.dao;

import org.example.spring.employee.entity.EmployeeEntity;
import org.example.spring.employee.entity.enums.EmployeeTypeEnum;
import org.example.spring.employee.entity.enums.SortingOrderEnum;
import org.springframework.util.StringUtils;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Named
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EmployeeEntity> getEmployees(String name, String city, EmployeeTypeEnum type, SortingOrderEnum order, String column, Integer limit, Integer offset) {
        TypedQuery<EmployeeEntity> query = getCriteriaQuery(name, city, type,
                order, column);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    public EmployeeEntity getEmployeeById(Long id) {
        return entityManager.find(EmployeeEntity.class, id);
    }

    @Override
    public EmployeeEntity saveOrUpdate(EmployeeEntity employeeEntity) {
        return entityManager.merge(employeeEntity);
    }

    private TypedQuery<EmployeeEntity> getCriteriaQuery(String name, String city, EmployeeTypeEnum type, SortingOrderEnum order, String column) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> criteriaQuery = criteriaBuilder.createQuery(EmployeeEntity.class);
        Root<EmployeeEntity> root = criteriaQuery.from(EmployeeEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        List<Consumer<TypedQuery<EmployeeEntity>>> queryConsumers = getTypedQueryConsumers(name,
                city, type, criteriaBuilder, root, predicates);

        // Create Final Query
        criteriaQuery = criteriaQuery.select(root)
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        if (order != null && column != null) {
            if (order == SortingOrderEnum.ASCENDING) {
                criteriaQuery = criteriaQuery
                        .orderBy(criteriaBuilder.asc(root.get(column)));
            } else {
                criteriaQuery = criteriaQuery
                        .orderBy(criteriaBuilder.desc(root.get(column)));
            }
        }

        TypedQuery<EmployeeEntity> query = entityManager.createQuery(criteriaQuery);
        queryConsumers.forEach(c -> c.accept(query));

        return query;
    }

    private <T> List<Consumer<TypedQuery<T>>> getTypedQueryConsumers(String name, String city, EmployeeTypeEnum type, CriteriaBuilder criteriaBuilder, Root<EmployeeEntity> root, List<Predicate> predicates) {
        List<Consumer<TypedQuery<T>>> queryConsumers = new ArrayList<>();

        // Create nameCriteria
        if (!StringUtils.isEmpty(name)) {
            ParameterExpression<String> nameParamExp = criteriaBuilder.parameter(String.class);
            Predicate nameCriteria = criteriaBuilder.equal(root.get("name"), nameParamExp);
            predicates.add(nameCriteria);
            queryConsumers.add(
                    query -> {
                        query.setParameter(nameParamExp, name);
                    });
        }

        // Create cityCriteria
        if (!StringUtils.isEmpty(city)) {
            ParameterExpression<String> cityParamExp = criteriaBuilder.parameter(String.class);
            Predicate cityCriteria = criteriaBuilder.equal(root.get("city"), cityParamExp);
            predicates.add(cityCriteria);
            queryConsumers.add(
                    query -> {
                        query.setParameter(cityParamExp, city);
                    });
        }

        // Create typeCriteria
        if (type != null) {
            ParameterExpression<EmployeeTypeEnum> typeParamExp = criteriaBuilder.parameter(EmployeeTypeEnum.class);
            Predicate typeCriteria = criteriaBuilder.equal(root.get("type"), typeParamExp);
            predicates.add(typeCriteria);
            queryConsumers.add(
                    query -> {
                        query.setParameter(typeParamExp, type);
                    });
        }

        return queryConsumers;
    }
}
