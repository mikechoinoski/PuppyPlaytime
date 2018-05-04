package com.choinoski.persistence;

import com.choinoski.entity.Pack;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class LoggedInPack {

    public Pack loggedInPackInfo(HttpServletRequest request) {

        GenericDao dao      = new GenericDao(Pack.class);
        Pack       userPack = null;

        if (request.getUserPrincipal() != null) {
            String packName = request.getUserPrincipal().getName();

            List<Pack> packs = dao.getByPropertyEqual("packName", packName);

            if (packs.size() == 1) {
                userPack = packs.get(0);
            }
        }

        return userPack;

    }
}
