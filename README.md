# Mancala with Sprin Boot and Angular

For running the project jdk 1.8 is required and some IDE like IDEA or maven or docker.

Project can be build/ran in 2 ways:
1. just by using maven / IDE
    - run _mvn clean package_ from root folder
    - run _java -jar ./mcl-app/java -jar mcl-app-0.0.1-SNAPSHOT.jar_
    - access [https://localhost:8443][https://localhost:8443]
    
2. using docker
    - run _docker-compose build_ to build the project and images
    - run _docker-compose up_ to start the app in a container
    - access [https://localhost:8080][https://localhost:8080]

[https://localhost:8443]: https://localhost:8443
[https://localhost:8080]: https://localhost:8080