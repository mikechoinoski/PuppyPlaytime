package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import com.choinoski.entity.PlaydateMember;
import com.choinoski.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  This servlet provides the administrive functions for the administrators of the site.
 *
 * @author mrchoinoski
 */
public class AdministrativeFunctions extends HttpServlet {

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

        dao = new GenericDao(Pack.class);

        processDeletes(allPacks, request);

        String url = "/jsp/administrativeFunctions.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    /**
     *  This method processes deleting all of the packs that the administrator specifies.
     *
     * @param deleteList the list of packs available to delete
     * @param request the http servlet request
     */
    public void processDeletes(List<Pack> deleteList, HttpServletRequest request) {

        List<Pack> packsToCheck = new ArrayList(deleteList);

        String packNumberText = null;
        String checkBoxValue  = null;
        int    position       = 0;

        List<Integer> deletes = new ArrayList();

        for (Pack currentPack: packsToCheck) {

            packNumberText = Integer.toString(currentPack.getPackNumber());
            checkBoxValue  = request.getParameter("packToRemove" + packNumberText);

            if (!(checkBoxValue == null)) {

                for (Pack packToRemove: deleteList) {
                    if(packToRemove.getPackNumber() == currentPack.getPackNumber()) {
                        processPlaydateMemberDeletes(packToRemove);
                        deletes.add(packToRemove.getPackNumber());
                        deleteList.remove(packToRemove);
                        break;
                    }
                }
            }

            position++;
        }

        for (Integer deletePackNr : deletes) {
            Pack packToDelete = (Pack) dao.getById(deletePackNr);
            dao.delete(packToDelete);
        }

    }


    /**
     *  This method processes deleting playdate rows for members
     *
     * @param packToRemove the pack that needs to have rows removed from the playdate table
     */
    public void processPlaydateMemberDeletes(Pack packToRemove) {

        Set<PackMember> memberSet = new HashSet<>(packToRemove.getMembers());

        for (PackMember member: memberSet) {

            Set<PlaydateMember> playdateMemberSet = new HashSet<>(member.getPlaydateMembers());
            for (PlaydateMember currentPlaydateMember: playdateMemberSet) {

                dao.delete(currentPlaydateMember);
                boolean removed = member.removeMember(currentPlaydateMember);
            }
        }

    }


}