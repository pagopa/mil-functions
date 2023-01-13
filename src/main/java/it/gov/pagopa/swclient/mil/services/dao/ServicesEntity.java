/*
 * ServicesEntity.java
 *
 * 29 nov 2022
 */
package it.gov.pagopa.swclient.mil.services.dao;

import org.bson.codecs.pojo.annotations.BsonId;

import io.quarkus.mongodb.panache.common.MongoEntity;
import it.gov.pagopa.swclient.mil.services.bean.Services;

/**
 * 
 * @author Antonio Tarricone
 */
@MongoEntity(database = "mil", collection = "services")
public class ServicesEntity {
	/*
	 * 
	 */
	@BsonId
	public String channel;

	/*
	 * 
	 */
	public Services services;
}
