# Esselunga-MUI ...

Test commit from esselunga
This is a comment for the fork

*  Compilation instructions:

The project uses [Apache Maven](https://maven.apache.org/).

You __**have**__ to create a property file called ```mui.[username].properties``` . The file can be empty
###Profiles
There are several profiles available but the default profile ("*integration*") and a profile for packaging for production ("*production*") are the ones that can be used if you're running with weblogic.

**The default profile** has **coverage tests** ([JaCoCo](https://www.eclemma.org/jacoco/)) and **unit tests** ([JUnit](https://junit.org/junit4/)) enabled. In case you need or want to package without tests please use the `-DskipTests` flag.

**The production profile** provided is supposed to be run when packaging for TEST/QA/PRODUCTION environment as it minifies CSS and JavaScripts and does not run tests nor coverage. The way to call it is by using the standard `-P` flag as in
`mvn -Pproduction package`

**The docker profile** provided is supposed to be run in the local development environment running docker (See [docker](#docker-compose-environment)). The profile reads ```docker.deploy.apps``` property to determine the deployment directory. (See [User Profile Variables](#user-profile-variables))

**The tomee profile** provided is supposed to be run into a local tomee configuration. The profile reads ```tomee.apps``` property to determine the deployment directory. (See [User Profile Variables](#user-profile-variables))


### Docker compose environment
The current environment provides a directory "compose" in which you have all the necessary stuff to run and debug the application within a docker container.
```
compose
------|apps <-- this directory is mounted as the application path for the application server
------|data <-- this directory is mounted as the data path for the database server
------|database
            |------derby-dockerfile <-- the database container description
------|logs <--
------|tomee
            |------mui-dockerfile <-- the application server container description
            |------conf <-- contains the tomee configuration
            |------derby <-- contains the derby libraries to connect to the local database                        
------|data.tar.gz <-- contains the databases
------|docker-compose.yml
```
The one thing you should change, within the docker-compose file, is the variable ```UUID``` as it contains the user id that will run both the database and the application server; this refletcs on the permissions of the exploded application pages and the new data created for the database.

The compose file exposes the following : 

| Machine Name  |      Port      |  Scope |  Accessible on |
|----------|:-------------:|------:|------:|
| database |  1527 | Access to derby database |localhost:1527|
| mui |    8080   |  Access to the application |localhost:8080|
| mui | 8000 |    Access to the debug port |localhost:8000|

###User profile variables
The file ```mui.[username].properties``` can contain any property, the only ones that are currently taken into account are 
```
tomee.apps=path/to/tomee/apps
docker.deploy.apps=path/to//compose/apps
````



