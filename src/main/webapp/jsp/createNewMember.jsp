<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">
    <center>
    <h2>Add a Pack Member</h2>
    <br><br>

    <form method="post" action="UploadServlet" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Name</td>
            <td align="center">
                <input type="text" name="memberName">
            </td>
        </tr>
        <tr>
            <td>Breed</td>
            <td align="center">
                <input type="text" name="memberBreed">
            </td>
        </tr>
        <tr>
            <td>Weight</td>
            <td align="center">
                <input type="number" name="memberWeight" min="0" max="300">
            </td>
        </tr>
        <tr>
            <td>Date of Birth</td>
            <td align="center">
                <input type="date" name="memberDateOfBirth">
            </td>
        </tr>
        <tr>
            <td>Gender</td>
            <td align="center">
                <label for="memberGenderMale">Male</label>
                <input type="radio" id="memberGenderMale" name="memberGender" value="male"/>
                <label for="memberGenderFemale">Female</label>
                <input type="radio" id=memberGenderFemale name="memberGender" value="female" />
            </td>
        </tr>
        <tr>
            <td>Intact</td>
            <td align="center">
                <label for="memberIntactYes">Yes</label>
                <input type="radio" id="memberIntactYes" name="memberIntact" value="yes"/>
                <label for="memberIntactNo">No</label>
                <input type="radio" id="memberIntactNo" name="memberIntact" value="no" />
            </td>
        </tr>
        <tr>
            <td colspan="2"><br></td>
        </tr>
        <tr>
            <td>Picture</td>
            <td align="center">
                <input type="file" name="dataFile" id="fileChooser"/>
            </td>
        </tr>
        <tr>
            <td colspan="2"><br></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="All done!" /></td>
        </tr>
    </table>
    <br><br>

    </form>

</center>
<c:import url="/jsp/page_bottom.jsp" />