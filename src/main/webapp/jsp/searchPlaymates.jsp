<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">
    <br>

    <center>

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
                    <select name="${currentCriteria.minimumSize}" style="width:70px">
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
                    <select name="${currentCriteria.maximumSize}" style="width:70px">
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
                    <input type="number" name="${currentCriteria.minimumAge}" min="0" max="30"
                           value="${currentCriteria.minimumAge}" style="width:40px">
                </td>
                <td>
                    <input type="number" name="${currentCriteria.maximumAge}" min="0" max="30"
                           value="${currentCriteria.maximumAge}" style="width:40px">
                </td>
                <td>
                    <select name="${currentCriteria.gender}" style="width:90px">
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
                    <select name="${currentCriteria.fixed}" style="width:70px">
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

        <br><br>
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
        <c:forEach var="singleMember" items="${members}">
            <div class="playmateContainer">
                <table>
                    <tr>
                        <td colspan="3" align="center"><b>${singleMember.name}</b></td>
                    </tr>
                    <tr>
                        <td width="35" align="center">
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
                            <a href="createPlaydate?selectedPackMember=${singleMember.packMemberNumber}"/>
                            <c:choose>
                                <c:when test="${empty singleMember.pictureFilename}">
                                    <img src="./uploadedPhotos/default_dog.png" height="130" width="130">
                                </c:when>
                                <c:otherwise>
                                    <img src="./uploadedPhotos/${singleMember.pictureFilename}"
                                         height="130" width="130">
                                </c:otherwise>
                            </c:choose>
                            </a>
                        </td>
                        <td width="35" align="center">
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
        </c:forEach>

    </center>

<c:import url="/jsp/page_bottom.jsp" />