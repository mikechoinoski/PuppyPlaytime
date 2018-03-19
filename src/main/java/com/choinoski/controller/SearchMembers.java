package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import com.choinoski.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * A simple servlet to welcome the user.
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/searchMembers"}
)

public class SearchMembers extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String currentSearchType     = req.getParameter("searchType");
        String currentSearchTerm     = req.getParameter("searchTerm");
        GenericDao memberDao         = new GenericDao(PackMember.class);
        GenericDao packDao           = new GenericDao(Pack.class);
        List<PackMember> packMembers = null;

        Pack retrievedPack = (Pack) packDao.getById(3);

        logger.debug("Pack name: " + retrievedPack.getPackName());

        req.setAttribute("testPack", retrievedPack);

        if(currentSearchType == null) {
            packMembers = memberDao.getAll();
            req.setAttribute("members", packMembers);
        } else if(currentSearchType.equals("Search by Gender")){
            packMembers = memberDao.getByPropertyEqual("sex", currentSearchTerm);
            req.setAttribute("members", packMembers);
        } else if(currentSearchType.equals("Search by Size")){
            packMembers = memberDao.getByPropertyEqual("size", currentSearchTerm);
            req.setAttribute("members", packMembers);
        } else {
            packMembers = memberDao.getAll();
            req.setAttribute("members", packMembers);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/members.jsp");
        dispatcher.forward(req, resp);
    }
}
