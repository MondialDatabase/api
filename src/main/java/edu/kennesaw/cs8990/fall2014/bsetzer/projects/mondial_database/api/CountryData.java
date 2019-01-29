package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api;

import java.math.BigDecimal;
import java.util.List;

public class CountryData {
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
    private List<CountryDatum> languageData;
    private List<CountryDatum> religionData;
    private List<CountryDatum> ethnicityData;
    private List<CountryDatum> borderingCountryData;
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

    public CountryData setCountry(String country) {
        this.country = country;
        return this;
    }

    public BigDecimal getPopulation() {
        return this.population;
    }

    public CountryData setPopulation(BigDecimal population) {
        this.population = population;
        return this;
    }

    public BigDecimal getArea() {
        return this.area;
    }

    public CountryData setArea(BigDecimal area) {
        this.area = area;
        return this;
    }

    public String getCapital() {
        return this.capital;
    }

    public CountryData setCapital(String capital) {
        this.capital = capital;
        return this;
    }

    public BigDecimal getInfantMortality() {
        return this.infantMortality;
    }

    public CountryData setInfantMortality(BigDecimal infantMortality) {
        this.infantMortality = infantMortality;
        return this;
    }

    public BigDecimal getPopulationGrowthRate() {
        return this.populationGrowthRate;
    }

    public CountryData setPopulationGrowthRate(BigDecimal populationGrowthRate) {
        this.populationGrowthRate = populationGrowthRate;
        return this;
    }

    public String getGovernment() {
        return this.government;
    }

    public CountryData setGovernment(String government) {
        this.government = government;
        return this;
    }

    public String getIndependenceData() {
        return this.independenceData;
    }

    public CountryData setIndependenceData(String independenceData) {
        this.independenceData = independenceData;
        return this;
    }

    public BigDecimal getGdpInMillionsUsd() {
        return this.gdpInMillionsUsd;
    }

    public CountryData setGdpInMillionsUsd(BigDecimal gdpInMillionsUsd) {
        this.gdpInMillionsUsd = gdpInMillionsUsd;
        return this;
    }

    public BigDecimal getAgricultureAsPercentOfGdp() {
        return this.agricultureAsPercentOfGdp;
    }

    public CountryData setAgricultureAsPercentOfGdp(BigDecimal agricultureAsPercentOfGdp) {
        this.agricultureAsPercentOfGdp = agricultureAsPercentOfGdp;
        return this;
    }

    public BigDecimal getServiceAsPercentOfGdp() {
        return this.serviceAsPercentOfGdp;
    }

    public CountryData setServiceAsPercentOfGdp(BigDecimal serviceAsPercentOfGdp) {
        this.serviceAsPercentOfGdp = serviceAsPercentOfGdp;
        return this;
    }

    public BigDecimal getIndustryAsPercentOfGdp() {
        return this.industryAsPercentOfGdp;
    }

    public CountryData setIndustryAsPercentOfGdp(BigDecimal industryAsPercentOfGdp) {
        this.industryAsPercentOfGdp = industryAsPercentOfGdp;
        return this;
    }

    public BigDecimal getInflationRatePerAnnum() {
        return this.inflationRatePerAnnum;
    }

    public CountryData setInflationRatePerAnnum(BigDecimal inflationRatePerAnnum) {
        this.inflationRatePerAnnum = inflationRatePerAnnum;
        return this;
    }

    public BigDecimal getTotalLengthOfBorder() {
        return this.totalLengthOfBorder;
    }

    public CountryData setTotalLengthOfBorder(BigDecimal totalLengthOfBorder) {
        this.totalLengthOfBorder = totalLengthOfBorder;
        return this;
    }

    public List<CountryDatum> getLanguageData() {
        return this.languageData;
    }

    public CountryData setLanguageData(List<CountryDatum> languageData) {
        this.languageData = languageData;
        return this;
    }

    public List<CountryDatum> getReligionData() {
        return this.religionData;
    }

    public CountryData setReligionData(List<CountryDatum> religionData) {
        this.religionData = religionData;
        return this;
    }

    public List<CountryDatum> getEthnicityData() {
        return this.ethnicityData;
    }

    public CountryData setEthnicityData(List<CountryDatum> ethnicityData) {
        this.ethnicityData = ethnicityData;
        return this;
    }

    public List<CountryDatum> getBorderingCountryData() {
        return this.borderingCountryData;
    }

    public CountryData setBorderingCountryData(List<CountryDatum> borderingCountryData) {
        this.borderingCountryData = borderingCountryData;
        return this;
    }

    public List<String> getContinentData() {
        return this.continentData;
    }

    public CountryData setContinentData(List<String> continentData) {
        this.continentData = continentData;
        return this;
    }

    public List<String> getCityData() {
        return this.cityData;
    }

    public CountryData setCityData(List<String> cityData) {
        this.cityData = cityData;
        return this;
    }

    public List<String> getMountainData() {
        return this.mountainData;
    }

    public CountryData setMountainData(List<String> mountainData) {
        this.mountainData = mountainData;
        return this;
    }

    public List<String> getSeaData() {
        return this.seaData;
    }

    public CountryData setSeaData(List<String> seaData) {
        this.seaData = seaData;
        return this;
    }

    public List<String> getRiverData() {
        return this.riverData;
    }

    public CountryData setRiverData(List<String> riverData) {
        this.riverData = riverData;
        return this;
    }

    public List<String> getIslandData() {
        return this.islandData;
    }

    public CountryData setIslandData(List<String> islandData) {
        this.islandData = islandData;
        return this;
    }

    public List<String> getLakeData() {
        return this.lakeData;
    }

    public CountryData setLakeData(List<String> lakeData) {
        this.lakeData = lakeData;
        return this;
    }

    public List<String> getDesertData() {
        return this.desertData;
    }

    public CountryData setDesertData(List<String> desertData) {
        this.desertData = desertData;
        return this;
    }
}
