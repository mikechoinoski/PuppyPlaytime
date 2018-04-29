# Design/Code Review 3

## Project: DonationTracker

### Developer: Justin Laabs

#### Reviewer: Michael Choinoski

|Category|Criteria|Rating/Comments|
|--------|---------|---------------|
|**Project Overview**|| |
||Which planned functionality has been met? | CRUD has been implemented for Items. Searching and deleting a user. Signing up a user.  |
||What planned functionality has not been met? | It appears all of the main functionality is at least started at the moment. |
||Describe the GitHub history and what it demonstrates about the project progress during the semester.| There are not a lot of commits, time log doesn't show a lot of time spent on the project and the project plan doesn't seem to have been followed closely.|
||Describe how peer and instructor feedback/recommendations were incorporated into the project.| It might be good to review the issues that are open as all the items are still open. Tips about the GenericDao and Rest services haven't been implemented.|
||Other comments/notes?| |
|**JSPs**|| |
||Evaluate the JSPs for templating, business logic, data validation, overall look and feel.| Templating is used for all jsps. Good amount of business logic in the jsps for signup, adding and editing charity items and searching users. Look of the jsps was basic at the time of the review and could be improved. Need to add data validation. |
||Other comments/notes?| |
|**Java code quality**|Evaluate the code quality for the following and identify specific areas for improvement (class, method or line number) <li>single-purpose methods <li>well-structured project <li>descriptive naming of packages, classes, methods, variables <li>classes appropriately-sized  (no monster classes) <li> CPD (copy paste detection, meaning are the same lines of code repeated?) <li>are there candidates for super/subclass relationships, abstract classes, interfaces? <li>are any values hard-coded that should be in a properties file? <li>proper exception handling <li>proper error reporting to the user <li> code documentation | All classes seem to the sized nicely. Code is structured well. Names for the classes seem to be good. There are hardcoded values like the http link for the API service, could add a properties file. SearchUser and ToProfileServlet seem to have duplicate code relating to the API service which might be able to go in another class. |
||Other comments/notes?| |
|**Logging**|Evaluate the use of logging, for example:<li>appropriate use of logging statements in the code for error logging and debugging <li>logging levels used - info, debug, error <li> no occurrences of  System.out.printlns or printStackTrace() <li> logging works on AWS deploy | It seems like log4j might be set up to use as I see the properties file for it but I don't notice any use of logging in the application.
||Other comments/notes?| |
|**Unit Tests**|Evaluate the unit tests, for example: <li>tests are truly a unit test rather than a high level functional test <li>test data is appropriately cleaned up or handled <li> there is full coverage of methods that perform business logic or utility functions <li>redundant code is eliminated by using set up and tear down methods, i.e., @Before, @After | DAOs are tested thoroughly and there is a test for the service. I didn't notice any business logic classes that needed to be tested as there were mostly servlets doing the work.|
||Other comments/notes?| |
|**Security**|Evaluate authentication/authorization:| Yes, there was a log in for user and admin. There is a login jsp for the tomee form authentication.|
||Is it implemented correctly and working locally as well as on AWS?| Was working locally but could not check on AWS. |
||Other comments/notes?| |
|**Web Service/API integration**|Evaluate the service/api integration, for example: <li> Which service/api is implemented? <li>How is  error handling of the service implemented? For example, what happens if the service is not available?| As of the review it was not implemented but Charity Navigator integration looks to be started because it has classes generated with robopojogenerator, there are a few classes for the service as well. |
||Other comments/notes?| |
|**Independent research topic**| What is the independent research topic?| Did not have a topic picked yet. |
||Is the independent research topic/technique implemented in the project?| No |
||Other comments/notes?| |
|**Deployment**| Has the application been successfully deployed to AWS?| Yes |
||Is the hosted application fully functioning?| Not functioning at this time. Possibly have to redeploy or restart the instance to get it working again.|
||Other comments/notes?| |
  