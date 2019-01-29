package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api;

import java.math.BigDecimal;

public class CountryDatum {
    private String datumName;
    private BigDecimal datumValue;

    public String getDatumName() {
        return this.datumName;
    }

    public CountryDatum setDatumName(String datumName) {
        this.datumName = datumName;
        return this;
    }

    public BigDecimal getDatumValue() {
        return this.datumValue;
    }

    public CountryDatum setDatumValue(BigDecimal datumValue) {
        this.datumValue = datumValue;
        return this;
    }
}