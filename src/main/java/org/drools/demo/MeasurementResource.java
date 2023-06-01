package org.drools.demo;

import java.util.HashSet;
import java.util.Set;

import org.kie.api.runtime.KieRuntimeBuilder;
import org.kie.api.runtime.KieSession;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/measurement")
public class MeasurementResource {

    @Inject
    KieRuntimeBuilder builder;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Set<?> measurement(Measurement measurement) {
        KieSession session = builder.newKieSession();
        Set<?> controlSet = new HashSet<>();
        session.setGlobal("controlSet", controlSet);
        session.insert(measurement);
        session.fireAllRules();
        return controlSet;
    }
}
