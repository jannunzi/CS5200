package com.atc.siterra.bup.meta.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MetadataService {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("SQLServer2");
	EntityManager em = null;
	public TableMetadata getTableMetadata(String tableName) {
		TableMetadata metadata = new TableMetadata();
		
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		metadata = em.find(TableMetadata.class, tableName);
		
		em.getTransaction().commit();
		em.close();
		
		return metadata;
	}
}
