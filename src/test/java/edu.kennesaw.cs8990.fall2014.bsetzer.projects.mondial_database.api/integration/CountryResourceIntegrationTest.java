package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.integration;

import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model.Country;
import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model.CountryDataQueryResult;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.external.ExternalTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.ws.rs.core.*;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Category(IntegrationTest.class)
public class CountryResourceIntegrationTest extends JerseyTest {
    private final int NUMBER_OF_COUNTRIES = 244;
    private final String NON_EXISTENT_COUNTRY_CODE = "CountryCodeThatDoesNotExist";
    private final Country TEST_COUNTRY = new Country()
            .setName("United States")
            .setCode("USA")
            .setCapital("Washington")
            .setProvince("District of Columbia");

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
    public void testGetCountryList() {
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

        countryList.stream().forEach(country -> {
            // Ensure fields that cannot have null values are properly defined
            Assert.assertTrue("HTTP body should have the serialized name for each country:",
                    country.getName() instanceof String);
            Assert.assertTrue("HTTP body should have the serialized code for each country:",
                    country.getCode() instanceof String);
            Assert.assertTrue("HTTP body should have the serialized area for each country:",
                    country.getArea().doubleValue() > 0);
            Assert.assertTrue("HTTP body should have the serialized population for each country:",
                    country.getPopulation().intValue() > 0);
        });

        // Test that an expected country is present in the Country List and defines the correct data fields and values.
        // If one country is correctly returned in the Response Body, then most likely all the others are as well.

        Country countryToTest = countryList.stream()
                .filter(country -> country.getName().equals(TEST_COUNTRY.getName()))
                .findAny()
                .orElseThrow();

        Assert.assertTrue("HTTP body should have the serialized name for the expected country:",
                countryToTest.getName().equals(TEST_COUNTRY.getName()));
        Assert.assertTrue("HTTP body should have the serialized code for the expected country:",
                countryToTest.getCode().equals(TEST_COUNTRY.getCode()));
        Assert.assertTrue("HTTP body should have the serialized capital for the expected country:",
                countryToTest.getCapital().equals(TEST_COUNTRY.getCapital()));
        Assert.assertTrue("HTTP body should have the serialized province for the expected country:",
                countryToTest.getProvince().equals(TEST_COUNTRY.getProvince()));
        Assert.assertTrue("HTTP body should have the serialized area for the expected country:",
                countryToTest.getArea().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized population for the expected country:",
                countryToTest.getPopulation().intValue() > 0);
    }

    @Test
    public void testGetCountryListWithEntityDataFiltering() {
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

        countryList.stream().forEach(country -> {
            // Ensure the requested fields that cannot have null values are properly defined
            Assert.assertTrue("HTTP body should have the serialized name for each country:",
                    country.getName() instanceof String);
            Assert.assertTrue("HTTP body should have the serialized code for each country:",
                    country.getCode() instanceof String);

            // Test that the other property values are not included in the response
            // Unfortunately this is not good enough to test if the fields themselves
            // were completely excluded from the response.
            Assert.assertTrue("HTTP body should not have the serialized capital for each country:",
                    country.getCapital() == null);
            Assert.assertTrue("HTTP body should not have the serialized province for each country:",
                    country.getProvince() == null);
            Assert.assertTrue("HTTP body should not have the serialized area for each country:",
                    country.getArea() == null);
            Assert.assertTrue("HTTP body should not have the serialized population for each country:",
                    country.getPopulation() == null);
        });

        // Test that an expected country is present in the Country List and defines the correct data fields and values.
        // If one country is correctly returned in the Response Body, then most likely all the others are as well.
        Country countryToTest = countryList.stream()
                .filter(country -> country.getName().equals(TEST_COUNTRY.getName()))
                .findAny()
                .orElseThrow();

        Assert.assertTrue("HTTP body should have the serialized name for each country (test country):",
                countryToTest.getName().equals(TEST_COUNTRY.getName()));
        Assert.assertTrue("HTTP body should have the serialized code for each country (test country):",
                countryToTest.getCode().equals(TEST_COUNTRY.getCode()));

        // Test that the other property values are not included in the response
        // Unfortunately this is not good enough to test if the fields themselves
        // were completely excluded from the response.
        Assert.assertTrue("HTTP body should not have the serialized capital for each country (test country):",
                countryToTest.getCapital() == null);
        Assert.assertTrue("HTTP body should not have the serialized province for each country (test country):",
                countryToTest.getProvince() == null);
        Assert.assertTrue("HTTP body should not have the serialized area for each country (test country):",
                countryToTest.getArea() == null);
        Assert.assertTrue("HTTP body should not have the serialized population for each country (test country):",
                countryToTest.getPopulation() == null);
    }

