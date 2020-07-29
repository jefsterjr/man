API to query installable and incompatible features on trucks.

### Pre-requisites

- Docker
- JDK 11
- Maven

### Installation

In applications(fota and file-processor) directory run:

```
mvn clean install package
```
### Change file directory

Open .env file and change DIRECTORY_PATH={your-new-directory-path} 

### Running Tests

To run the tests, acess the project folder(fota or file-processor) and run:
```
mvn test
```
### Usage

In the application directory folder run to start the container
```
 docker-compose up
```
To the stop the container and delete the image.

```
 docker-compose down -v --rmi local
```

### Swagger
```
http://localhost:8400/fota/swagger-ui.html
```

### Database Diagram
![alt text](https://github.com/jefsterjr/man/blob/master/man.png)
