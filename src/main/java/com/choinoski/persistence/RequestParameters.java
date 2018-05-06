package com.choinoski.persistence;

import com.choinoski.entity.PackMember;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Compares data that was included in a request.
 *
 * @author mchoinoski
 */
public class RequestParameters {

    /**
     * Compares a list of members to a map that contains members that are selected.
     *
     * @param members         the members
     * @param selectedMembers the selected members
     * @param checkboxName    the checkbox name
     * @return the members from checkbox
     */
    public List<PackMember> getMembersFromCheckbox(List<PackMember> members, Map<String, String[]> selectedMembers,
                                                   String checkboxName) {

        List<PackMember> comparedMembers = new ArrayList<PackMember>();

        for (Map.Entry<String, String[]> entry : selectedMembers.entrySet()) {

            Boolean isCheckBox = entry.getKey().contains(checkboxName);

            if (isCheckBox) {
                String keyStr = entry.getKey().replace(checkboxName, "");
                for (PackMember currentMember : members) {
                    if (Integer.parseInt(keyStr) == currentMember.getPackMemberNumber()) {
                        comparedMembers.add(currentMember);
                    }
                }
            }
        }

        return comparedMembers;
    }

}
