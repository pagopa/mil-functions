package it.gov.pagopa.swclient.mil.services.dao;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;

@ApplicationScoped
public class ServicesRepository implements ReactivePanacheMongoRepository<ServicesEntity> {
}
