/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ServiceLayer
 * [ServiceLayer]<br>
 * USAGE:
 * <pre>
 *        ClientRest client = new ClientRest();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Armando
 */
public class ClientRest {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/VucicArmando_Project5/webresources";

    public ClientRest() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("ServiceLayer");
    }

    public String getAppointments() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("Appointments");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(String.class);
    }

    public String getInfo() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("Service");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(String.class);
    }

    public String getAppointmentById(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("Appointments/{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(String.class);
    }

    public void putXml(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void close() {
        client.close();
    }

    public static void main(String[] args) {
        ClientRest client = new ClientRest();
        System.out.println("Get Info: " + client.getInfo());
        System.out.println("Get all appointments" + client.getAppointments());
        System.out.println("Get appointment by id" + client.getAppointmentById("240"));
    }

}
