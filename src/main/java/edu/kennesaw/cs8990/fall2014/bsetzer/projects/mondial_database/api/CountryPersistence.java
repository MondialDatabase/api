package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.NotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class CountryPersistence {
    static final String JDBCPostgreSQLConnectionString = "jdbc:postgresql://db-server:5432/postgres?user=postgres";
    static final String SQLDriverClassPath = "org.postgresql.Driver";

    static List<Country> getAllCountries() throws Exception {
        List<Country> countryList = new ArrayList<Country>();
        String countriesQuery = "SELECT name, code, capital, province, area, population from country";

        Class.forName(SQLDriverClassPath);

        try (Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(countriesQuery)) {
            while (resultSet.next()) {
                Country country = new Country()
                        .setName(resultSet.getString("name"))
                        .setCode(resultSet.getString("code"))
                        .setCapital(resultSet.getString("capital"))
                        .setProvince(resultSet.getString("province"))
                        .setArea(resultSet.getBigDecimal("area"))
                        .setPopulation(resultSet.getBigDecimal("population"));

                countryList.add(country);
            }
        }

        return countryList;
    }

    static Country getCountry(String countryCode) throws Exception {
        Country country;
        String countryQuery = "SELECT name, code, capital, province, area, population from country WHERE code = ?";

        Class.forName(SQLDriverClassPath);

        try (Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);
             PreparedStatement statement = connection.prepareStatement(countryQuery)) {

            statement.setString(1, countryCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new NotFoundException();
                }

                country = new Country()
                        .setName(resultSet.getString("name"))
                        .setCode(resultSet.getString("code"))
                        .setCapital(resultSet.getString("capital"))
                        .setProvince(resultSet.getString("province"))
                        .setArea(resultSet.getBigDecimal("area"))
                        .setPopulation(resultSet.getBigDecimal("population"));
            }
        }

        return country;
    }

    private static List<String> getCitiesInCountry(String countryCode) throws Exception {
        List<String> cityList = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT DISTINCT name FROM city WHERE country = ?")) {

            statement.setString(1, countryCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cityList.add(resultSet.getString("name"));
                }
            }
        }

        return cityList;
    }

    private static List<String> getMountainsInCountry(String countryCode) throws Exception {
        List<String> mountainList = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT DISTINCT mountain FROM geo_mountain WHERE country = ?")) {

            statement.setString(1, countryCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    mountainList.add(resultSet.getString("mountain"));
                }
            }
        }

        return mountainList;
    }

    private static List<String> getSeasInCountry(String countryCode) throws Exception {
        List<String> seaList = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT DISTINCT sea FROM geo_sea WHERE country = ?")) {

            statement.setString(1, countryCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    seaList.add(resultSet.getString("sea"));
                }
            }
        }

        return seaList;
    }

    private static List<String> getRiversInCountry(String countryCode) throws Exception {
        List<String> riverList = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT DISTINCT river FROM geo_river WHERE country = ?")) {

            statement.setString(1, countryCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    riverList.add(resultSet.getString("river"));
                }
            }
        }

        return riverList;
    }

    private static List<String> getIslandsInCountry(String countryCode) throws Exception {
        List<String> islandList = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT DISTINCT island FROM geo_island WHERE country = ?")) {

            statement.setString(1, countryCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    islandList.add(resultSet.getString("island"));
                }
            }
        }

        return islandList;
    }

    private static List<String> getLakesInCountry(String countryCode) throws Exception {
        List<String> lakeList = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT DISTINCT lake FROM geo_lake WHERE country = ?")) {

            statement.setString(1, countryCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    lakeList.add(resultSet.getString("lake"));
                }
            }
        }

        return lakeList;
    }

    private static List<String> getDesertsInCountry(String countryCode) throws Exception {
        List<String> desertList = new ArrayList<String>();
        try (Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT DISTINCT desert FROM geo_desert WHERE country = ?")) {

            statement.setString(1, countryCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    desertList.add(resultSet.getString("desert"));
                }
            }
        }

        return desertList;
    }

    static CountryData getCountryData(
            String countryCode,
            boolean population,
            boolean area,
            boolean capital,
            boolean infantMortality,
            boolean populationGrowthRate,
            boolean government,
            boolean independenceData,
            boolean gdpInMillionsUsd,
            boolean agricultureAsPercentOfGdp,
            boolean serviceAsPercentOfGdp,
            boolean industryAsPercentOfGdp,
            boolean inflationRatePerAnnum,
            boolean totalLengthOfBorder,
            boolean languageData,
            boolean religionData,
            boolean ethnicityData,
            boolean borderingCountryData,
            boolean continentData,
            boolean cityData,
            boolean mountainData,
            boolean seaData,
            boolean riverData,
            boolean islandData,
            boolean lakeData,
            boolean desertData
    ) throws Exception {
        CountryData data;
        String selection = "SELECT %s from country";
        String query = "WHERE country.code = ?";

        List<String> nonAggregateColumnSelections = new ArrayList<String>();
        List<String> aggregateColumnSelections = new ArrayList<String>();

        nonAggregateColumnSelections.add("country.name AS country_name");

        if (population) {
            nonAggregateColumnSelections.add("country.population AS country_population");
        }

        if (area) {
            nonAggregateColumnSelections.add("country.area AS country_area");
        }

        if (capital) {
            nonAggregateColumnSelections.add("country.capital AS country_capital");
        }

        List<String> countryJoins = new ArrayList<String>();

        if (infantMortality || populationGrowthRate) {
            if (infantMortality) {
                nonAggregateColumnSelections.add("population.infant_mortality AS country_population_infant_mortality");
            }

            if (populationGrowthRate) {
                nonAggregateColumnSelections.add("population.population_growth AS country_population_growth");
            }

            countryJoins.add("LEFT JOIN population ON country.code = population.country");
        }

        if (government || independenceData) {
            if (government) {
                nonAggregateColumnSelections.add("politics.government AS country_political_government");
            }

            if (independenceData) {
                nonAggregateColumnSelections.add("politics.dependent AS country_political_dependency");
                nonAggregateColumnSelections.add("politics.independence AS country_political_independence");
            }

            countryJoins.add("LEFT JOIN politics on country.code = politics.country");
        }

        if (gdpInMillionsUsd || agricultureAsPercentOfGdp || serviceAsPercentOfGdp || industryAsPercentOfGdp || inflationRatePerAnnum) {
            if (gdpInMillionsUsd) {
                nonAggregateColumnSelections.add("economy.gdp AS country_economy_gdp");
            }

            if (agricultureAsPercentOfGdp) {
                nonAggregateColumnSelections.add("economy.agriculture AS country_economy_agriculture");
            }

            if (serviceAsPercentOfGdp) {
                nonAggregateColumnSelections.add("economy.service AS country_economy_service");
            }

            if (industryAsPercentOfGdp) {
                nonAggregateColumnSelections.add("economy.industry AS country_economy_industry");
            }

            if (inflationRatePerAnnum) {
                nonAggregateColumnSelections.add("economy.inflation AS country_economy_inflation");
            }

            countryJoins.add("LEFT JOIN economy on country.code = economy.country");
        }

        if (totalLengthOfBorder || borderingCountryData) {
            if (totalLengthOfBorder) {
                aggregateColumnSelections.add("sum(DISTINCT borders.length) AS country_border_length");
            }

            if (borderingCountryData) {
                aggregateColumnSelections.add("json_agg(DISTINCT jsonb_build_object('datumName', concat(borders.country1, '/', borders.country2), 'datumValue', borders.length)) AS country_bordering_countries");
            }

            countryJoins.add("LEFT JOIN borders on country.code = borders.country1 or country.code = borders.country2");
        }

        if (languageData) {
            aggregateColumnSelections.add("json_agg(DISTINCT jsonb_build_object('datumName', language.name, 'datumValue', language.percentage)) AS country_languages");
            countryJoins.add("LEFT JOIN language on country.code = language.country");
        }

        if (religionData) {
            aggregateColumnSelections.add("json_agg(DISTINCT jsonb_build_object('datumName', religion.name, 'datumValue', religion.percentage)) AS country_religions");
            countryJoins.add("LEFT JOIN religion on country.code = religion.country");
        }

        if (ethnicityData) {
            aggregateColumnSelections.add("json_agg(DISTINCT jsonb_build_object('datumName', ethnicgroup.name, 'datumValue', ethnicgroup.percentage)) AS country_ethnicities");
            countryJoins.add("LEFT JOIN ethnicgroup on country.code = ethnicgroup.country");
        }

        if (continentData) {
            aggregateColumnSelections.add("json_agg(DISTINCT encompasses.continent) AS country_continents");
            countryJoins.add("LEFT JOIN encompasses on country.code = encompasses.country");
        }

        Class.forName(SQLDriverClassPath);

        List<String> columnSelections = new ArrayList<String>();
        columnSelections.addAll(nonAggregateColumnSelections);
        columnSelections.addAll(aggregateColumnSelections);

        try (Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);
             PreparedStatement statement = connection.prepareStatement(String.join(" ",
                     String.format(selection, String.join(",", columnSelections)),
                     String.join(" ", countryJoins),
                     query,
                     aggregateColumnSelections.size() == 0 ? "" : "GROUP BY " + String.join(",",
                             nonAggregateColumnSelections.stream()
                                     .map(s -> s.replaceAll("AS.+", ""))
                                     .collect(Collectors.toList()))))) {

            statement.setString(1, countryCode);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new NotFoundException();
                }

                data = new CountryData()
                        .setCountry(resultSet.getString("country_name"));

                if (population) {
                    data.setPopulation(resultSet.getBigDecimal("country_population"));
                }

                if (area) {
                    data.setArea(resultSet.getBigDecimal("country_area"));
                }

                if (capital) {
                    data.setCapital(resultSet.getString("country_capital"));
                }

                if (infantMortality) {
                    data.setInfantMortality(resultSet.getBigDecimal("country_population_infant_mortality"));
                }

                if (populationGrowthRate) {
                    data.setPopulationGrowthRate(resultSet.getBigDecimal("country_population_growth"));
                }

                if (government) {
                    data.setGovernment(resultSet.getString("country_political_government"));
                }

                if (independenceData) {
                    String dependent = resultSet.getString("country_political_dependency");
                    if (dependent == null) {
                        data.setIndependenceData(resultSet.getString("country_political_independence"));
                    } else {
                        data.setIndependenceData(dependent);
                    }
                }

                if (gdpInMillionsUsd) {
                    data.setGdpInMillionsUsd(resultSet.getBigDecimal("country_economy_gdp"));
                }

                if (agricultureAsPercentOfGdp) {
                    data.setAgricultureAsPercentOfGdp(resultSet.getBigDecimal("country_economy_agriculture"));
                }

                if (serviceAsPercentOfGdp) {
                    data.setServiceAsPercentOfGdp(resultSet.getBigDecimal("country_economy_service"));
                }

                if (industryAsPercentOfGdp) {
                    data.setIndustryAsPercentOfGdp(resultSet.getBigDecimal("country_economy_industry"));
                }

                if (inflationRatePerAnnum) {
                    data.setInflationRatePerAnnum(resultSet.getBigDecimal("country_economy_inflation"));
                }

                if (totalLengthOfBorder) {
                    data.setTotalLengthOfBorder(resultSet.getBigDecimal("country_border_length"));
                }

                if (borderingCountryData) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    data.setBorderingCountryData(objectMapper.readValue(
                            resultSet.getString("country_bordering_countries"),
                            new TypeReference<List<CountryDatum>>(){}));
                }

                if (languageData) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    data.setLanguageData(objectMapper.readValue(
                            resultSet.getString("country_languages"),
                            new TypeReference<List<CountryDatum>>(){}));
                }

                if (religionData) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    data.setReligionData(objectMapper.readValue(
                            resultSet.getString("country_religions"),
                            new TypeReference<List<CountryDatum>>(){}));
                }

                if (ethnicityData) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    data.setEthnicityData(objectMapper.readValue(
                            resultSet.getString("country_ethnicities"),
                            new TypeReference<List<CountryDatum>>(){}));
                }

                if (continentData) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    data.setContinentData(objectMapper.readValue(
                            resultSet.getString("country_continents"),
                            new TypeReference<List<String>>(){}));
                }

                if (cityData) {
                    data.setCityData(CountryPersistence.getCitiesInCountry(countryCode));
                }

                if (mountainData) {
                    data.setMountainData(CountryPersistence.getMountainsInCountry(countryCode));
                }

                if (seaData) {
                    data.setSeaData(CountryPersistence.getSeasInCountry(countryCode));
                }

                if (riverData) {
                    data.setRiverData(CountryPersistence.getRiversInCountry(countryCode));
                }

                if (islandData) {
                    data.setIslandData(CountryPersistence.getIslandsInCountry(countryCode));
                }

                if (lakeData) {
                    data.setLakeData(CountryPersistence.getLakesInCountry(countryCode));
                }

                if (desertData) {
                    data.setDesertData(CountryPersistence.getDesertsInCountry(countryCode));
                }
            }
        }

        return data;
    }
}
