# SQL Proxy

Allows connecting to an SQL database/datasource over HTTP without the need of drivers.

## Getting Started

Clone the repo.

### Prerequisites

Install Java, Gradle, and Docker

### Installing

Step 1: Build with Gradle

```
./gradlew build && java -jar build/libs/gs-spring-boot-docker-0.1.0.jar   
```

Step 2: Copy the build libs

```
mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)
```

Step 3. Build the Docker Image 

```
docker build --build-arg JAR_FILE=build/libs/*.jar -t springio/gs-spring-boot-docker .

```

Step 4: Run the container
```
docker run -p 8080:8080 -t springio/gs-spring-boot-docker
```

## Development

Using IntelliJ with Gradle just run the application.

### Using the Proxy

Step 1:  Connect 

* using POSTMAN or CURL (or whatever http client you want)

POST to localhost:8080/connect

```
{
 "username": "[database user]",
 "password": "[database password]",
 "type": "[database type (sqlserver, oracle, mysql, postgres, snowflake, etc)]",
 "host": "[database host]",
 "port": "[database port]",
 "database": "[defaut database]",
 "params": {"[custom]": "[value]", "[custom2]": "[value]", ...}
}
```

Sql Server Example
```
{
 "username": "sa",
 "password": "abcd123",
 "type": "sqlserver",
 "host": "localhost",
 "port": "1433",
 "database": "",
}
```
 Step 2: Query
 
 http://localhost:8080/query?sql=[sql statement]
 
 Example (sql server):
 
 http://localhost:8080/query?sql=select * from spt_monitor
 
## Deployment

Deploy the container with Docker, Kubernetes, Portainer, etc

## Built With

* Gradle
* Docker
* Spring Boot

## Contributing


## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Scott Lepper**

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments
https://spring.io/guides/gs/spring-boot-docker/

