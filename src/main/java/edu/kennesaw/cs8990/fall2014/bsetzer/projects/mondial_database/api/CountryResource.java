package edu.kennesaw.cs8990.fall2014.bsetzer.projects.mondial_database.api;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("country")
public class CountryResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> get() throws Exception {
        return CountryPersistence.getAllCountries();
    }
}