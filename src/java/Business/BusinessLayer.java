/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import components.data.Appointment;
import components.data.AppointmentLabTest;
import components.data.DB;
import components.data.IComponentsData;
import components.data.PSC;
import components.data.Patient;
import components.data.Phlebotomist;
import java.util.List;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Armando
 */
public class BusinessLayer {

    public IComponentsData database = new DB();
    public UriInfo context;
    public List<Object> objs;
    public String url;
    public String listXML;

    public BusinessLayer(UriInfo context) {
        database.initialLoad("LAMS");
        this.context = context;
    }

    public String getInfo() {
        listXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><AppointmentList><intro>Welcome to the LAMS Appointment Service</intro>"
                + "<wadl>" + this.context.getBaseUri().toString() + "application.wadl" + "</wadl></AppointmentList>";
        return listXML;
    }

    public String getAppointments() {
        objs = database.getData("Appointment", "");
        listXML = generateXML(objs);
        return listXML;
    }

    public String generateXML(List<Object> objects) {
        listXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><AppointmentList>";

        for (Object appointObject : objects) {
            Appointment appointment = (Appointment) appointObject;
            Phlebotomist phlebotomist = appointment.getPhlebid();
            PSC psc = appointment.getPscid();
            Patient patient = appointment.getPatientid();

            listXML += "<appointment date=\"" + appointment.getApptdate() + "\"" + " id=\"" + appointment.getId() + "\""
                    + " time=\"" + appointment.getAppttime() + "\">" + "<uri>" + this.context.getBaseUri().toString() + "</uri>"
                    + "<patient id=\"" + patient.getId() + "\">" + "<name>" + patient.getName() + "</name>" + "<address>" + patient.getAddress() + "</address>"
                    + "<insurance>" + patient.getInsurance() + "</insurance>" + "<dob>" + patient.getDateofbirth() + "</dob>" + "</patient>";
            listXML += "<phlebotomist id=\"" + phlebotomist.getId() + "\">" + "<name>" + phlebotomist.getName() + "</name>" + "</phlebotomist>";
            listXML += "<psc id=\"" + psc.getId() + "\">" + "<name>" + psc.getName() + "</name>" + "</psc>" + "<allLabTests>";
            List<AppointmentLabTest> appointmentLabTests = appointment.getAppointmentLabTestCollection();
            for (AppointmentLabTest labTest : appointmentLabTests) {
                listXML += "<appointmentLabTest apptointmentId=\"" + labTest.getAppointment().getId() + "\""
                        + " dxcode=\"" + labTest.getAppointmentLabTestPK().getDxcode() + "\""
                        + " labTestId=\"" + labTest.getAppointmentLabTestPK().getLabtestid() + "\"></appointmentLabTest>";
            }
            listXML += "</allLabTests></appointment>";
        }
        listXML += "</AppointmentList>";
        return listXML;
    }

    public String getAppointmentById(String id) {
        objs = database.getData("Appointment", "patientid='" + id + "'");
        listXML = generateXML(objs);
        return listXML;
    }
}
