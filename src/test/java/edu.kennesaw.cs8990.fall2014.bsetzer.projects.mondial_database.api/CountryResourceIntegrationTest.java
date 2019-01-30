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
import java.nio.charset.StandardCharsets;
import java.util.List;

@Category(IntegrationTest.class)
public class CountryResourceIntegrationTest extends JerseyTest {
    private final int NUMBER_OF_COUNTRIES = 244;
    private final String NON_EXISTENT_COUNTRY_CODE = "CountryCodeThatDoesNotExist";
    private final Country TEST_COUNTRY = new Country()
            .setName("United States")
            .setCode("USA");

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

        // Test that an expected country is present in the Country List and defines the correct data fields.
        // If one country is correctly returned in the Response Body, then most likely all the others are as well.
        Country countryToTest = countryList.stream()
                .filter(country -> country.getName().equals(TEST_COUNTRY.getName()))
                .findAny()
                .orElseThrow();

        Assert.assertTrue("HTTP body should have the serialized name for each country (test country):",
                countryToTest.getName().equals(TEST_COUNTRY.getName()));
        Assert.assertTrue("HTTP body should have the serialized code for each country (test country):",
                countryToTest.getCode().equals(TEST_COUNTRY.getCode()));
        Assert.assertTrue("HTTP body should have the serialized capital for each country (test country):",
                countryToTest.getCapital().length() > 0);
        Assert.assertTrue("HTTP body should have the serialized province for each country (test country):",
                countryToTest.getProvince().length() > 0);
        Assert.assertTrue("HTTP body should have the serialized area for each country (test country):",
                countryToTest.getArea().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized population for each country (test country):",
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

        // Test that an expected country is present in the Country List and defines the correct data fields.
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
        // were completely excluded or not.
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

        Assert.assertTrue("HTTP body should have the serialized name (test country):",
                country.getName().equals(TEST_COUNTRY.getName()));
        Assert.assertTrue("HTTP body should have the serialized code (test country):",
                country.getCode().equals(TEST_COUNTRY.getCode()));
        Assert.assertTrue("HTTP body should have the serialized capital (test country):",
                country.getCapital().length() > 0);
        Assert.assertTrue("HTTP body should have the serialized province (test country):",
                country.getProvince().length() > 0);
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

        Assert.assertTrue("HTTP body should have the serialized name (test country):",
                country.getName().equals(TEST_COUNTRY.getName()));
        Assert.assertTrue("HTTP body should have the serialized code (test country):",
                country.getCode().equals(TEST_COUNTRY.getCode()));
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

        // Test that Response Body is parse-able to a CountryData Object.
        CountryData countryData = response.readEntity(new GenericType<CountryData>(){});

        Assert.assertTrue("HTTP body should have the serialized country name (test country):",
                countryData.getCountry().equals(TEST_COUNTRY.getName()));
        Assert.assertTrue("HTTP body should have the serialized population (test country):",
                countryData.getPopulation().intValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized area (test country):",
                countryData.getArea().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized capital (test country):",
                countryData.getCapital().length() > 0);
        Assert.assertTrue("HTTP body should have the serialized infant mortality (test country):",
                countryData.getInfantMortality().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized population growth rate (test country):",
                countryData.getPopulationGrowthRate().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized government (test country):",
                countryData.getGovernment().length() > 0);
        Assert.assertTrue("HTTP body should have the serialized independence data (test country):",
                countryData.getIndependenceData().length() > 0);
        Assert.assertTrue("HTTP body should have the serialized GDP in millions USD (test country):",
                countryData.getGdpInMillionsUsd().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized agriculture as percent of GDP (test country):",
                countryData.getAgricultureAsPercentOfGdp().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized service as percent of GDP (test country):",
                countryData.getServiceAsPercentOfGdp().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized industry as percent of GDP (test country):",
                countryData.getIndustryAsPercentOfGdp().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized inflation rate per annum (test country):",
                countryData.getInflationRatePerAnnum().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized total length of border (test country):",
                countryData.getTotalLengthOfBorder().doubleValue() > 0);
        Assert.assertTrue("HTTP body should have the serialized language data (test country):",
                countryData.getLanguageData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized religion data (test country):",
                countryData.getReligionData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized ethnicity data (test country):",
                countryData.getEthnicityData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized bordering country data (test country):",
                countryData.getBorderingCountryData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized continent data (test country):",
                countryData.getContinentData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized city data (test country):",
                countryData.getCityData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized mountain data (test country):",
                countryData.getMountainData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized sea data (test country):",
                countryData.getSeaData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized river data (test country):",
                countryData.getRiverData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized island data (test country):",
                countryData.getIslandData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized lake data (test country):",
                countryData.getLakeData().size() > 0);
        Assert.assertTrue("HTTP body should have the serialized desert data (test country):",
                countryData.getDesertData().size() > 0);
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

        // Test that Response Body is parse-able to a CountryData Object.
        CountryData countryData = response.readEntity(new GenericType<CountryData>(){});

        Assert.assertTrue("HTTP body should not have the serialized country name (test country):",
                countryData.getCountry() == null);
        Assert.assertTrue("HTTP body should not have the serialized population (test country):",
                countryData.getPopulation() == null);
        Assert.assertTrue("HTTP body should not have the serialized area (test country):",
                countryData.getArea() == null);
        Assert.assertTrue("HTTP body should not have the serialized capital (test country):",
                countryData.getCapital() == null);
        Assert.assertTrue("HTTP body should not have the serialized infant mortality (test country):",
                countryData.getInfantMortality() == null);
        Assert.assertTrue("HTTP body should not have the serialized population growth rate (test country):",
                countryData.getPopulationGrowthRate() == null);
        Assert.assertTrue("HTTP body should not have the serialized government (test country):",
                countryData.getGovernment() == null);
        Assert.assertTrue("HTTP body should not have the serialized independence data (test country):",
                countryData.getIndependenceData() == null);
        Assert.assertTrue("HTTP body should not have the serialized GDP in millions USD (test country):",
                countryData.getGdpInMillionsUsd() == null);
        Assert.assertTrue("HTTP body should not have the serialized agriculture as percent of GDP (test country):",
                countryData.getAgricultureAsPercentOfGdp() == null);
        Assert.assertTrue("HTTP body should not have the serialized service as percent of GDP (test country):",
                countryData.getServiceAsPercentOfGdp() == null);
        Assert.assertTrue("HTTP body should not have the serialized industry as percent of GDP (test country):",
                countryData.getIndustryAsPercentOfGdp() == null);
        Assert.assertTrue("HTTP body should not have the serialized inflation rate per annum (test country):",
                countryData.getInflationRatePerAnnum() == null);
        Assert.assertTrue("HTTP body should have the serialized total length of border (test country):",
                countryData.getTotalLengthOfBorder().doubleValue() > 0);
        Assert.assertTrue("HTTP body should not have the serialized language data (test country):",
                countryData.getLanguageData() == null);
        Assert.assertTrue("HTTP body should not have the serialized religion data (test country):",
                countryData.getReligionData() == null);
        Assert.assertTrue("HTTP body should not have the serialized ethnicity data (test country):",
                countryData.getEthnicityData() == null);
        Assert.assertTrue("HTTP body should have the serialized bordering country data (test country):",
                countryData.getBorderingCountryData().size() > 0);
        Assert.assertTrue("HTTP body should not have the serialized continent data (test country):",
                countryData.getContinentData() == null);
        Assert.assertTrue("HTTP body should not have the serialized city data (test country):",
                countryData.getCityData() == null);
        Assert.assertTrue("HTTP body should not have the serialized mountain data (test country):",
                countryData.getMountainData() == null);
        Assert.assertTrue("HTTP body should not have the serialized sea data (test country):",
                countryData.getSeaData() == null);
        Assert.assertTrue("HTTP body should not have the serialized river data (test country):",
                countryData.getRiverData() == null);
        Assert.assertTrue("HTTP body should not have the serialized island data (test country):",
                countryData.getIslandData() == null);
        Assert.assertTrue("HTTP body should not have the serialized lake data (test country):",
                countryData.getLakeData() == null);
        Assert.assertTrue("HTTP body should not have the serialized desert data (test country):",
                countryData.getDesertData() == null);
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
