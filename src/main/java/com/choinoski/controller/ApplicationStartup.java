package com.choinoski.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.choinoski.util.*;

import static org.h2.util.SortedProperties.loadProperties;

/**
 *  This servlet sets initializes data used for the entire application. It
 *  is ran only once each time the application starts up.
 *
 * @author mrchoinoski
 */
@WebServlet(
        name = "applicationStartup",
        urlPatterns = { "/puppyPlaytimeStartUp" },
        loadOnStartup = 1
)
public class ApplicationStartup extends HttpServlet implements PropertiesLoaderInterface {

    private static final String PROPERTY_PATH = "/project4.properties";

    public void init() {

        Properties properties  = loadProperties(PROPERTY_PATH);
        getServletContext().setAttribute("puppyPlaytimeProperties", properties);

    }

}
