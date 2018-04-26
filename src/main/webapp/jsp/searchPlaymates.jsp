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
        <c:forEach var="userMembers" items="${members}">
            <div class="rcorners">
                <table>
                    <tr>
                        <td><br>
                            Name: "${currentUser.name}"<br>
                            Size: "${currentUser.size}"<br>
                            Breed: "${currentUser.breed}"
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${empty userMembers.pictureFilename}">
                                    <img src="./uploadedPhotos/default_dog.png" height="80" width="80">
                                </c:when>
                                <c:otherwise>
                                    <img src="./uploadedPhotos/${userMembers.pictureFilename}" height="80" width="80">
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><br>
                            Age: "${currentUser.name}"<br>
                            Gender: "${currentUser.size}"<br>
                            Intact: "${currentUser.breed}"
                        </td>
                    </tr>
                </table>
            </div>
        </c:forEach>

    </center>

<c:import url="/jsp/page_bottom.jsp" />