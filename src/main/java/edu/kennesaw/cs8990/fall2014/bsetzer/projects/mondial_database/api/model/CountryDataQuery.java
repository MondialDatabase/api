package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model;

public class CountryDataQuery {
    private Boolean country;
    private Boolean population;
    private Boolean area;
    private Boolean capital;
    private Boolean infantMortality;
    private Boolean populationGrowthRate;
    private Boolean government;
    private Boolean independenceData;
    private Boolean gdpInMillionsUsd;
    private Boolean agricultureAsPercentOfGdp;
    private Boolean serviceAsPercentOfGdp;
    private Boolean industryAsPercentOfGdp;
    private Boolean inflationRatePerAnnum;
    private Boolean totalLengthOfBorder;
    private Boolean languageData;
    private Boolean religionData;
    private Boolean ethnicityData;
    private Boolean borderingCountryData;
    private Boolean continentData;
    private Boolean cityData;
    private Boolean mountainData;
    private Boolean seaData;
    private Boolean riverData;
    private Boolean islandData;
    private Boolean lakeData;
    private Boolean desertData;

    public CountryDataQuery _withAllFields() {
        this.country = true;
        this.population = true;
        this.area = true;
        this.capital = true;
        this.infantMortality = true;
        this.populationGrowthRate = true;
        this.government = true;
        this.independenceData = true;
        this.gdpInMillionsUsd = true;
        this.agricultureAsPercentOfGdp = true;
        this.serviceAsPercentOfGdp = true;
        this.industryAsPercentOfGdp = true;
        this.inflationRatePerAnnum = true;
        this.totalLengthOfBorder = true;
        this.languageData = true;
        this.religionData = true;
        this.ethnicityData = true;
        this.borderingCountryData = true;
        this.continentData = true;
        this.cityData = true;
        this.mountainData = true;
        this.seaData = true;
        this.riverData = true;
        this.islandData = true;
        this.lakeData = true;
        this.desertData = true;
        return this;
    }

    public CountryDataQuery _withRest(boolean value) {
        if (this.country == null) { this.country = value; }
        if (this.population == null) { this.population = value; }
        if (this.area == null) { this.area = value; }
        if (this.capital == null) { this.capital = value; }
        if (this.infantMortality == null) { this.infantMortality = value; }
        if (this.populationGrowthRate == null) { this.populationGrowthRate = value; }
        if (this.government == null) { this.government = value; }
        if (this.independenceData == null) { this.independenceData = value; }
        if (this.gdpInMillionsUsd == null) { this.gdpInMillionsUsd = value; }
        if (this.agricultureAsPercentOfGdp == null) { this.agricultureAsPercentOfGdp = value; }
        if (this.serviceAsPercentOfGdp == null) { this.serviceAsPercentOfGdp = value; }
        if (this.industryAsPercentOfGdp == null) { this.industryAsPercentOfGdp = value; }
        if (this.inflationRatePerAnnum == null) { this.inflationRatePerAnnum = value; }
        if (this.totalLengthOfBorder == null) { this.totalLengthOfBorder = value; }
        if (this.languageData == null) { this.languageData = value; }
        if (this.religionData == null) { this.religionData = value; }
        if (this.ethnicityData == null) { this.ethnicityData = value; }
        if (this.borderingCountryData == null) { this.borderingCountryData = value; }
        if (this.continentData == null) { this.continentData = value; }
        if (this.cityData == null) { this.cityData = value; }
        if (this.mountainData == null) { this.mountainData = value; }
        if (this.seaData == null) { this.seaData = value; }
        if (this.riverData == null) { this.riverData = value; }
        if (this.islandData == null) { this.islandData = value; }
        if (this.lakeData == null) { this.lakeData = value; }
        if (this.desertData == null) { this.desertData = value; }
        return this;
    }

    public boolean withCountry() { return this.country; }
    public CountryDataQuery withCountry(boolean value) {
        this.country = value;
        return this;
    }

    public boolean withPopulation() { return this.population; }
    public CountryDataQuery withPopulation(boolean value) {
        this.population = value;
        return this;
    }

    public boolean withArea() { return this.area; }
    public CountryDataQuery withArea(boolean value) {
        this.area = value;
        return this;
    }

