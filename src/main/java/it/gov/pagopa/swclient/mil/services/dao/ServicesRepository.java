package it.gov.pagopa.swclient.mil.services.dao;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ServicesRepository implements ReactivePanacheMongoRepository<ServicesEntity> {
}
