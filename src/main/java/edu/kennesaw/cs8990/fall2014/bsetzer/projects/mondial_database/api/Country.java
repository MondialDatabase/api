package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api;

import java.math.BigDecimal;

public class Country {
    private String name;
    private String code;
    private String capital;
    private String province;
    private BigDecimal area;
    private BigDecimal population;

    public String getName() {
        return this.name;
    }

    public Country setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public Country setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCapital() {
        return this.capital;
    }

    public Country setCapital(String capital) {
        this.capital = capital;
        return this;
    }

    public String getProvince() {
        return this.province;
    }

    public Country setProvince(String province) {
        this.province = province;
        return this;
    }

    public BigDecimal getArea() {
        return this.area;
    }

    public Country setArea(BigDecimal area) {
        this.area = area;
        return this;
    }

    public BigDecimal getPopulation() {
        return this.population;
    }

    public Country setPopulation(BigDecimal population) {
        this.population = population;
        return this;
    }
}
