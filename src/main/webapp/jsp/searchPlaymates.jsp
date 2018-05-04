<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content" align="center">
    <br>

        <h2>Find Playmates</h2>

        <form action="SearchPlaymates" class="form-inline"/>

        <table class="searchtable">
            <tr>
                <th>Minimum<br>Size</th>
                <th>Maximum<br>Size</th>
                <th>Minimum<br>Age</th>
                <th>Maximum<br>Age</th>
                <th>Gender</th>
                <th>Fixed</th>
            </tr>
            <tr>
                <td>
                    <select name="minimumSize" style="width:70px">
                        <option value="XS"
                                <c:if test="${currentCriteria.minimumSize == 'XS'}">
                                    selected="selected"
                                </c:if>
                        >XS</option>
                        <option value="S"
                                <c:if test="${currentCriteria.minimumSize == 'S'}">
                                    selected="selected"
                                </c:if>
                        >S</option>
                        <option value="M"
                                <c:if test="${currentCriteria.minimumSize == 'M'}">
                                    selected="selected"
                                </c:if>
                        >M</option>
                        <option value="L"
                                <c:if test="${currentCriteria.minimumSize == 'L'}">
                                    selected="selected"
                                </c:if>
                        >L</option>
                        <option value="XL"
                                <c:if test="${currentCriteria.minimumSize== 'XL'}">
                                    selected="selected"
                                </c:if>
                        >XL</option>
                    </select>
                </td>
                <td>
                    <select name="maximumSize" style="width:70px">
                        <option value="XS"
                                <c:if test="${currentCriteria.maximumSize == 'XS'}">
                                    selected="selected"
                                </c:if>
                        >XS</option>
                        <option value="S"
                                <c:if test="${currentCriteria.maximumSize == 'S'}">
                                    selected="selected"
                                </c:if>
                        >S</option>
                        <option value="M"
                                <c:if test="${currentCriteria.maximumSize == 'M'}">
                                    selected="selected"
                                </c:if>
                        >M</option>
                        <option value="L"
                                <c:if test="${currentCriteria.maximumSize == 'L'}">
                                    selected="selected"
                                </c:if>
                        >L</option>
                        <option value="XL"
                                <c:if test="${currentCriteria.maximumSize == 'XL'}">
                                    selected="selected"
                                </c:if>
                        >XL</option>
                    </select>
                </td>
                <td>
                    <input type="number" name="minimumAge" min="0" max="30"
                           value="${currentCriteria.minimumAge}" style="width:40px">
                </td>
                <td>
                    <input type="number" name="maximumAge" min="0" max="30"
                           value="${currentCriteria.maximumAge}" style="width:40px">
                </td>
                <td>
                    <select name="gender" style="width:90px">
                        <option value="Both"
                                <c:if test="${currentCriteria.gender == 'Both'}">
                                    selected="selected"
                                </c:if>
                        >Both</option>
                        <option value="Male"
                                <c:if test="${currentCriteria.gender == 'Male'}">
                                    selected="selected"
                                </c:if>
                        >Male</option>
                        <option value="Female"
                                <c:if test="${currentCriteria.gender == 'Female'}">
                                    selected="selected"
                                </c:if>
                        >Female</option>
                    </select>
                </td>
                <td>
                    <select name="fixed" style="width:70px">
                        <option value="Both"
                                <c:if test="${currentCriteria.fixed == 'Both'}">
                                    selected="selected"
                                </c:if>
                        >Both</option>
                        <option value="Yes"
                                <c:if test="${currentCriteria.fixed == 'Yes'}">
                                    selected="selected"
                                </c:if>
                        >Yes</option>
                        <option value="No"
                                <c:if test="${currentCriteria.fixed == 'No'}">
                                    selected="selected"
                                </c:if>
                        >No</option>
                    </select>
                </td>
                <td align="center">
                    <input type="submit" class="button" value="Search" />
                </td>
            </tr>
        </table>
        </form>


        <br><br>
        <form action="CreatePlaydate" class="form-inline"/>
        <table>
            <tr>
                <td align="center">
                    <font color="#b8860b" size="3">Size&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
                </td>
                <td align="center">
                    <font color="darkred" size="3">Age&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
                </td>
                <td align="center">
                    <font color="blue" size="3">Gender</font>
                    <font color="black" size="3">/</font>
                    <font color="#ff1493" size="3">Female&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
                </td>
                </td>
                <td align="center">
                    <font color="green" size="3">Fixed</font>
                </td>
            </tr>
        </table>
        <br><br>
        <c:forEach var="singleMember" items="${searchMembers}">
            <c:if test="${singleMember.pack.packNumber != userPack.packNumber}">
                <div class="memberContainer">
                    <table>
                        <tr>
                            <td colspan="3" align="center"><b>${singleMember.name}</b></td>
                        </tr>
                        <tr>
                            <td width="40" align="center">
                                <c:if test="${singleMember.size == 'XS'}">
                                    <font color="#b8860b" size="2"><b>X</b><br></font>
                                </c:if>
                                <c:if test="${singleMember.size == 'XS'}">
                                    <font color="#b8860b" size="2"><b>S</b></font>
                                </c:if>
                                <c:if test="${singleMember.size == 'S'}">
                                    <font color="#b8860b" size="3"><b>S</b></font>
                                </c:if>
                                <c:if test="${singleMember.size == 'M'}">
                                    <font color="#b8860b" size="4"><b>M</b></font>
                                </c:if>
                                <c:if test="${singleMember.size == 'L'}">
                                    <font color="#b8860b" size="5"><b>L</b></font>
                                </c:if>
                                <c:if test="${singleMember.size == 'XL'}">
                                    <font color="#b8860b" size="6"><b>X</b><br></font>
                                </c:if>
                                <c:if test="${singleMember.size == 'XL'}">
                                    <font color="#b8860b" size="6"><b>L</b></font>
                                </c:if>
                                <br><br>
                                <font color="darkred" size="5"><b>${singleMember.age}</b></font>
                            </td>
                            <td width="130" align="center">
                                <c:choose>
                                    <c:when test="${empty singleMember.pictureFilename}">
                                        <input type="checkbox" name="memberCheckBox${singleMember.packMemberNumber}" id="member${singleMember.packMemberNumber}" value="memberCheckBoxValue${singleMember.packMemberNumber}" style="display:none">
                                        <label for="member${singleMember.packMemberNumber}" style="background:url(./uploadedPhotos/default_dog.png) no-repeat; background-size: cover; background-position: center;"></label>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" name="memberCheckBox${singleMember.packMemberNumber}" id="member${singleMember.packMemberNumber}" value="memberCheckBoxValue${singleMember.packMemberNumber}" style="display:none">
                                        <label for="member${singleMember.packMemberNumber}" style="background:url('./uploadedPhotos/${singleMember.pictureFilename}') no-repeat; background-size: cover; background-position: center;"></label>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td width="40" align="center">
                                <c:if test="${singleMember.sex.toString() == 'M'}">
                                    <font color="blue" size="5"><b>M</b></font>
                                </c:if>
                                <c:if test="${singleMember.sex.toString() == 'F'}">
                                    <font color="#ff1493" size="5"><b>F</b></font>
                                </c:if>
                                <c:if test="${!singleMember.intact}"><br><br><font color="green" size="5"><b>+</b></font></c:if>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3" align="center"><b>${singleMember.breed}</b></td>
                        </tr>
                    </table>
                </div>
            </c:if>
        </c:forEach><br><br>
        <input type="submit" class="button" value="Create Playdate" />
        </form>

<c:import url="/jsp/page_bottom.jsp" />