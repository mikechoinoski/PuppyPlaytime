package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import com.choinoski.persistence.GenericDao;
import com.choinoski.persistence.MemberSearchCriteria;
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

        String currentSearchType     = request.getParameter("searchType");
        String currentSearchTerm     = request.getParameter("searchTerm");
        GenericDao memberDao         = new GenericDao(PackMember.class);
        List<PackMember> membersAll  = null;
        List<PackMember> membersAge  = null;
        List<PackMember> results     = null;
        //Pack retrievedPack = (Pack) packDao.getById(3);

        //logger.debug("Pack name: " + retrievedPack.getPackName());

        //req.setAttribute("testPack", retrievedPack);
        HttpSession          session          = request.getSession();
        MemberSearchCriteria searchParameters = (MemberSearchCriteria) session.getAttribute("currentCriteria");

        //membersAll = memberDao.getAll();

        //LocalDate minimumDate = LocalDate.now().minusYears(searchParameters.getMinimumAge());
        //LocalDate maximumDate = LocalDate.now().minusYears(searchParameters.getMaximumAge());

        //membersAge = memberDao.getByPropertyBetween("dateOfBirth", maximumDate, minimumDate);


        if(currentSearchType == null) {
            membersAll = memberDao.getAll();
            request.setAttribute("members", membersAll);
        }
        //} else if(currentSearchType.equals("Search by Gender")){
        //    packMembers = memberDao.getByPropertyEqual("sex", currentSearchTerm);
        //    request.setAttribute("members", packMembers);
        //} else if(currentSearchType.equals("Search by Size")){
        //    packMembers = memberDao.getByPropertyEqual("size", currentSearchTerm);
        //    request.setAttribute("members", packMembers);
        //} else {
        //    packMembers = memberDao.getAll();
        //    request.setAttribute("members", packMembers);
        //}

        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/searchPlaymates.jsp");
        dispatcher.forward(request, resp);
    }
}
