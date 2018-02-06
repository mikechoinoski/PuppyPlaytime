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
       5. Picture of new pack member is added and verified by Amazon Rekognition
       6. Servlet is used to create new pack member object from the information entered
       7. New pack member is added to the database   

## Search for Playmates
       1. Use clicks on playmates option in the menu bar
       2. Servlet brings up a search jsp
       3. User enters information about the type of playmate wanted
       4. Servlet is used to fetch results form database
       5. Objects are created for each dog retrieved
       6. Jsp is used to show the results

## Request Playdate
       1. User selects playmate from the search page
       2. Servlet is used to bring up the request playdate jsp
       3. User enters information for the playdate
       4. Servlet is used to create playdate object
       5. Email is sent to the requestee (need to research this)
       6. Playdate is added to the database

## Show Active Playdates
       1. User selects playdates from the menu bar
       2. Servlet retrieves playdates from the playdate table
       3. Objects are created for each playdate
       4. Jsp is used to show each playdate
       5. Any un-approved playdates will give the option of yes/no for the approval
       6. Jsp will show comfirmation message that playdate was approved

## View play areas
       1. User selects play areas from the menu bar
       2. Servlet retrieves locations from database
       3. Location objects are created
       4. Locations are mapped to a map using google maps api
       5. Servlet is used to show a jsp page with locations

## View ways to play
       1. User selects ways to play from the menu bar
       2. Servlet retrieves ways to play from database
       3. Objects for ways to play are created
       4. Jsp is used to show the ways to play
       
## Friend Add/View?  
