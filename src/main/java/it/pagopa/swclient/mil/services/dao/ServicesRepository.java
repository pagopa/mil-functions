/*
 * ServicesRepository.java
 *
 * 29 nov 2022
 */
package it.pagopa.swclient.mil.services.dao;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * 
 * @author Antonio Tarricone
 */
@ApplicationScoped
public class ServicesRepository implements ReactivePanacheMongoRepository<ServicesEntity> {
}
