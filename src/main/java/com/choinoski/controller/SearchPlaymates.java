package com.choinoski.controller;

import com.choinoski.entity.PackMember;
import com.choinoski.persistence.GenericDao;
import com.choinoski.persistence.MemberSearchCriteria;
import com.choinoski.util.SearchDataConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * A simple servlet to welcome the user.
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/SearchPlaymates"}
)

public class SearchPlaymates extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao dao            = new GenericDao(PackMember.class);

        List<PackMember> members  = null;

        MemberSearchCriteria   searchParameters = null;

        LocalDate minimumDate = null;
        LocalDate maximumDate = null;

        int minimumWeight     = 0;
        int maximumWeight     = 0;

        char gender = ' ';
        Boolean intact = null;

        //logger.debug("Pack name: " + retrievedPack.getPackName());

        HttpSession          session          = request.getSession();
        //searchParameters = (MemberSearchCriteria) session.getAttribute("currentCriteria");
        if (session.getAttribute("currentCriteria") == null) {
            searchParameters = new MemberSearchCriteria(0,30,"XS",
                    "XL","Both","Both");
            members = dao.getAll();
        } else {
            //Need to compare the new values to the old values and make updates as needed
            searchParameters = new MemberSearchCriteria(Integer.parseInt(request.getParameter("minimumAge")),
                    Integer.parseInt(request.getParameter("maximumAge")),request.getParameter("minimumSize"),
                    request.getParameter("maximimSize"),request.getParameter("gender"),
                    request.getParameter("fixed"));

            minimumDate      = LocalDate.now().minusYears(searchParameters.getMinimumAge());
            maximumDate      = LocalDate.now().minusYears(searchParameters.getMaximumAge());
            minimumWeight    = searchParameters.getMinimumWeightForSize(searchParameters.getMinimumSize());
            maximumWeight    = searchParameters.getMaximumWeightForSize(searchParameters.getMaximumSize());
            gender           = searchParameters.getCharGender(searchParameters.getGender());
            intact           = searchParameters.getIntact(searchParameters.getFixed());

            members = dao.getByMultipleProperty(
                    "weight", minimumWeight, maximumWeight,
                    "dateOfBirth", maximumDate, minimumDate,
                    "sex", gender,
                    "intact",intact);
        }

        session.setAttribute("currentCriteria", searchParameters);
        request.setAttribute("members", members);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/searchPlaymates.jsp");
        dispatcher.forward(request, resp);
    }
}
