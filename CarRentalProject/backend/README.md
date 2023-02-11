## WONDERLAND CAR RENTAL SYSTEM's backend

### !Important

The backend application follows the n-tier architecture with emphasis of the following.
1. Separation Of Concern.
2. Dependency Injection thanks to Spring boot.

### Technologies used.

- The backend is built using [Java (Spring boot)](https://spring.io/projects/spring-boot) in the backend.
- It is running on the [MySQL DB](https://www.mysql.com/).
- The backend image can be created using [docker](https://www.docker.com/).

-The front is built using React(javaScript library)
### Prerequisites.

- Environments and/or programming language prerequisites.
    - Java JDK: [JDK](https://www.oracle.com/java/technologies/downloads/) preferably download _jdk
      19 but minimum Jdk 17_ ([download link](https://www.oracle.com/java/technologies/downloads/))
- Editors or IDEs
    - **IDEs/editors:**
    - _Preferably_: [Intellij](https://www.jetbrains.com/idea/download/)
      link ([download link](https://www.jetbrains.com/idea/))
    - _Alternative_: [Eclipse](https://www.eclipse.org/downloads/)
      link ([download link](https://www.eclipse.org/downloads/))

### Getting started.

- install backend dependencies by running the command `mvn install`
- alternatively open the backend with your either eclipse or intellij and the dependencies will be installed
  automatically.

### Running the Application.

**Run the application.**

Choose one of the following alternatives to run the backend on your laptop.

- Using Docker and Docker compose (make sure you have docker installed):
    ````
      docker compose command
      - docker compose up
    ````

- Manually (make sure you have mysql installed and a database setup for the application):
    ````
    run the backend with intellij IDE.
  
        - Open the backend project with intellij
        - wait for the project (maven) dependencies to be installed / resolved.
        - click on the green play button in the top right corner on the IDE to start the backend.
  
    run the backend in terminal:
  
        - mvn spring-boot:run
  
        Alternatively.
        - java -jar target/app-0.0.1-SNAPSHOT.jar
        - mvn spring-boot:run
    ````
