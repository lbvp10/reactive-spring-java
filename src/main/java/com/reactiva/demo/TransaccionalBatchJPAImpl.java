package com.reactiva.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class TransaccionalBatchJPAImpl<T> implements TransaccionalBatch<T> {

	@PersistenceContext
	private EntityManager entityManager;
	
	private int batchSize = 10_000;
	
	@Override
	public void saveAll(List<T> entities) {
		 int size = entities.size();
	        
	        for (int i = 0; i < size; i += batchSize) {
	                int toIndex = i + (((i + batchSize) < size) ? batchSize : size - i);
	                processSaveBatch(entities.subList(i, toIndex));
	                entityManager.flush();
	                entityManager.clear();
	        }
		
	}

	@Override
	public void processSaveBatch(List<T> subList) {
		subList.forEach(t->{
    		entityManager.persist(t);

    	});
		
	}






}
