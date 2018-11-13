# Preference service

The task:

·  Create a Restful service named Preference Service which will connect the database in order to persist for a given
 user property "show_one_time_confirmation_page". `[DONE]`

·  Preference Service API should take 3 attributes as an input: ClientID, ApplicationID, PropertyName. `[DONE]`

·  Preference Service should use Post method `[DONE]`

·  The response should return the value of the show_one_time_confirmation_page `[?] Something is not adding up here`

·  The should work as follow: `[DONE]`

    o   First request for a given ClientID, ApplicationID should return true 

    o   All next request should return false

·  Service should validate the request `[DONE]`

·  Service should use Spring Boot and Spring Data `[DONE]`

·  Service should create an entry in database for every new client and application ID - no validation if client or 
application ID exists would be required. `[DONE]`

·  Project should use Maven or Gradle as build tool. `[MAVEN]`


## Testing endpoint

curl -X POST http://localhost:8080/api/v1/preferences -d "{\"applicationId\":1, \"clientId\":2, \"propertyName\":\"test\"}" -H "Content-Type: application/json"

## Other

·  The application is using an in memory H2 Database for persisting preferences.

·  The database is initialized with some data during application bootstrap only for testing reasons. 

·  H2-console is enabled and can be found on: http://localhost:8080/h2-console

·  The application is using as composite primary key the ApplicationID and ClientID.


