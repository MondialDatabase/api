package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model.Country;
import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model.CountryDataQuery;
import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model.CountryDataQueryResult;
import edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model.CountryDataQueryResultPair;

import javax.ws.rs.NotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CountryPersistence {
    static final String JDBCPostgreSQLConnectionString = "jdbc:postgresql://db-server:5432/postgres?user=postgres";
    static final String SQLDriverClassPath = "org.postgresql.Driver";

    public static List<Country> getAllCountries() throws Exception {
        List<Country> countryList = new ArrayList<Country>();
        String sqlQuery = "SELECT name, code, capital, province, area, population from country";

        Class.forName(SQLDriverClassPath);

        try (Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {
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

    public static Country getCountry(String countryCode) throws Exception {
        Country country;
        String sqlQuery = "SELECT name, code, capital, province, area, population from country WHERE code = ?";

        Class.forName(SQLDriverClassPath);

        try (Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

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

    private static List<String> getGeographyInCountry(String countryCode, String geoTable, String geoTableColumn) throws Exception {
        List<String> geoList = new ArrayList<String>();
        String sqlQuery = String.format("SELECT DISTINCT %s FROM %s WHERE country = ?", geoTableColumn, geoTable);

        try (Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setString(1, countryCode);
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    geoList.add(resultSet.getString(geoTableColumn));
                }
            }
        }

        return geoList;
    }

    private static String createSQLQueryStringForRetrievingCountryData(CountryDataQuery countryDataQuery) {
        String selectionClause = "SELECT %s from country";
        List<String> nonAggregateColumnSelectionClauses = new ArrayList<String>();
        List<String> aggregateColumnSelectionClauses = new ArrayList<String>();
        List<String> joinClauses = new ArrayList<String>();
        String whereClause = "WHERE country.code = ?";

        if (countryDataQuery.withCountry()) {
            nonAggregateColumnSelectionClauses.add("country.name AS country_name");
        }

        if (countryDataQuery.withPopulation()) {
            nonAggregateColumnSelectionClauses.add("country.population AS country_population");
        }

        if (countryDataQuery.withArea()) {
            nonAggregateColumnSelectionClauses.add("country.area AS country_area");
        }

        if (countryDataQuery.withCapital()) {
            nonAggregateColumnSelectionClauses.add("country.capital AS country_capital");
        }

        if (countryDataQuery.withInfantMortality() || countryDataQuery.withPopulationGrowthRate()) {
            if (countryDataQuery.withInfantMortality()) {
                nonAggregateColumnSelectionClauses.add("population.infant_mortality AS country_population_infant_mortality");
            }

            if (countryDataQuery.withPopulationGrowthRate()) {
                nonAggregateColumnSelectionClauses.add("population.population_growth AS country_population_growth");
            }

            joinClauses.add("LEFT JOIN population ON country.code = population.country");
        }

        if (countryDataQuery.withGovernment() || countryDataQuery.withIndependenceData()) {
            if (countryDataQuery.withGovernment()) {
                nonAggregateColumnSelectionClauses.add("politics.government AS country_political_government");
            }

            if (countryDataQuery.withIndependenceData()) {
                nonAggregateColumnSelectionClauses.add("politics.dependent AS country_political_dependency");
                nonAggregateColumnSelectionClauses.add("politics.independence AS country_political_independence");
            }

            joinClauses.add("LEFT JOIN politics on country.code = politics.country");
        }

        if (countryDataQuery.withGdpInMillionsUsd() ||
                countryDataQuery.withAgricultureAsPercentOfGdp() ||
                countryDataQuery.withServiceAsPercentOfGdp() ||
                countryDataQuery.withIndustryAsPercentOfGdp() ||
                countryDataQuery.withInflationRatePerAnnum()) {

            if (countryDataQuery.withGdpInMillionsUsd()) {
                nonAggregateColumnSelectionClauses.add("economy.gdp AS country_economy_gdp");
            }

            if (countryDataQuery.withAgricultureAsPercentOfGdp()) {
                nonAggregateColumnSelectionClauses.add("economy.agriculture AS country_economy_agriculture");
            }

            if (countryDataQuery.withServiceAsPercentOfGdp()) {
                nonAggregateColumnSelectionClauses.add("economy.service AS country_economy_service");
            }

            if (countryDataQuery.withIndustryAsPercentOfGdp()) {
                nonAggregateColumnSelectionClauses.add("economy.industry AS country_economy_industry");
            }

            if (countryDataQuery.withInflationRatePerAnnum()) {
                nonAggregateColumnSelectionClauses.add("economy.inflation AS country_economy_inflation");
            }

            joinClauses.add("LEFT JOIN economy on country.code = economy.country");
        }

        if (countryDataQuery.withTotalLengthOfBorder() || countryDataQuery.withBorderingCountryData()) {
            if (countryDataQuery.withTotalLengthOfBorder()) {
                aggregateColumnSelectionClauses.add("sum(DISTINCT borders.length) AS country_border_length");
            }

            if (countryDataQuery.withBorderingCountryData()) {
                aggregateColumnSelectionClauses.add("json_agg(DISTINCT jsonb_build_object('datumName', concat(borders.country1, '/', borders.country2), 'datumValue', borders.length)) AS country_bordering_countries");
            }

            joinClauses.add("LEFT JOIN borders on country.code = borders.country1 or country.code = borders.country2");
        }

        if (countryDataQuery.withLanguageData()) {
            aggregateColumnSelectionClauses.add("json_agg(DISTINCT jsonb_build_object('datumName', language.name, 'datumValue', language.percentage)) AS country_languages");
            joinClauses.add("LEFT JOIN language on country.code = language.country");
        }

        if (countryDataQuery.withReligionData()) {
            aggregateColumnSelectionClauses.add("json_agg(DISTINCT jsonb_build_object('datumName', religion.name, 'datumValue', religion.percentage)) AS country_religions");
            joinClauses.add("LEFT JOIN religion on country.code = religion.country");
        }

        if (countryDataQuery.withEthnicityData()) {
            aggregateColumnSelectionClauses.add("json_agg(DISTINCT jsonb_build_object('datumName', ethnicgroup.name, 'datumValue', ethnicgroup.percentage)) AS country_ethnicities");
            joinClauses.add("LEFT JOIN ethnicgroup on country.code = ethnicgroup.country");
        }

        if (countryDataQuery.withContinentData()) {
            aggregateColumnSelectionClauses.add("json_agg(DISTINCT encompasses.continent) AS country_continents");
            joinClauses.add("LEFT JOIN encompasses on country.code = encompasses.country");
        }

        List<String> columnSelectionClauses = new ArrayList<String>();

        columnSelectionClauses.addAll(nonAggregateColumnSelectionClauses);
        columnSelectionClauses.addAll(aggregateColumnSelectionClauses);

        String groupByClause = nonAggregateColumnSelectionClauses.size() > 0 && aggregateColumnSelectionClauses.size() > 0 ?
                "GROUP BY " + String.join(",",
                        nonAggregateColumnSelectionClauses.stream()
                                .map(s -> s.replaceAll("AS.+", "")) // Removing any declared column aliases (this may not be necessary)
                                .collect(Collectors.toList())) : "";

        return String.join(" ",
                String.format(selectionClause, String.join(",", columnSelectionClauses)),
                String.join(" ", joinClauses),
                whereClause,
                groupByClause);
    }

    public static CountryDataQueryResult getCountryData(
            String countryCode,
            CountryDataQuery countryDataQuery
    ) throws Exception {
        CountryDataQueryResult data;
        String sqlQuery = CountryPersistence.createSQLQueryStringForRetrievingCountryData(countryDataQuery);

        Class.forName(SQLDriverClassPath);

        try (Connection connection = DriverManager.getConnection(JDBCPostgreSQLConnectionString);
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setString(1, countryCode);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new NotFoundException();
                }

                data = new CountryDataQueryResult();

                if (countryDataQuery.withCountry()) {
                    data.setCountry(resultSet.getString("country_name"));
                }

                if (countryDataQuery.withPopulation()) {
                    data.setPopulation(resultSet.getBigDecimal("country_population"));
                }

                if (countryDataQuery.withArea()) {
                    data.setArea(resultSet.getBigDecimal("country_area"));
                }

                if (countryDataQuery.withCapital()) {
                    data.setCapital(resultSet.getString("country_capital"));
                }

                if (countryDataQuery.withInfantMortality()) {
                    data.setInfantMortality(resultSet.getBigDecimal("country_population_infant_mortality"));
                }

                if (countryDataQuery.withPopulationGrowthRate()) {
                    data.setPopulationGrowthRate(resultSet.getBigDecimal("country_population_growth"));
                }

                if (countryDataQuery.withGovernment()) {
                    data.setGovernment(resultSet.getString("country_political_government"));
                }

                if (countryDataQuery.withIndependenceData()) {
                    String dependent = resultSet.getString("country_political_dependency");
                    if (dependent == null) {
                        data.setIndependenceData(resultSet.getString("country_political_independence"));
                    } else {
                        data.setIndependenceData(dependent);
                    }
                }

                if (countryDataQuery.withGdpInMillionsUsd()) {
                    data.setGdpInMillionsUsd(resultSet.getBigDecimal("country_economy_gdp"));
                }

                if (countryDataQuery.withAgricultureAsPercentOfGdp()) {
                    data.setAgricultureAsPercentOfGdp(resultSet.getBigDecimal("country_economy_agriculture"));
                }

                if (countryDataQuery.withServiceAsPercentOfGdp()) {
                    data.setServiceAsPercentOfGdp(resultSet.getBigDecimal("country_economy_service"));
                }

                if (countryDataQuery.withIndustryAsPercentOfGdp()) {
                    data.setIndustryAsPercentOfGdp(resultSet.getBigDecimal("country_economy_industry"));
                }

                if (countryDataQuery.withInflationRatePerAnnum()) {
                    data.setInflationRatePerAnnum(resultSet.getBigDecimal("country_economy_inflation"));
                }

                if (countryDataQuery.withTotalLengthOfBorder()) {
                    data.setTotalLengthOfBorder(resultSet.getBigDecimal("country_border_length"));
                }

                if (countryDataQuery.withBorderingCountryData()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    data.setBorderingCountryData(objectMapper.readValue(
                            resultSet.getString("country_bordering_countries"),
                            new TypeReference<List<CountryDataQueryResultPair>>(){}));
                }

                if (countryDataQuery.withLanguageData()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    data.setLanguageData(objectMapper.readValue(
                            resultSet.getString("country_languages"),
                            new TypeReference<List<CountryDataQueryResultPair>>(){}));
                }

                if (countryDataQuery.withReligionData()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    data.setReligionData(objectMapper.readValue(
                            resultSet.getString("country_religions"),
                            new TypeReference<List<CountryDataQueryResultPair>>(){}));
                }

                if (countryDataQuery.withEthnicityData()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    data.setEthnicityData(objectMapper.readValue(
                            resultSet.getString("country_ethnicities"),
                            new TypeReference<List<CountryDataQueryResultPair>>(){}));
                }

                if (countryDataQuery.withContinentData()) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    data.setContinentData(objectMapper.readValue(
                            resultSet.getString("country_continents"),
                            new TypeReference<List<String>>(){}));
                }

                if (countryDataQuery.withCityData()) {
                    data.setCityData(CountryPersistence
                            .getGeographyInCountry(countryCode, "city", "name"));
                }

                if (countryDataQuery.withMountainData()) {
                    data.setMountainData(CountryPersistence
                            .getGeographyInCountry(countryCode, "geo_mountain", "mountain"));
                }

                if (countryDataQuery.withSeaData()) {
                    data.setSeaData(CountryPersistence
                            .getGeographyInCountry(countryCode, "geo_sea", "sea"));
                }

                if (countryDataQuery.withRiverData()) {
                    data.setRiverData(CountryPersistence
                            .getGeographyInCountry(countryCode, "geo_river", "river"));
                }

                if (countryDataQuery.withIslandData()) {
                    data.setIslandData(CountryPersistence
                            .getGeographyInCountry(countryCode, "geo_island", "island"));
                }

                if (countryDataQuery.withLakeData()) {
                    data.setLakeData(CountryPersistence
                            .getGeographyInCountry(countryCode, "geo_lake", "lake"));
                }

                if (countryDataQuery.withDesertData()) {
                    data.setDesertData(CountryPersistence
                            .getGeographyInCountry(countryCode, "geo_desert", "desert"));
                }
            }
        }

        return data;
    }
}
