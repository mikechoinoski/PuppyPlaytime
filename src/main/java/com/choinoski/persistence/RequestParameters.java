package com.choinoski.persistence;

import com.choinoski.entity.PackMember;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestParameters {

    public List<PackMember> getMembersFromCheckbox(List<PackMember> members, Map<String, String[]> selectedMembers) {

        List<PackMember> comparedMembers = new ArrayList<PackMember>();

        for (Map.Entry<String, String[]> entry : selectedMembers.entrySet()) {

            String keyStr = entry.getKey().replace("memberCheckBox", "");
            //memberNumbers.add(Integer.parseInt(keyStr));
            for (PackMember currentMember : members) {
                if (Integer.parseInt(keyStr) == currentMember.getPackMemberNumber()) {
                    comparedMembers.add(currentMember);
                }
            }
            //String[] value = (String[])entry.getValue();
        }

        return comparedMembers;
    }

}
