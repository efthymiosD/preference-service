# Preference service

The task:

·  Create a Restful service named Preference Service which will connect to the database in order to persist for a given
 user property name e.g. "show_one_time_confirmation_page".

·  Preference Service API should take 3 attributes as an input: ClientID, ApplicationID, PropertyName.

·  Preference Service should use Post method 

·  The Service should work as follows: 

    o   First request for a given ClientID, ApplicationID should return true 

    o   All next request should return false

·  Service should validate the request 

·  Service should use Spring Boot and Spring Data 

·  Service should create an entry in database for every new client and application ID - no validation if client or 
application ID exists is be required. 

·  Project should use Maven or Gradle as build tool. `[MAVEN]`


## Testing endpoint

curl -X POST http://localhost:8080/api/v1/preferences -d "{\"applicationId\":1, \"clientId\":2, \"propertyName\":\"test\"}" -H "Content-Type: application/json"

## Important information

·  The application is using an in memory H2 Database for persisting preferences.

·  The database is initialized with some data during application bootstrap mainly for testing reasons.
(See resources/data.sql)

·  H2-console is enabled and can be found on: http://localhost:8080/h2-console

·  The application is designed to use a composite primary key of ApplicationID and ClientID.


