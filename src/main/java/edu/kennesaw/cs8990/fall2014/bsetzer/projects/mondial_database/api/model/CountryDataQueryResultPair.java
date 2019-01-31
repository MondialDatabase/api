package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api.model;

import java.math.BigDecimal;

public class CountryDataQueryResultPair {
    private String datumName;
    private BigDecimal datumValue;

    public String getDatumName() {
        return this.datumName;
    }

    public CountryDataQueryResultPair setDatumName(String datumName) {
        this.datumName = datumName;
        return this;
    }

    public BigDecimal getDatumValue() {
        return this.datumValue;
    }

    public CountryDataQueryResultPair setDatumValue(BigDecimal datumValue) {
        this.datumValue = datumValue;
        return this;
    }
}