    public boolean withCapital() { return this.capital; }
    public CountryDataQuery withCapital(boolean value) {
        this.capital = value;
        return this;
    }

    public boolean withInfantMortality() { return this.infantMortality; }
    public CountryDataQuery withInfantMortality(boolean value) {
        this.infantMortality = value;
        return this;
    }

    public boolean withPopulationGrowthRate() { return this.populationGrowthRate; }
    public CountryDataQuery withPopulationGrowthRate(boolean value) {
        this.populationGrowthRate = value;
        return this;
    }

    public boolean withGovernment() { return this.government; }
    public CountryDataQuery withGovernment(boolean value) {
        this.government = value;
        return this;
    }

    public boolean withIndependenceData() { return this.independenceData; }
    public CountryDataQuery withIndependenceData(boolean value) {
        this.independenceData = value;
        return this;
    }

    public boolean withGdpInMillionsUsd() { return this.gdpInMillionsUsd; }
    public CountryDataQuery withGdpInMillionsUsd(boolean value) {
        this.gdpInMillionsUsd = value;
        return this;
    }

    public boolean withAgricultureAsPercentOfGdp() { return this.agricultureAsPercentOfGdp; }
    public CountryDataQuery withAgricultureAsPercentOfGdp(boolean value) {
        this.agricultureAsPercentOfGdp = true;
        return this;
    }

    public boolean withServiceAsPercentOfGdp() { return this.serviceAsPercentOfGdp; }
    public CountryDataQuery withServiceAsPercentOfGdp(boolean value) {
        this.serviceAsPercentOfGdp = value;
        return this;
    }

    public boolean withIndustryAsPercentOfGdp() { return this.industryAsPercentOfGdp; }
    public CountryDataQuery withIndustryAsPercentOfGdp(boolean value) {
        this.industryAsPercentOfGdp = value;
        return this;
    }

    public boolean withInflationRatePerAnnum() { return this.inflationRatePerAnnum; }
    public CountryDataQuery withInflationRatePerAnnum(boolean value) {
        this.inflationRatePerAnnum = value;
        return this;
    }

    public boolean withTotalLengthOfBorder() { return this.totalLengthOfBorder; }
    public CountryDataQuery withTotalLengthOfBorder(boolean value) {
        this.totalLengthOfBorder = value;
        return this;
    }

    public boolean withLanguageData() { return this.languageData; }
    public CountryDataQuery withLanguageData(boolean value) {
        this.languageData = value;
        return this;
    }

    public boolean withReligionData() { return this.religionData; }
    public CountryDataQuery withReligionData(boolean value) {
        this.religionData = value;
        return this;
    }

    public boolean withEthnicityData() { return this.ethnicityData; }
    public CountryDataQuery withEthnicityData(boolean value) {
        this.ethnicityData = value;
        return this;
    }

    public boolean withBorderingCountryData() { return this.borderingCountryData; }
    public CountryDataQuery withBorderingCountryData(boolean value) {
        this.borderingCountryData = value;
        return this;
    }

    public boolean withContinentData() { return this.continentData; }
    public CountryDataQuery withContinentData(boolean value) {
        this.continentData = value;
        return this;
    }

    public boolean withCityData() { return this.cityData; }
    public CountryDataQuery withCityData(boolean value) {
        this.cityData = value;
        return this;
    }

    public boolean withMountainData() { return this.mountainData; }
    public CountryDataQuery withMountainData(boolean value) {
        this.mountainData = value;
        return this;
    }

    public boolean withSeaData() { return this.seaData; }
    public CountryDataQuery withSeaData(boolean value) {
        this.seaData = value;
        return this;
    }

    public boolean withRiverData() { return this.riverData; }
    public CountryDataQuery withRiverData(boolean value) {
        this.riverData = value;
        return this;
    }

    public boolean withIslandData() { return this.islandData; }
    public CountryDataQuery withIslandData(boolean value) {
        this.islandData = value;
        return this;
    }

    public boolean withLakeData() { return this.lakeData; }
    public CountryDataQuery withLakeData(boolean value) {
        this.lakeData = value;
        return this;
    }

    public boolean withDesertData() { return this.desertData; }
    public CountryDataQuery withDesertData(boolean value) {
        this.desertData = value;
        return this;
    }
}
