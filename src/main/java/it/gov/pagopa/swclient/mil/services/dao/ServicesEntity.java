package it.gov.pagopa.swclient.mil.services.dao;

import org.bson.codecs.pojo.annotations.BsonId;

import io.quarkus.mongodb.panache.common.MongoEntity;
import it.gov.pagopa.swclient.mil.dto.Channel;
import it.gov.pagopa.swclient.mil.services.dto.Services;

@MongoEntity(collection = "services")
public class ServicesEntity {
	@BsonId
	public Channel channel;

	public Services services;
}
