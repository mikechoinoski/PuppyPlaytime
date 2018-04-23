package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import com.choinoski.entity.Role;
import com.choinoski.persistence.GenericDao;
import org.apache.commons.lang3.BooleanUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class YourPackUpdateServlet extends HttpServlet {

    private GenericDao dao;

    private Boolean    updatesMade;
    private Boolean    removeMembers;

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

        HttpSession session     = request.getSession();
        Pack        userPack    = (Pack) session.getAttribute("userPack");

        dao = new GenericDao(PackMember.class);

        Set<PackMember> memberSet = new HashSet<>(userPack.getMembers());

        processDeletes(userPack, memberSet, request);
        processUpdates(memberSet, request);

        String url = "/jsp/yourPack.jsp";

        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }


    public void processDeletes(Pack theUserPack, Set<PackMember> deleteSet, HttpServletRequest request) {

        String memberNumberText = null;
        String checkBoxValue    = null;

        for (PackMember currentMember: deleteSet) {

            memberNumberText = Integer.toString(currentMember.getPackMemberNumber());
            checkBoxValue    = request.getParameter("memberToRemove" + memberNumberText);

            if (!(checkBoxValue == null)) {
                boolean removed = theUserPack.removeMember(currentMember);
                dao.delete(currentMember);
            }
        }

    }

    public void processUpdates(Set<PackMember> updateSet, HttpServletRequest request) {

        String memberNumberText = null;

        String memberName       = null;
        String memberBirthday   = null;
        String memberWeight     = null;
        String memberBreed      = null;
        String memberGender     = null;
        String memberIntact     = null;

        for (PackMember currentMember: updateSet) {

            PackMember updatedMember = null;

            updatesMade      = false;
            removeMembers    = false;
            memberNumberText = Integer.toString(currentMember.getPackMemberNumber());

            memberName     = request.getParameter("memberName" + memberNumberText);
            memberBirthday = request.getParameter("memberBirthday" + memberNumberText);
            memberWeight   = request.getParameter("memberWeight" + memberNumberText);
            memberBreed    = request.getParameter("memberBreed" + memberNumberText);
            memberGender   = request.getParameter("memberGender" + memberNumberText);
            memberIntact   = request.getParameter("memberIntact" + memberNumberText);

            updatedMember = updatePackMember(currentMember, memberName, memberBirthday, memberWeight, memberBreed,
                    memberGender, memberIntact);

            if (!updatedMember.equals(currentMember)) {
                currentMember.copyDemographicData(updatedMember);
                dao.saveOrUpdate(currentMember);
            }

        }
    }

    public PackMember updatePackMember(PackMember member, String name, String birthday, String weight, String breed,
                                 String gender, String intact) {

        PackMember updatedMember = new PackMember(member);

        updatedMember.setName(name);
        updatedMember.setBreed(breed);

        if (weight != null) {
            updatedMember.setWeight(Integer.parseInt(weight));
        }

        if (birthday != null) {
            updatedMember.setDateOfBirth(LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

        if (gender != null) {
            if (intact.equals("Yes")) {
                updatedMember.setSex('M');
            } else if (intact.equals("No")) {
                updatedMember.setSex('F');
            }
        }

        if (intact != null) {
            if (intact.equals("Yes")) {
                updatedMember.setIntact(true);
            } else if (intact.equals("No")) {
                updatedMember.setIntact(false);
            }
        }

        return updatedMember;

    }

}
