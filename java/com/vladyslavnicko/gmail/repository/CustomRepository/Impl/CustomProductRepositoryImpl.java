package com.vladyslavnicko.gmail.repository.CustomRepository.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vladyslavnicko.gmail.exception.ConflictException;
import com.vladyslavnicko.gmail.model.Product;
import com.vladyslavnicko.gmail.repository.CustomRepository.CustomProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class CustomProductRepositoryImpl implements CustomProductRepository {
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	@Transactional
	public Product updateProduct(Product product) {
		if (product == null) {
			return null;
		}
		
		try {
			StringBuilder queryBuilder = new StringBuilder("UPDATE Product p SET ");
            Map<String, Object> parameters = new HashMap<>();
            
            addParameter(queryBuilder, parameters, "name", product.getName());
            addParameter(queryBuilder, parameters, "price", product.getPrice());
            addParameter(queryBuilder, parameters, "category_id", product.getCategory());
            addParameter(queryBuilder, parameters, "description", product.getDescription());
            addParameter(queryBuilder, parameters, "brand_id", product.getBrand());

            if (product.getEditDate() == null) {
                product.setEditDate(new Date());
            }
            addParameter(queryBuilder, parameters, "edit_date", product.getEditDate());

            queryBuilder.append(" WHERE p.id = :id");
            parameters.put("id", product.getId());

            Query query = entityManager.createQuery(queryBuilder.toString());
            for (Map.Entry<String, Object> param : parameters.entrySet()) {
                query.setParameter(param.getKey(), param.getValue());
            }

            query.executeUpdate();
            return product;
            
		} catch (Exception e) {
			throw new ConflictException("Could not edit product");
		}
	}
	
	private void addParameter(StringBuilder queryBuilder, Map<String, Object> parameters, String field, Object value) {
        if (value != null) {
            if (!parameters.isEmpty()) {
                queryBuilder.append(", ");
            }
            queryBuilder.append("p.").append(field).append(" = :").append(field);
            parameters.put(field, value);
        }
    }

}
