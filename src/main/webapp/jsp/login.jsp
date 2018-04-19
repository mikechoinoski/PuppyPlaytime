<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/jsp/page_top.jsp" />

<div class="image2"></div>
<div class="content">
    <br>
<center>

    <h2>Welcome to Puppy Playtime!</h2>
    <br><br>

    <h3><u>Please Log In to View Your Pack</u></h3>

    <br>

    <FORM ACTION="j_security_check" METHOD="POST">
        <TABLE>
            <TR>
                <TD>User name: </TD>
                <TD><INPUT TYPE="TEXT" NAME="j_username"></TD>
            </TR>
            <TR>
                <TD>Password: </TD>
                <TD><INPUT TYPE="PASSWORD" NAME="j_password"></TD>
            </TR>
            <TR>
                <TD colspan="2"></TD>
            </TR>
            <TR>
                <TD align="center" colspan="2"><INPUT TYPE="SUBMIT" VALUE="Log In"></TD>
            </TR>
        </TABLE>
    </FORM>



<br><br><br>

<h3><u>Would you like to meet new packs?</u></h3><br>
<h3><a href="createNewPack">Create you pack today and come visit our Playground!</a></h3>
</center>
<c:import url="/jsp/page_bottom.jsp" />