/*
 * ServicesRepository.java
 *
 * 29 nov 2022
 */
package it.gov.pagopa.swclient.mil.services.dao;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;

/**
 * 
 * @author Antonio Tarricone
 */
@ApplicationScoped
public class ServicesRepository implements ReactivePanacheMongoRepository<ServicesEntity> {
}
