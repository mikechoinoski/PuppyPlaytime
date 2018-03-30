package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import com.choinoski.entity.Role;
import com.choinoski.persistence.GenericDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

@Path("/userData")

public class RetrieveUserData {
    // The Java method will process HTTP GET requests
    @GET

    @Produces("application/json")
    public Response getMessage() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        GenericDao packDao  = new GenericDao(Pack.class);
        List<Pack> packs    = packDao.getAll();

        String     output   = "[";

        int        count    = 0;

        for (Pack currentPack : packs)
        {
            count = count + 1;
            if (count == packs.size()) {
                output = output + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(currentPack) + "]";
            } else {
                output = output + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(currentPack) + ",";
            }

        }

        return Response.ok(output, MediaType.APPLICATION_JSON).build();
    }

}