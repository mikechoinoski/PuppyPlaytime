package com.choinoski.controller;

import com.choinoski.entity.Pack;
import com.choinoski.entity.PackMember;
import com.choinoski.persistence.GenericDao;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class DeletePacks {

    private GenericDao dao;

    public void processDeletes(Set<Pack> deleteSet, HttpServletRequest request) {

        String memberNumberText = null;
        String checkBoxValue    = null;

        for (Pack currentPack: deleteSet) {

            memberNumberText = Integer.toString(currentMember.getPackMemberNumber());
            checkBoxValue    = request.getParameter("memberToRemove" + memberNumberText);

            if (!(checkBoxValue == null)) {
                boolean removed = theUserPack.removeMember(currentMember);
                dao.delete(currentPack);
            }
        }

    }


}