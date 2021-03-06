package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model;

import java.math.BigDecimal;
import java.util.List;

public class CountryDataQueryResult {
    private String country;
    private BigDecimal population;
    private BigDecimal area;
    private String capital;
    private BigDecimal infantMortality;
    private BigDecimal populationGrowthRate;
    private String government;
    private String independenceData;
    private BigDecimal gdpInMillionsUsd;
    private BigDecimal agricultureAsPercentOfGdp;
    private BigDecimal serviceAsPercentOfGdp;
    private BigDecimal industryAsPercentOfGdp;
    private BigDecimal inflationRatePerAnnum;
    private BigDecimal totalLengthOfBorder;
    private List<CountryDataQueryResultPair> languageData;
    private List<CountryDataQueryResultPair> religionData;
    private List<CountryDataQueryResultPair> ethnicityData;
    private List<CountryDataQueryResultPair> borderingCountryData;
    private List<String> continentData;
    private List<String> cityData;
    private List<String> mountainData;
    private List<String> seaData;
    private List<String> riverData;
    private List<String> islandData;
    private List<String> lakeData;
    private List<String> desertData;

    public String getCountry() {
        return this.country;
    }
    public CountryDataQueryResult setCountry(String country) {
        this.country = country;
        return this;
    }

    public BigDecimal getPopulation() {
        return this.population;
    }
    public CountryDataQueryResult setPopulation(BigDecimal population) {
        this.population = population;
        return this;
    }

    public BigDecimal getArea() {
        return this.area;
    }
    public CountryDataQueryResult setArea(BigDecimal area) {
        this.area = area;
        return this;
    }

    public String getCapital() {
        return this.capital;
    }
    public CountryDataQueryResult setCapital(String capital) {
        this.capital = capital;
        return this;
    }

    public BigDecimal getInfantMortality() {
        return this.infantMortality;
    }
    public CountryDataQueryResult setInfantMortality(BigDecimal infantMortality) {
        this.infantMortality = infantMortality;
        return this;
    }

    public BigDecimal getPopulationGrowthRate() {
        return this.populationGrowthRate;
    }
    public CountryDataQueryResult setPopulationGrowthRate(BigDecimal populationGrowthRate) {
        this.populationGrowthRate = populationGrowthRate;
        return this;
    }

    public String getGovernment() {
        return this.government;
    }
    public CountryDataQueryResult setGovernment(String government) {
        this.government = government;
        return this;
    }

    public String getIndependenceData() {
        return this.independenceData;
    }
    public CountryDataQueryResult setIndependenceData(String independenceData) {
        this.independenceData = independenceData;
        return this;
    }

    public BigDecimal getGdpInMillionsUsd() {
        return this.gdpInMillionsUsd;
    }
    public CountryDataQueryResult setGdpInMillionsUsd(BigDecimal gdpInMillionsUsd) {
        this.gdpInMillionsUsd = gdpInMillionsUsd;
        return this;
    }

    public BigDecimal getAgricultureAsPercentOfGdp() {
        return this.agricultureAsPercentOfGdp;
    }
    public CountryDataQueryResult setAgricultureAsPercentOfGdp(BigDecimal agricultureAsPercentOfGdp) {
        this.agricultureAsPercentOfGdp = agricultureAsPercentOfGdp;
        return this;
    }

    public BigDecimal getServiceAsPercentOfGdp() {
        return this.serviceAsPercentOfGdp;
    }
    public CountryDataQueryResult setServiceAsPercentOfGdp(BigDecimal serviceAsPercentOfGdp) {
        this.serviceAsPercentOfGdp = serviceAsPercentOfGdp;
        return this;
    }

    public BigDecimal getIndustryAsPercentOfGdp() {
        return this.industryAsPercentOfGdp;
    }
    public CountryDataQueryResult setIndustryAsPercentOfGdp(BigDecimal industryAsPercentOfGdp) {
        this.industryAsPercentOfGdp = industryAsPercentOfGdp;
        return this;
    }

    public BigDecimal getInflationRatePerAnnum() {
        return this.inflationRatePerAnnum;
    }
    public CountryDataQueryResult setInflationRatePerAnnum(BigDecimal inflationRatePerAnnum) {
        this.inflationRatePerAnnum = inflationRatePerAnnum;
        return this;
    }

    public BigDecimal getTotalLengthOfBorder() {
        return this.totalLengthOfBorder;
    }
    public CountryDataQueryResult setTotalLengthOfBorder(BigDecimal totalLengthOfBorder) {
        this.totalLengthOfBorder = totalLengthOfBorder;
        return this;
    }

    public List<CountryDataQueryResultPair> getLanguageData() {
        return this.languageData;
    }
    public CountryDataQueryResult setLanguageData(List<CountryDataQueryResultPair> languageData) {
        this.languageData = languageData;
        return this;
    }

    public List<CountryDataQueryResultPair> getReligionData() {
        return this.religionData;
    }
    public CountryDataQueryResult setReligionData(List<CountryDataQueryResultPair> religionData) {
        this.religionData = religionData;
        return this;
    }

    public List<CountryDataQueryResultPair> getEthnicityData() {
        return this.ethnicityData;
    }
    public CountryDataQueryResult setEthnicityData(List<CountryDataQueryResultPair> ethnicityData) {
        this.ethnicityData = ethnicityData;
        return this;
    }

    public List<CountryDataQueryResultPair> getBorderingCountryData() {
        return this.borderingCountryData;
    }
    public CountryDataQueryResult setBorderingCountryData(List<CountryDataQueryResultPair> borderingCountryData) {
        this.borderingCountryData = borderingCountryData;
        return this;
    }

    public List<String> getContinentData() {
        return this.continentData;
    }

    public CountryDataQueryResult setContinentData(List<String> continentData) {
        this.continentData = continentData;
        return this;
    }

    public List<String> getCityData() {
        return this.cityData;
    }
    public CountryDataQueryResult setCityData(List<String> cityData) {
        this.cityData = cityData;
        return this;
    }

    public List<String> getMountainData() {
        return this.mountainData;
    }
    public CountryDataQueryResult setMountainData(List<String> mountainData) {
        this.mountainData = mountainData;
        return this;
    }

    public List<String> getSeaData() {
        return this.seaData;
    }
    public CountryDataQueryResult setSeaData(List<String> seaData) {
        this.seaData = seaData;
        return this;
    }

    public List<String> getRiverData() {
        return this.riverData;
    }
    public CountryDataQueryResult setRiverData(List<String> riverData) {
        this.riverData = riverData;
        return this;
    }

    public List<String> getIslandData() {
        return this.islandData;
    }
    public CountryDataQueryResult setIslandData(List<String> islandData) {
        this.islandData = islandData;
        return this;
    }

    public List<String> getLakeData() {
        return this.lakeData;
    }
    public CountryDataQueryResult setLakeData(List<String> lakeData) {
        this.lakeData = lakeData;
        return this;
    }

    public List<String> getDesertData() {
        return this.desertData;
    }
    public CountryDataQueryResult setDesertData(List<String> desertData) {
        this.desertData = desertData;
        return this;
    }
}
