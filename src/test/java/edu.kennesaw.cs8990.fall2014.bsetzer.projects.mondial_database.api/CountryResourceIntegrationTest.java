package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api;

import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.external.ExternalTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Category(IntegrationTest.class)
public class CountryResourceIntegrationTest extends JerseyTest {
    private final int NUMBER_OF_COUNTRIES = 244;

    @Override
    protected Application configure() {
        forceSet(TestProperties.CONTAINER_PORT, "8080");
        return new Application(); // Create a dummy application; the real application is created externally.
    }

    @Override
    protected TestContainerFactory getTestContainerFactory() {
        return new ExternalTestContainerFactory();
    }

    @Override
    protected URI getBaseUri() {
        return UriBuilder.fromUri(super.getBaseUri()).path("api")
                .build();
    }

    @Test
    public void testGet() {
        Response response = target("country")
                .request(MediaType.APPLICATION_JSON)
                .get();

        Assert.assertEquals("HTTP Response Status Code should be OK:",
                Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals("HTTP Content-Type should be JSON:",
                MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        // Test that Response Body is parse-able to a List of Country Objects.
        List<Country> countryList = response.readEntity(new GenericType<List<Country>>(){});

        Assert.assertTrue("HTTP Body should have a serialized list of all countries:",
                countryList.size() == NUMBER_OF_COUNTRIES);

        // Test that the United States is present in the Country List and defines the correct data fields.
        // If one country is correctly returned in the Response Body, then most likely all the others are as well.
        Country countryToTest = countryList.stream()
                .filter(country -> country.getName().equals("United States"))
                .findAny()
                .orElseThrow();

        Assert.assertTrue("HTTP body should have the serialized name for each country (test: United States):",
                countryToTest.getName().length() > 0);
        Assert.assertTrue("HTTP body should have the serialized code for each country (test: United States):",
                countryToTest.getCode().length() > 0);
        Assert.assertTrue("HTTP body should have the serialized capital for each country (test: United States):",
                countryToTest.getCapital().length() > 0);
        Assert.assertTrue("HTTP body should have the serialized province for each country (test: United States):",
                countryToTest.getProvince().length() > 0);
        Assert.assertTrue("HTTP body should have the serialized area for each country (test: United States):",
                countryToTest.getArea().intValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized population for each country (test: United States):",
                countryToTest.getPopulation().intValue() > 0);
    }

    @Test
    public void testGetWithEntityDataFiltering() {
        Response response = target("country")
                .queryParam("select", "name,code")
                .request(MediaType.APPLICATION_JSON)
                .get();

        Assert.assertEquals("HTTP Response Status Code should be OK:",
                Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals("HTTP Content-Type should be JSON:",
                MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        // Test that Response Body is parse-able to a List of Country Objects.
        List<Country> countryList = response.readEntity(new GenericType<List<Country>>(){});

        Assert.assertTrue("HTTP Body should have a serialized list of all countries:",
                countryList.size() == NUMBER_OF_COUNTRIES);

        // Test that the United States is present in the Country List and defines the correct data fields.
        // If one country is correctly returned in the Response Body, then most likely all the others are as well.
        Country countryToTest = countryList.stream()
                .filter(country -> country.getName().equals("United States"))
                .findAny()
                .orElseThrow();

        Assert.assertTrue("HTTP body should have the serialized name for each country (test: United States):",
                countryToTest.getName().length() > 0);
        Assert.assertTrue("HTTP body should have the serialized code for each country (test: United States):",
                countryToTest.getCode().length() > 0);

        // Test that the other property values are not included in the response
        // Unfortunately this is not good enough to test if the fields themselves
        // were completely excluded or not.
        Assert.assertTrue("HTTP body should not have the serialized capital for each country (test: United States):",
                countryToTest.getCapital() == null);
        Assert.assertTrue("HTTP body should not have the serialized province for each country (test: United States):",
                countryToTest.getProvince() == null);
        Assert.assertTrue("HTTP body should not have the serialized area for each country (test: United States):",
                countryToTest.getArea() == null);
        Assert.assertTrue("HTTP body should not have the serialized population for each country (test: United States):",
                countryToTest.getPopulation() == null);
    }
}
