package com.choinoski.persistence;

import com.choinoski.entity.Pack;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * This class gets the data for a logged in user.
 *
 * @author mchoinoski
 */
public class LoggedInPack {

    /**
     * Retrieves the pack that is logged in.
     *
     * @param request the request that contains login information
     * @return the pack logged in
     */
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
