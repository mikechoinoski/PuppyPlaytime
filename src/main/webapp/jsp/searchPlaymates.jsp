<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">
    <br>

    <center>

        <h2>Search Results: </h2>

        <form action="SearchPlaymates" class="form-inline"/>

        <table class="searchtable">
            <tr>
                <th>Minimum<br>Age</th>
                <th>Maximum<br>Age</th>
                <th>Minimum<br>Weight</th>
                <th>Maximum<br>Weight</th>
                <th>Gender</th>
                <th>Fixed</th>
            </tr>
            <tr>
                <td>
                    <input type="number" name="${currentCriteria.minimumAge}" min="0" max="30"
                           value="${currentCriteria.maximumAge}" style="width:40px">
                </td>
                <td>
                    <input type="number" name="memberWeight${currentCriteria.maximumAge}" min="0" max="30"
                           value="${currentCriteria.maximumAge}" style="width:40px">
                </td>
                <td>
                    <input type="number" name="${currentCriteria.minimumWeight}" min="0" max="300"
                           value="${currentCriteria.minimumWeight}" style="width:40px">
                </td>
                <td>
                    <input type="number" name="memberWeight${currentCriteria.maximumWeight}" min="0" max="300"
                           value="${currentCriteria.maximumWeight}" style="width:40px">
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
                    <select name="${currentCriteria.intact}" style="width:70px">
                        <option value="Both"
                                <c:if test="${currentCriteria.intact == 'Both'}">
                                    selected="selected"
                                </c:if>
                        >Both</option>
                        <option value="Yes"
                                <c:if test="${currentCriteria.intact == 'Yes'}">
                                    selected="selected"
                                </c:if>
                        >Yes</option>
                        <option value="No"
                                <c:if test="${currentCriteria.intact == 'No'}">
                                    selected="selected"
                                </c:if>
                        >No</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan=2 align="center">
                    <input type="submit" value="Submit Search" />
                </td>
            </tr>
        </table>

        <table>
            <tr>
                <td colspan=2><u>Results to Return:</u></td>
            </tr>
            <tr>
                <td colspan=2 align="center">All
                    <input type="radio" name="searchType"
                           value="Return All"/>
                    Size
                    <input type="radio" name="searchType"
                           value="Search by Size"/>
                    Gender <input type="radio" name="searchType"
                                  value="Search by Gender" /><br><br>
                </td>
            </tr>
            <tr>
                <td>Search Term: </td>
                <td><input type="text" name="searchTerm"
                           placeholder="Enter Text" /></td>
            </tr>
            <tr>
                <td colspan=2 align="center">
                    <input type="submit" value="Submit Search" />
                </td>
            </tr>
        </table>
        <br><br>
        <table>
            <tr>
                <td align="center">
                    <font color="#f4a460" size="3">Size&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
                </td>
                <td align="center">
                    <font color="#ff4500" size="3">Age&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
                </td>
                <td align="center">
                    <font color="blue" size="3">Gender&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
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
                                <font color="#f4a460" size="2"><b>X</b><br></font>
                            </c:if>
                            <c:if test="${singleMember.size == 'XS'}">
                                <font color="#f4a460" size="2"><b>S</b></font>
                            </c:if>
                            <c:if test="${singleMember.size == 'S'}">
                                <font color="#f4a460" size="3"><b>S</b></font>
                            </c:if>
                            <c:if test="${singleMember.size == 'M'}">
                                <font color="#f4a460" size="4"><b>M</b></font>
                            </c:if>
                            <c:if test="${singleMember.size == 'L'}">
                                <font color="#f4a460" size="5"><b>L</b></font>
                            </c:if>
                            <c:if test="${singleMember.size == 'XL'}">
                                <font color="#f4a460" size="6"><b>X</b><br></font>
                            </c:if>
                            <c:if test="${singleMember.size == 'XL'}">
                                <font color="#f4a460" size="6"><b>L</b></font>
                            </c:if>
                            <br><br>
                            <font color="#ff4500" size="5"><b>${singleMember.age}</b></font>
                        </td>
                        <td width="130" align="center">
                            <c:choose>
                                <c:when test="${empty singleMember.pictureFilename}">
                                    <img src="./uploadedPhotos/default_dog.png" height="130" width="130">
                                </c:when>
                                <c:otherwise>
                                    <img src="./uploadedPhotos/${singleMember.pictureFilename}"
                                         height="130" width="130">
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td width="35" align="center">
                            <c:if test="${singleMember.sex.toString() == 'M'}">
                                <font color="blue" size="5"><b>M</b></font>
                            </c:if>
                            <c:if test="${singleMember.sex.toString() == 'F'}">
                                <font color="#ff1493" size="5"><b>F</b></font>
                            </c:if><br><br>
                            <c:if test="${!singleMember.intact}"><font color="green" size="5"><b>+</b></font></c:if>
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