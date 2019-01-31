package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.unit;

import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model.Country;
import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model.CountryDataQuery;
import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model.CountryDataQueryResult;
import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model.CountryDataQueryResultPair;
import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.persistence.CountryPersistence;
import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.resource.CountryResource;
import mockit.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Category(UnitTest.class)
public class CountryResourceUnitTest {
    @Test
    public void testGetCountryListFromPersistence() throws Exception {
        CountryResource resource = new CountryResource();

        List<Country> mockCountryList = new ArrayList<Country>(Stream.of(
                new Country()
                        .setName("Country 1")
                        .setCode("C1"),
                new Country()
                        .setName("Country 2")
                        .setCode("C2")
        ).collect(Collectors.toList()));

        new Expectations(CountryPersistence.class) {{
            CountryPersistence.getAllCountries();
            result = mockCountryList;
        }};

        List<Country> result = resource.get();

        new Verifications() {{
            CountryPersistence.getAllCountries();
            times = 1;
        }};

        Assert.assertEquals("Should return the list of countries from persistence:", mockCountryList, result);
    }

    @Test
    public void testGetCountryFromPersistence() throws Exception {
        CountryResource resource = new CountryResource();

        Country mockCountry = new Country()
                .setName("Country")
                .setCode("C");

        new Expectations(CountryPersistence.class) {{
            CountryPersistence.getCountry("C");
            result = mockCountry;
        }};

        Country result = resource.get("C");

        new Verifications() {{
           CountryPersistence.getCountry("C");
           times = 1;
        }};

        Assert.assertEquals("Should return the specified country from persistence:", mockCountry, result);
    }

    @Test
    public void testGetCountryQueryFromPersistenceWithAllData(
            @Mocked CountryDataQuery mockCountryDataQuery
    ) throws Exception {
        CountryResource resource = new CountryResource();

        CountryDataQueryResult mockCountryDataQueryResult = new CountryDataQueryResult()
                .setCountry("Country")
                .setPopulation(new BigDecimal(100)) // 100 people
                .setArea(new BigDecimal(1.0)) // 1 square mile
                .setCapital("Capital")
                .setInfantMortality(new BigDecimal(5.0)) // 5 deaths per 1000 live births
                .setPopulationGrowthRate(new BigDecimal(1.1))
                .setGovernment("Government")
                .setIndependenceData("Independence")
                .setGdpInMillionsUsd(new BigDecimal(1440000))
                .setAgricultureAsPercentOfGdp(new BigDecimal(25))
                .setServiceAsPercentOfGdp(new BigDecimal(50))
                .setIndustryAsPercentOfGdp(new BigDecimal(25))
                .setInflationRatePerAnnum(new BigDecimal(1.5)) // annual percent change in rate of inflation
                .setTotalLengthOfBorder(new BigDecimal(1)) // 1 mile
                .setLanguageData(new ArrayList<CountryDataQueryResultPair>(Stream.of(
                        new CountryDataQueryResultPair()
                                .setDatumName("Language 1")
                                .setDatumValue(new BigDecimal(90)),
                        new CountryDataQueryResultPair()
                                .setDatumName("Language 2")
                                .setDatumValue(new BigDecimal(10))
                ).collect(Collectors.toList())))
                .setReligionData(new ArrayList<CountryDataQueryResultPair>(Stream.of(
                        new CountryDataQueryResultPair()
                                .setDatumName("Religion 1")
                                .setDatumValue(new BigDecimal(25)),
                        new CountryDataQueryResultPair()
                                .setDatumName("Religion 2")
                                .setDatumValue(new BigDecimal(75))
                ).collect(Collectors.toList())))
                .setEthnicityData(new ArrayList<CountryDataQueryResultPair>(Stream.of(
                        new CountryDataQueryResultPair()
                                .setDatumName("Ethnicity 1")
                                .setDatumValue(new BigDecimal(25)),
                        new CountryDataQueryResultPair()
                                .setDatumName("Ethnicity 2")
                                .setDatumValue(new BigDecimal(75))
                ).collect(Collectors.toList())))
                .setBorderingCountryData(new ArrayList<CountryDataQueryResultPair>(Stream.of(
                        new CountryDataQueryResultPair()
                                .setDatumName("Bordering Country 1")
                                .setDatumValue(new BigDecimal(1)), // 1 mile
                        new CountryDataQueryResultPair()
                                .setDatumName("Bordering Country 2")
                                .setDatumValue(new BigDecimal(1))
                ).collect(Collectors.toList())))
                .setContinentData(new ArrayList<String>(Stream.of(
                        "Continent 1"
                ).collect(Collectors.toList())))
                .setCityData(new ArrayList<String>(Stream.of(
                        "City 1"
                ).collect(Collectors.toList())))
                .setMountainData(new ArrayList<String>(Stream.of(
                        "Mountain 1"
                ).collect(Collectors.toList())))
                .setSeaData(new ArrayList<String>(Stream.of(
                        "Sea 1"
                ).collect(Collectors.toList())))
                .setRiverData(new ArrayList<String>(Stream.of(
                        "River 1"
                ).collect(Collectors.toList())))
                .setIslandData(new ArrayList<String>(Stream.of(
                        "Island 1"
                ).collect(Collectors.toList())))
                .setLakeData(new ArrayList<String>(Stream.of(
                        "Lake 1"
                ).collect(Collectors.toList())))
                .setDesertData(new ArrayList<String>(Stream.of(
                        "Desert 1"
                ).collect(Collectors.toList())));

        new Expectations() {{
            mockCountryDataQuery._withAllFields();
        }};

        new Expectations(CountryPersistence.class) {{
            CountryPersistence.getCountryData("C", mockCountryDataQuery);
            result = mockCountryDataQueryResult;
        }};

        CountryDataQueryResult result = resource.get("C", "");

        new Verifications() {{
            CountryPersistence.getCountryData("C", mockCountryDataQuery);
            times = 1;
        }};

        Assert.assertEquals("Should return all query data for the specified country from persistence:",
                mockCountryDataQueryResult, result);
    }

    @Test
    public void testGetCountryQueryFromPersistenceWithSomeData(
            @Mocked CountryDataQuery mockCountryDataQuery
    ) throws Exception {
        CountryResource resource = new CountryResource();

        CountryDataQueryResult mockCountryDataQueryResult = new CountryDataQueryResult()
                .setPopulation(new BigDecimal(1000000))
                .setLanguageData(Stream.of(
                        new CountryDataQueryResultPair()
                            .setDatumName("Language 1")
                            .setDatumValue(new BigDecimal(100))
                ).collect(Collectors.toList()));

        new Expectations() {{
            mockCountryDataQuery.withPopulation(true);
            mockCountryDataQuery.withLanguageData(true);
            mockCountryDataQuery._withRest(false);
        }};

        new Expectations(CountryPersistence.class) {{
            CountryPersistence.getCountryData("C1", (CountryDataQuery) any);
            result = mockCountryDataQueryResult;
        }};

        CountryDataQueryResult result = resource.get("C1", "population,languageData");

        new Verifications() {{
            CountryPersistence.getCountryData("C1", (CountryDataQuery) any);
            times = 1;
        }};

        Assert.assertEquals("Should return only query data specified for the specified country from persistence:",
                mockCountryDataQueryResult, result);
    }
}
