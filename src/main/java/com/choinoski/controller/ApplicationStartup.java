package com.choinoski.controller;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;
import com.choinoski.util.*;

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

    private static final String PROPERTY_PATH = "/puppyPlaytime.properties";

    /**
     *  This method does any initialization tasks for the entire application.
     */

    public void init() {

        Properties properties  = loadProperties(PROPERTY_PATH);

        getServletContext().setAttribute("puppyPlaytimeProperties", properties);

    }

}
