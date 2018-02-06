# Application Flow

## New Pack Sign Up
	1. User chooses log in on the menu bar
	2. User selects new user
	3. User fills out the sign up form and submits
	4. Request goes to sign up servlet
	5. Servlet creates a owner object and then creates owner in the database
	6. Response to user confirming addition (show a message on the jsp)

## Pack Leader Sign In
	1. User chooses log in on the menu bar
	2. User enters username and password on form and submits
	3. If user is authenticated, the server will handle allowing access owner information/your pack page. (Tomcat's JDBC Realm Authentication)
	4. If authentication fails, show error message/page

## New Pack Member

## View your Pack

## Search for Playmates

## Display Playmates

## Request Playdate

## View play areas

## View ways to play
