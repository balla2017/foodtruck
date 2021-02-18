Food Truck
-------------------------
Backend service to retrieve openning food truck info limit to 10.

### Building and running the project
navigate into the redfin-take-home-assignment folder.

Build the project:  
`./gradlew build`

Run :  
`./gradlew run`
if the number of results is greater than 10, press enter to see more, or input "exit".

###Write up
For data persistence, I would probably setup a job to call the Socrata API regularly, maybe daily or at a cadence that matches how frequently Socrata refreshes their own data, and I would store/cache that data locally in some way (maybe a simple nosql database like redis or a cloud service like AWS). If we are serving responses to thousands of user’s requests we would not want to have to repeatedly call the Socrata API to fill these requests. In such a case, I would not call the Socrata api with a specific query based on the current time, but instead retrieve all of their possible results for that day (or week, depending on Socrata's data refresh) and have those ready when a GET request to our service was made. This means we would need to filter for the trucks that are currently open for the user based on current time as part of our own database query. I would definitely have automated tests and take whatever other steps needed to put a continuous integration & delivery system in place, so that we could implement changes for users as features are requested without breaking everything that we already have in place. Performance-wise we would want to think about caching, page load time, and indexing the results in our database and querying the database efficiently so that we can scale the application to server our many future users.In addition, we need to instrument and monitor to ensure the health status of our API.
