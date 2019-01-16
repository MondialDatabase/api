package edu.kennesaw.ksuweb.bsetzer.cs8990.fall2014.projects;

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

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCapital() {
        return this.capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public BigDecimal getArea() {
        return this.area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getPopulation() {
        return this.population;
    }

    public void setPopulation(BigDecimal population) {
        this.population = population;
    }
}
