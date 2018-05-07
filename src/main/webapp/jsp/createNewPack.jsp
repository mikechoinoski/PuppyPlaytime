<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">

    <h2 class="pageHeader">Create Your Pack</h2>
    <br><br>

    <form method="post" action="insertNewPack">
        <table>
            <tr>
                <td>Pack Name:</td>
                <td><input type="text" name="packName" value="${packName}"></td>
            </tr>
            <tr>
                <td>First Name:</td>
                <td><input type="text" name="firstName" value="${firstName}"></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><input type="text" name="lastName" value="${lastName}"></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><input type="text" name="address" value="${address}"></td>
            </tr>
            <tr>
                <td>Phone Number:</td>
                <td><input type="text" name="phoneNumber" value="${phoneNumber}"></td>
            </tr>
            <tr>
                <td>Email Address:</td>
                <td><input type="text" name="emailAddress" value="${emailAddress}"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="text" name="password" value="${password}"></td>
            </tr>
            <tr>
                <td colspan="2"><br></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Create New Pack" /></td>
            </tr>
        </table>

    </form>

    <br><br>

    <c:forEach var="oneError" items="${errorList}">
    <font color="red">${oneError}</font><br>
    </c:forEach>



<c:import url="/jsp/page_bottom.jsp" />