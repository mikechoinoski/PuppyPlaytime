package com.choinoski.persistence;

import com.choinoski.entity.Pack;
import com.choinoski.entity.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RetrieveUserDataTest {

    @Test
    public void testGoogleApiJSON() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Role currentRole = new Role();
        String output = mapper.writeValueAsString(currentRole);
        System.out.println(output);

        //Client client = ClientBuilder.newClient();
        //WebTarget target =
        //        client.target("http://maps.googleapis.com/maps/api/geocode/json?" +
        //                "address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=false");
        //String response = target.request(MediaType.TEXT_PLAIN).get(String.class);
        //ObjectMapper mapper = new ObjectMapper();
        //assertEquals(37.4224082, response);
    }


}