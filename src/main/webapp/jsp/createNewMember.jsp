<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image"></div>
<div class="content">
    <center>
    <h2>Add a Pack Member</h2>
    <br>

    <div class="blackborder">
        <form method="post" action="CreateMember" enctype="multipart/form-data">
        <table>
            <tr>
                <th align="left">Name</th>
                <td align="center" colspan="2">
                    <input type="text" name="memberName">
                </td>
            </tr>
            <tr>
                <th align="left">Breed</th>
                <td align="center" colspan="2">
                    <input type="text" name="memberBreed">
                </td>
            </tr>
            <tr>
                <th align="left">Weight</th>
                <td align="center" colspan="2">
                    <input type="number" name="memberWeight" min="0" max="300">
                </td>
            </tr>
            <tr>
                <th align="left">Date of Birth</th>
                <td align="center" colspan="2">
                    <input type="date" name="memberDateOfBirth">
                </td>
            </tr>
            <tr>
                <th align="left">Gender</th>
                <td align="center">
                    <label for="memberGenderMale">Male</label>
                    <input type="radio" id="memberGenderMale" name="memberGender" value="male"/>
                </td>
                <td align="center">
                    <label for="memberGenderFemale">Female</label>
                    <input type="radio" id=memberGenderFemale name="memberGender" value="female" />
                </td>
            </tr>
            <tr>
                <th align="left">Intact</th>
                <td align="center">
                    <label for="memberIntactYes">Yes</label>
                    <input type="radio" id="memberIntactYes" name="memberIntact" value="yes"/>
                </td>
                <td align="center">
                    <label for="memberIntactNo">No</label>
                    <input type="radio" id="memberIntactNo" name="memberIntact" value="no" />
                </td>
            </tr>
            <tr>
                <td colspan="3"><br></td>
            </tr>
            <tr>
                <th align="left">Picture</th>
                <td align="center" colspan="2">
                    <input type="file" name="dataFile" id="fileChooser"/>
                </td>
            </tr>
            <tr>
                <td colspan="3"><br></td>
            </tr>
            <tr>
                <td colspan="3" align="center"><input type="submit" value="All done!" /></td>
            </tr>
        </table>
        </form>
    </div>

</center>
<c:import url="/jsp/page_bottom.jsp" />