/*
 * ServicesResourceTest.java
 *
 * 29 nov 2022
 */
package it.pagopa.swclient.mil.services.resource;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.mutiny.Uni;
import it.pagopa.swclient.mil.services.bean.Labels;
import it.pagopa.swclient.mil.services.bean.Service;
import it.pagopa.swclient.mil.services.bean.Services;
import it.pagopa.swclient.mil.services.dao.ServicesEntity;
import it.pagopa.swclient.mil.services.dao.ServicesRepository;
import it.pagopa.swclient.mil.services.resource.ServicesResource;

/**
 * 
 * @author Antonio Tarricone
 */
@QuarkusTest
@TestHTTPEndpoint(ServicesResource.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServicesResourceTest {
	/*
	 * 
	 */
	@InjectMock
	ServicesRepository servicesRepository;

	/**
	 * 
	 */
	@BeforeAll
	public void setup() {
		Labels labels1 = new Labels();
		labels1.setIt("Pagamento Avvisi pagoPA");
		labels1.setEn("Payment of pagoPA Notices");

		Service service1 = new Service();
		service1.setServiceId("19386e0c-aec5-4f88-b8bc-667667fa18a9");
		service1.setLabels(labels1);

		Labels labels2 = new Labels();
		labels2.setIt("Iniziative Governative");
		labels2.setEn("Government Initiatives");

		Service service2 = new Service();
		service2.setServiceId("cdc24144-b7db-40e8-9e37-969f3aef5e40");
		service2.setLabels(labels1);

		Services services = new Services();
		services.setServices(List.of(service1, service2));

		ServicesEntity servicesEntity = new ServicesEntity();
		servicesEntity.channel = "ATM";
		servicesEntity.services = services;

		Uni<Optional<ServicesEntity>> servicesEntityOptionalUni = Uni.createFrom().item(Optional.of(servicesEntity));

		Mockito.when(servicesRepository.findByIdOptional("ATM")).thenReturn(servicesEntityOptionalUni);
	}

	/**
	 * 
	 */
	@Test
	void testGetServices_200() {
		given()
			.headers("RequestId", "1de3c885-5584-4910-b43a-4ad6e3fd55f9",
				"Version", "1.0.0",
				"AcquirerId", "12345",
				"Channel", "ATM",
				"TerminalId", "12345678")
			.when()
			.get()
			.then()
			.statusCode(200);
	}

	/**
	 * 
	 */
	@Test
	void testGetServices_404() {
		given()
			.headers("RequestId", "1de3c885-5584-4910-b43a-4ad6e3fd55f9",
				"Version", "1.0.0",
				"AcquirerId", "12345",
				"Channel", "POS",
				"MerchantId", "123456789012345",
				"TerminalId", "12345678")
			.when()
			.get()
			.then()
			.statusCode(404);
	}
}