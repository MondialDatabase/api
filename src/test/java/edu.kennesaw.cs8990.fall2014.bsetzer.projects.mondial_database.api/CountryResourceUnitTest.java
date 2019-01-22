package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api;

import mockit.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountryResourceUnitTest {
    @Test
    public void testCountryResourcesGetCountriesFromPersistence() throws Exception {
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
}
