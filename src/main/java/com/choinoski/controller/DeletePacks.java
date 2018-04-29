package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import com.choinoski.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeletePacks extends HttpServlet {

    private GenericDao dao;

    /**
     * Handles HTTP GET requests. Sets data for the HTTP request
     * data. Forwards data to a JSP to display.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if there is a Servlet failure
     * @throws IOException      if there is an IO failure
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<Pack> allPacks = (List<Pack>) session.getAttribute("allPacks");

        //dao = new GenericDao(Pack.class);

        //List<Pack> packs = dao.getAll();

        processDeletes(allPacks, request);

        String url = "/jsp/deletePacks.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    public void processDeletes(List<Pack> deleteSet, HttpServletRequest request) {

        String memberNumberText = null;
        String checkBoxValue    = null;

        for (Pack currentPack: deleteSet) {

            memberNumberText = Integer.toString(currentPack.getPackNumber());
            checkBoxValue    = request.getParameter("memberToRemove" + memberNumberText);

            if (!(checkBoxValue == null)) {
                dao.delete(currentPack);
            }
        }

    }


}