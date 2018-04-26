<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">
    <br>

    <center>

        <h2>Search Results: </h2>

        <form action="SearchPlaymates" class="form-inline"/>

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
                    <font color="brown" size="3">Size&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
                </td>
                <td align="center">
                    <font color="darkred" size="3">Age&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
                </td>
                <td align="center">
                    <font color="tan" size="3">Gender&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>
                </td>
                </td>
                <td align="center">
                    <font color="green" size="3">Intact</font>
                </td>
            </tr>
        </table>
        <br><br>
        <c:forEach var="singleMember" items="${members}">
            <div class="blackborder">
                <table>
                    <tr>
                        <td colspan="5" align="center"><b>${singleMember.name}</b></td>
                    </tr>
                    <tr>
                        <td width="50" align="center">
                            <font color="brown" size="5"><b>${singleMember.size}</b></font>
                        </td>
                        <td width="50" align="center">
                            <font color="darkred" size="5"><b>${singleMember.age}</b></font>
                        </td>
                        <td width="100" align="center">
                            <c:choose>
                                <c:when test="${empty singleMember.pictureFilename}">
                                    <img src="./uploadedPhotos/default_dog.png" height="80" width="80">
                                </c:when>
                                <c:otherwise>
                                    <img src="./uploadedPhotos/${singleMember.pictureFilename}" height="80" width="80">
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td width="50" align="center">
                            <font color="tan" size="5"><b>${singleMember.sex}</b></font>
                        </td>
                        </td>
                        <td width="50" align="center"><b>
                            <font color="green" size="5">
                                <c:if test="${singleMember.intact}">Y</c:if>
                                <c:if test="${!singleMember.intact}">N</c:if>
                            </b></font>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="5" align="center"><b>${singleMember.breed}</b></td>
                    </tr>
                </table>
            </div>
        </c:forEach>

    </center>

<c:import url="/jsp/page_bottom.jsp" />