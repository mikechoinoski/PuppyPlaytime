# Application Flow

## New Pack Sign Up
        1. User chooses log in on the menu bar
        2. User selects new user
        3. User fills out the sign up form and submits
        4. Request goes to sign up servlet
        5. Servlet creates a owner object and then creates owner in the database
        6. Servlet creates objects for any pack members that were entered and creates pack members in the database
        7. Response to user confirming addition (show a message on the jsp)

## Pack Leader Sign In
        1. User chooses log in on the menu bar
        2. User enters username and password on form and submits
        3. If user is authenticated, the server will handle allowing access owner information/your pack page. 
        4. If authentication fails, show error message/page

## View your Pack       
       1. User selects Your Pack              
       2. A servlet is used to create a jsp to find pack members in the database              
       3. Pack member objects are created              
       4. A JSP is used to display the pack Members. 
       
## New Pack Member
       1. Same steps as View your pack
       2. Select add new pack member option
       3. Servlet is used to display a jsp with pack member information fields
       4. User enters information about the new pack member
       5. Servlet is used to create new pack member object from the information entered
       6. New pack member is added to the database  

## Search for Playmates
       1. Use clicks on playmates option in the menu bar
       2.

## Display Playmates

## Request Playdate

## View play areas

## View ways to play