    @Test
    public void testGetCountryByCodeThatExists() {
        Response response = target("country")
                .path(TEST_COUNTRY.getCode())
                .request(MediaType.APPLICATION_JSON)
                .get();

        Assert.assertEquals("HTTP Response Status Code should be OK:",
                Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals("HTTP Content-Type should be JSON:",
                MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        // Test that Response Body is parse-able to a Country Object.
        Country country = response.readEntity(new GenericType<Country>(){});

        // Test that the country has the expected fields and values
        Assert.assertTrue("HTTP body should have the serialized name (test country):",
                country.getName().equals(TEST_COUNTRY.getName()));
        Assert.assertTrue("HTTP body should have the serialized code (test country):",
                country.getCode().equals(TEST_COUNTRY.getCode()));
        Assert.assertTrue("HTTP body should have the serialized capital (test country):",
                country.getCapital().equals(TEST_COUNTRY.getCapital()));
        Assert.assertTrue("HTTP body should have the serialized province (test country):",
                country.getProvince().equals(TEST_COUNTRY.getProvince()));
        Assert.assertTrue("HTTP body should have the serialized area (test country):",
                country.getArea().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized population (test country):",
                country.getPopulation().intValue() > 0);
    }

    @Test
    public void testGetCountryByCodeThatExistsWithEntityDataFiltering() {
        Response response = target("country")
                .path(TEST_COUNTRY.getCode())
                .queryParam("select", "name, code")
                .request(MediaType.APPLICATION_JSON)
                .get();

        Assert.assertEquals("HTTP Response Status Code should be OK:",
                Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals("HTTP Content-Type should be JSON:",
                MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        // Test that Response Body is parse-able to a Country Object.
        Country country = response.readEntity(new GenericType<Country>(){});

        // Test that the country has the expected fields and values
        Assert.assertTrue("HTTP body should have the serialized name (test country):",
                country.getName().equals(TEST_COUNTRY.getName()));
        Assert.assertTrue("HTTP body should have the serialized code (test country):",
                country.getCode().equals(TEST_COUNTRY.getCode()));

        // Test that the other property values are not included in the response
        // Unfortunately this is not good enough to test if the fields themselves
        // were completely excluded from the response.
        Assert.assertTrue("HTTP body should not have the serialized capital (test country):",
                country.getCapital() == null);
        Assert.assertTrue("HTTP body should not have the serialized province (test country):",
                country.getProvince() == null);
        Assert.assertTrue("HTTP body should not have the serialized area (test country):",
                country.getArea() == null);
        Assert.assertTrue("HTTP body should not have the serialized population (test country):",
                country.getPopulation() == null);
    }

    @Test
    public void testGetCountryByCodeThatDoesNotExist() {
        Response response = target("country")
                .path(NON_EXISTENT_COUNTRY_CODE)
                .request(MediaType.APPLICATION_JSON)
                .get();

        Assert.assertEquals("HTTP Response Status Code should be NOT FOUND:",
                Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        Assert.assertEquals("HTTP Content-Type should be HTML:",
                String.join(";", MediaType.TEXT_HTML, MediaType.CHARSET_PARAMETER + "=" + StandardCharsets.UTF_8.name().toLowerCase()),
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
    }

    @Test
    public void getCountryQueryForCountryThatExists() {
        Response response = target("country")
                .path(TEST_COUNTRY.getCode())
                .path("query")
                .request(MediaType.APPLICATION_JSON)
                .get();

        Assert.assertEquals("HTTP Response Status Code should be OK:",
                Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals("HTTP Content-Type should be JSON:",
                MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        // Test that Response Body is parse-able to a CountryDataQueryResult Object.
        CountryDataQueryResult countryDataQueryResult = response.readEntity(new GenericType<CountryDataQueryResult>(){});

        // Test that the country data query result has the expected fields and values
        Assert.assertTrue("HTTP body should have the serialized country name (test country):",
                countryDataQueryResult.getCountry().equals(TEST_COUNTRY.getName()));
        Assert.assertTrue("HTTP body should have the serialized population (test country):",
                countryDataQueryResult.getPopulation().intValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized area (test country):",
                countryDataQueryResult.getArea().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized capital (test country):",
                countryDataQueryResult.getCapital().equals(TEST_COUNTRY.getCapital()));
        Assert.assertTrue("HTTP body should have the serialized infant mortality (test country):",
                countryDataQueryResult.getInfantMortality().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized population growth rate (test country):",
                countryDataQueryResult.getPopulationGrowthRate().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized government (test country):",
                countryDataQueryResult.getGovernment().length() > 0);
        Assert.assertTrue("HTTP body should have the serialized independence data (test country):",
                countryDataQueryResult.getIndependenceData().length() > 0);
        Assert.assertTrue("HTTP body should have the serialized GDP in millions USD (test country):",
                countryDataQueryResult.getGdpInMillionsUsd().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized agriculture as percent of GDP (test country):",
                countryDataQueryResult.getAgricultureAsPercentOfGdp().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized service as percent of GDP (test country):",
                countryDataQueryResult.getServiceAsPercentOfGdp().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized industry as percent of GDP (test country):",
                countryDataQueryResult.getIndustryAsPercentOfGdp().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized inflation rate per annum (test country):",
                countryDataQueryResult.getInflationRatePerAnnum().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized total length of border (test country):",
                countryDataQueryResult.getTotalLengthOfBorder().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized language data (test country):",
                countryDataQueryResult.getLanguageData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized religion data (test country):",
                countryDataQueryResult.getReligionData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized ethnicity data (test country):",
                countryDataQueryResult.getEthnicityData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized bordering country data (test country):",
                countryDataQueryResult.getBorderingCountryData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized continent data (test country):",
                countryDataQueryResult.getContinentData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized city data (test country):",
                countryDataQueryResult.getCityData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized mountain data (test country):",
                countryDataQueryResult.getMountainData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized sea data (test country):",
                countryDataQueryResult.getSeaData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized river data (test country):",
                countryDataQueryResult.getRiverData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized island data (test country):",
                countryDataQueryResult.getIslandData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized lake data (test country):",
                countryDataQueryResult.getLakeData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized desert data (test country):",
                countryDataQueryResult.getDesertData().size() > 0);
    }

    @Test
    public void getCountryQueryForCountryThatExistsWithEntityFiltering() {
        Response response = target("country")
                .path(TEST_COUNTRY.getCode())
                .path("query")
                .queryParam("select", "totalLengthOfBorder,borderingCountryData")
                .request(MediaType.APPLICATION_JSON)
                .get();

        Assert.assertEquals("HTTP Response Status Code should be OK:",
                Response.Status.OK.getStatusCode(), response.getStatus());
        Assert.assertEquals("HTTP Content-Type should be JSON:",
                MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));

        // Test that Response Body is parse-able to a CountryDataQueryResult Object.
        CountryDataQueryResult countryDataQueryResult = response.readEntity(new GenericType<CountryDataQueryResult>(){});

        // Test that the country data query result has the expected fields and values
        // for the specified fields. Unfortunately this is not good enough to test
        // if the non-specified fields were completely excluded from the response.
        Assert.assertTrue("HTTP body should not have the serialized country name (test country):",
                countryDataQueryResult.getCountry() == null);
        Assert.assertTrue("HTTP body should not have the serialized population (test country):",
                countryDataQueryResult.getPopulation() == null);
        Assert.assertTrue("HTTP body should not have the serialized area (test country):",
                countryDataQueryResult.getArea() == null);
        Assert.assertTrue("HTTP body should not have the serialized capital (test country):",
                countryDataQueryResult.getCapital() == null);
        Assert.assertTrue("HTTP body should not have the serialized infant mortality (test country):",
                countryDataQueryResult.getInfantMortality() == null);
        Assert.assertTrue("HTTP body should not have the serialized population growth rate (test country):",
                countryDataQueryResult.getPopulationGrowthRate() == null);
        Assert.assertTrue("HTTP body should not have the serialized government (test country):",
                countryDataQueryResult.getGovernment() == null);
        Assert.assertTrue("HTTP body should not have the serialized independence data (test country):",
                countryDataQueryResult.getIndependenceData() == null);
        Assert.assertTrue("HTTP body should not have the serialized GDP in millions USD (test country):",
                countryDataQueryResult.getGdpInMillionsUsd() == null);
        Assert.assertTrue("HTTP body should not have the serialized agriculture as percent of GDP (test country):",
                countryDataQueryResult.getAgricultureAsPercentOfGdp() == null);
        Assert.assertTrue("HTTP body should not have the serialized service as percent of GDP (test country):",
                countryDataQueryResult.getServiceAsPercentOfGdp() == null);
        Assert.assertTrue("HTTP body should not have the serialized industry as percent of GDP (test country):",
                countryDataQueryResult.getIndustryAsPercentOfGdp() == null);
        Assert.assertTrue("HTTP body should not have the serialized inflation rate per annum (test country):",
                countryDataQueryResult.getInflationRatePerAnnum() == null);
        Assert.assertTrue("HTTP body should have the serialized total length of border (test country):",
                countryDataQueryResult.getTotalLengthOfBorder().doubleValue() > 0);
        Assert.assertTrue("HTTP body should not have the serialized language data (test country):",
                countryDataQueryResult.getLanguageData() == null);
        Assert.assertTrue("HTTP body should not have the serialized religion data (test country):",
                countryDataQueryResult.getReligionData() == null);
        Assert.assertTrue("HTTP body should not have the serialized ethnicity data (test country):",
                countryDataQueryResult.getEthnicityData() == null);
        Assert.assertTrue("HTTP body should have the serialized bordering country data (test country):",
                countryDataQueryResult.getBorderingCountryData().size() > 0);
        Assert.assertTrue("HTTP body should not have the serialized continent data (test country):",
                countryDataQueryResult.getContinentData() == null);
        Assert.assertTrue("HTTP body should not have the serialized city data (test country):",
                countryDataQueryResult.getCityData() == null);
        Assert.assertTrue("HTTP body should not have the serialized mountain data (test country):",
                countryDataQueryResult.getMountainData() == null);
        Assert.assertTrue("HTTP body should not have the serialized sea data (test country):",
                countryDataQueryResult.getSeaData() == null);
        Assert.assertTrue("HTTP body should not have the serialized river data (test country):",
                countryDataQueryResult.getRiverData() == null);
        Assert.assertTrue("HTTP body should not have the serialized island data (test country):",
                countryDataQueryResult.getIslandData() == null);
        Assert.assertTrue("HTTP body should not have the serialized lake data (test country):",
                countryDataQueryResult.getLakeData() == null);
        Assert.assertTrue("HTTP body should not have the serialized desert data (test country):",
                countryDataQueryResult.getDesertData() == null);
    }

    @Test
    public void getCountryQueryForCountryThatDoesNotExist() {
        Response response = target("country")
                .path(NON_EXISTENT_COUNTRY_CODE)
                .path("query")
                .request(MediaType.APPLICATION_JSON)
                .get();

        Assert.assertEquals("HTTP Response Status Code should be NOT FOUND:",
                Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        Assert.assertEquals("HTTP Content-Type should be HTML:",
                String.join(";", MediaType.TEXT_HTML, MediaType.CHARSET_PARAMETER + "=" + StandardCharsets.UTF_8.name().toLowerCase()),
                response.getHeaderString(HttpHeaders.CONTENT_TYPE));
    }
}
