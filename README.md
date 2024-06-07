# jobApplication

Technologies used:
-
- Java Spring Boot
- Mockito for testing
- Java faker for database seeder
- Lombok
- Swagger
- Sqlite
- JPA

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

Endpoints:
-
- POST /client: accessible without api token, requires a name (String, less than 100 characters otherwise returns http status 422) and an email address(in valid format otherwise returns http status 422), if client with the same name or email exists then returns with http status 409
- GET /position/:id: requires api key, returns the positionDTO if the client has a position with the same id, otherwise returns http status 404 if user doesn't have a position with the same id, if no api key was provided returns http status 401
- GET /position/search: requires api key, returns a list of links, the first half of the list consists of elements where the beginning of the title matched, the last part consists of titles where the substring was found in the word, if no apiKey provided returns http status 401
- POST /position: requires api key in the header, otherwise returns 401, else creates a new position, if a new location name was provided adds a new location to the location table too 

Running the application:
-
- start:
  - Windows: <b>.\run.ps1</b> 
  - Linux: <b>./run.sh</b>
- after starting the application the DatabaseSeeder class will check if the client table is empty. If yes then it will generate dummy data with faker.
- swagger is available at http://localhost:8080/swagger-ui/index.html

Ideas for improvement:
- 
- Configure Spring security with sessions, so the client won't have to send the api key every single time
- Add a password to the client so in the future they will be able to fetch the api key after logging in
- Store the api key of the user more securely, maybe encode it with BCryptPasswordEncoder
- Manage users and applications, CVs, cover letters, maybe some level of customization for the job listings, f.e. photos
- Dockerize the application, create CI
- Write more tcs, maybe some integration tcs
