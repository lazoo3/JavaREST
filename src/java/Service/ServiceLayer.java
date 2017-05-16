/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Business.BusinessLayer;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Armando
 */
@Path("ServiceLayer")
public class ServiceLayer {

    @Context
    private UriInfo context;
    private BusinessLayer bl;

    public ServiceLayer() {
    }

    @GET
    @Path("/Service")
    @Produces(MediaType.APPLICATION_XML)
    public String getInfo() {
        bl = new BusinessLayer(context);
        return bl.getInfo();
    }

    @GET
    @Path("/Appointments")
    @Produces(MediaType.APPLICATION_XML)
    public String getAppointments() {
        bl = new BusinessLayer(context);
        return bl.getAppointments();
    }

    @GET
    @Path("/Appointments/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public String getAppointmentById(@PathParam("id") String id) {
        bl = new BusinessLayer(context);
        return bl.getAppointmentById(id);
    }

    /**
     * PUT method for updating or creating an instance of ServiceLayer
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
