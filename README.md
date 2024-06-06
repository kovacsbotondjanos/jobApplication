# jobApplication

Database:
-
- sqlite database with jpa. 
- 3 tables: 
  - Position: The position table contains the name of the position, the id of the location and the client who posted it.
  - Client: The client table contains all the info about the client, their name, email address and api key
  - Location: The location table contains the name of the location. It might be a good idea to create this table, so we won't have to store the names of every city, and we can have additional information about every location
  - relationships: Position-Client: M-1, Position-Location: M-1

Validation errors:
-
- RuntimeExceptions: 
  - ClientAlreadyExistsException(http status: 409 CONFLICT)
  - InvalidFormDataException(http status: 422 UNPROCESSABLE_ENTITY)
  - UnauthenticatedException(http status: 401 UNAUTHORIZED)

Running the application:
-
- start: <b>mvn spring-boot:run</b> 
- after starting the application the DatabaseSeeder class will check if the client table is empty. If yes then it will generate dummy data with faker.
- swagger is available at http://localhost:8080/swagger-ui/index.html

Ideas for improvement:
- 
- Configure Spring security with sessions, so the client won't have to send the api key every single time
- Add a password to the client so in the future they will be able to fetch the api key after logging in
- Manage users and applications, CVs, cover letters, maybe some level of customization for the job listings, f.e. photos
- Dockerize the application, create CI