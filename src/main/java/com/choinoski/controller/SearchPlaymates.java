package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import com.choinoski.persistence.GenericDao;
import com.choinoski.persistence.MemberSearchCriteria;
import com.choinoski.util.SizeAndWeightConverter;
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

        String currentSearchType  = request.getParameter("searchType");
        String currentSearchTerm  = request.getParameter("searchTerm");

        GenericDao dao            = new GenericDao(PackMember.class);

        List<PackMember> members  = null;

        MemberSearchCriteria   searchParameters = null;

        SizeAndWeightConverter converter        = null;

        LocalDate minimumDate = null;
        LocalDate maximumDate = null;

        int minimumWeight     = 0;
        int maximumWeight     = 0;

        //logger.debug("Pack name: " + retrievedPack.getPackName());

        HttpSession          session          = request.getSession();
        if (session.getAttribute("currentCriteria") == null) {
            searchParameters = new MemberSearchCriteria(0,30,"XS",
                    "XL","Both","Both");
            members = dao.getAll();
            request.setAttribute("currentCriteria", searchParameters);
            request.setAttribute("members", members);
        } else {
            converter        = new SizeAndWeightConverter();
            searchParameters = (MemberSearchCriteria) session.getAttribute("currentCriteria");
            minimumDate      = LocalDate.now().minusYears(searchParameters.getMinimumAge());
            maximumDate      = LocalDate.now().minusYears(searchParameters.getMaximumAge());
            minimumWeight    = converter.getMinimumWeightForSize(searchParameters.getMinimumSize());
            maximumWeight    = converter.getMaximumWeightForSize(searchParameters.getMaximumSize());
            members = dao.getByMultipleProperty(
                    "weight", minimumWeight, maximumWeight,
                    "dateOfBirth", maximumDate, minimumDate,
                    "sex", ' ', "intact",null);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/searchPlaymates.jsp");
        dispatcher.forward(request, resp);
    }
}
