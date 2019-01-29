package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api;

import java.util.Arrays;
import java.util.List;
import javax.validation.groups.Default;
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
    public CountryData get(
            @PathParam("PK") String country,
            @DefaultValue("") @QueryParam("select") String selection
    ) throws Exception {
        List<String> selectionList = Arrays.asList(selection.split(","));
        boolean selectAll = selection.equals("");

        return CountryPersistence.getCountryData(
                country,
                selectAll || selectionList.contains("population"),
                selectAll || selectionList.contains("area"),
                selectAll || selectionList.contains("capital"),
                selectAll || selectionList.contains("infantMortality"),
                selectAll || selectionList.contains("populationGrowthRate"),
                selectAll || selectionList.contains("government"),
                selectAll || selectionList.contains("independenceData"),
                selectAll || selectionList.contains("gdpInMillionsUsd"),
                selectAll || selectionList.contains("agricultureAsPercentOfGdp"),
                selectAll || selectionList.contains("serviceAsPercentOfGdp"),
                selectAll || selectionList.contains("industryAsPercentOfGdp"),
                selectAll || selectionList.contains("inflationRatePerAnnum"),
                selectAll || selectionList.contains("totalLengthOfBorder"),
                selectAll || selectionList.contains("languageData"),
                selectAll || selectionList.contains("religionData"),
                selectAll || selectionList.contains("ethnicityData"),
                selectAll || selectionList.contains("borderingCountryData"),
                selectAll || selectionList.contains("continentData"),
                selectAll || selectionList.contains("cityData"),
                selectAll || selectionList.contains("mountainData"),
                selectAll || selectionList.contains("seaData"),
                selectAll || selectionList.contains("riverData"),
                selectAll || selectionList.contains("islandData"),
                selectAll || selectionList.contains("lakeData"),
                selectAll || selectionList.contains("desertData")
        );
    }
}