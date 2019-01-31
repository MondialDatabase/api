package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.resource;

import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model.Country;
import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model.CountryDataQuery;
import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model.CountryDataQueryResult;
import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.persistence.CountryPersistence;

import java.util.Arrays;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("country")
public class CountryResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> get() throws Exception {
        return CountryPersistence.getAllCountries();
    }

    @GET
    @Path("/{PK}")
    @Produces(MediaType.APPLICATION_JSON)
    public Country get(@PathParam("PK") String countryCode) throws Exception {
        return CountryPersistence.getCountry(countryCode);
    }

    @GET
    @Path("/{PK}/query")
    @Produces(MediaType.APPLICATION_JSON)
    public CountryDataQueryResult get(
            @PathParam("PK") String country,
            @DefaultValue("") @QueryParam("select") String selection
    ) throws Exception {
        CountryDataQuery countryDataQuery = new CountryDataQuery();
        if (selection.equals("")) {
            countryDataQuery = countryDataQuery._withAllFields();
        } else {
            List<String> selectionList = Arrays.asList(selection.split(","));
            if (selectionList.contains("country")) {
                countryDataQuery.withCountry(true);
            }

            if (selectionList.contains("population")) {
                countryDataQuery.withPopulation(true);
            }

            if (selectionList.contains("area")) {
                countryDataQuery.withArea(true);
            }

            if (selectionList.contains("capital")) {
                countryDataQuery.withCapital(true);
            }

            if (selectionList.contains("infantMortality")) {
                countryDataQuery.withInfantMortality(true);
            }

            if (selectionList.contains("populationGrowthRate")) {
                countryDataQuery.withPopulationGrowthRate(true);
            }

            if (selectionList.contains("government")) {
                countryDataQuery.withGovernment(true);
            }

            if (selectionList.contains("independenceData")) {
                countryDataQuery.withIndependenceData(true);
            }

            if (selectionList.contains("gdpInMillionsUsd")) {
                countryDataQuery.withGdpInMillionsUsd(true);
            }

            if (selectionList.contains("agricultureAsPercentOfGdp")) {
                countryDataQuery.withAgricultureAsPercentOfGdp(true);
            }

            if (selectionList.contains("serviceAsPercentOfGdp")) {
                countryDataQuery.withServiceAsPercentOfGdp(true);
            }

            if (selectionList.contains("industryAsPercentOfGdp")) {
                countryDataQuery.withIndustryAsPercentOfGdp(true);
            }

            if (selectionList.contains("inflationRatePerAnnum")) {
                countryDataQuery.withInflationRatePerAnnum(true);
            }

            if (selectionList.contains("totalLengthOfBorder")) {
                countryDataQuery.withTotalLengthOfBorder(true);
            }

            if (selectionList.contains("languageData")) {
                countryDataQuery.withLanguageData(true);
            }

            if (selectionList.contains("religionData")) {
                countryDataQuery.withReligionData(true);
            }

            if (selectionList.contains("ethnicityData")) {
                countryDataQuery.withEthnicityData(true);
            }

            if (selectionList.contains("borderingCountryData")) {
                countryDataQuery.withBorderingCountryData(true);
            }

            if (selectionList.contains("continentData")) {
                countryDataQuery.withContinentData(true);
            }

            if (selectionList.contains("cityData")) {
                countryDataQuery.withCityData(true);
            }

            if (selectionList.contains("mountainData")) {
                countryDataQuery.withMountainData(true);
            }

            if (selectionList.contains("seaData")) {
                countryDataQuery.withSeaData(true);
            }

            if (selectionList.contains("riverData")) {
                countryDataQuery.withRiverData(true);
            }

            if (selectionList.contains("islandData")) {
                countryDataQuery.withIslandData(true);
            }

            if (selectionList.contains("lakeData")) {
                countryDataQuery.withLakeData(true);
            }

            if (selectionList.contains("desertData")) {
                countryDataQuery.withDesertData(true);
            }

            countryDataQuery._withRest(false);
        }

        return CountryPersistence.getCountryData(country, countryDataQuery);
    }
}