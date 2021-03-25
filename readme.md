Docker run Mysql container and SpringBoot app using docker network

docker network create todo-mariadb-network

docker run --detach --env MYSQL_ROOT_PASSWORD=passroot --env MYSQL_USER=usertodos --env MYSQL_PASSWORD=passtodos --env MYSQL_DATABASE=todos --name my_maria_cristina --publish 3306:3306 --network=todo-mariadb-network --volume=todo-mariadb-volume:/var/lib/mysql mariadb:latest

docker container run -p 8080:8080 --network=todo-mariadb-network -e RDS_HOSTNAME=my_maria_cristina niculesculaurentiu/todo-spring-boot:0.0.1


Docker commands:

docker container run -p 5000:5000 -m 512m —cpu-quota 5000 -d —restart=always in28min/todo-rest-api-h2:1.0.0.RELEASE
docker pull imageID
docker tag imageID1 imageID2
docker images
docker image remove imageID
docker image inspect imageID
docker image history imageID
docker container ls -a
docker container logs -f containerID
docker container stop containerID
docker container prune
docker container pause containerID
docker container unpause containerID
docker search mariadb

docker events
docker top containerID
docker stats
docker system df

docker run -dit openjdk:8-jdk-alpine = runs the container in a detached mode with interactive shell
docker container cp finalName.jar container_name:/tmp = copies the jar into the running container under container dir /tmp
docker container exec container_name ls /tmp = execute a command using the container’s interactive shell
docker container commit container_name Laurentiu/hello-rest-api:manual.1 = creates a docker image
docker container commit —change=‘CMD [“java”, ”-jar”, ”/tmp/jarfile.jar”]’ container_name image_name

Dockerfile content

FROM openjdk:8-jdk-alpine - from which image the container should be created
ADD out/artifacts/*.jar  - copy the jars to the new image root folder
ADD target/01-hello-world-rest-api.jar /
ENTRYPOINT [“sh”, “-c”, “java -jar /01-hello-world-rest-api.jar”]  - execute the command when the container is started
CMD java -jar /01-hello-world-rest-api.jar  - this worked for me! :)


Build of the docker image from docker file
docker build -t lau/hello-world-rest-api:dockerfile1 .
docker run -p 5001:8080 lau/hello-world-rest-api:dockerfile1   
The format of the --publish (-p) command is [host port]:[container port]

mvn -e package -DskipTests  = build the jar and the docker image
mvn clean package -DskipTests


docker push dockerUserId/imageID:tag - pushes the image on the docker hub for the userID

- Run docker with mariaDB server:

docker run -e MYSQL_ROOT_PASSWORD=rootdummy -e MYSQL_DATABASE=todos -e MYSQL_USER=usertodos -e MYSQL_PASSWORD=passtodos -p 3306:3306 -d mariadb:latest

- Then open mysql shell from the terminal: mysqlsh

- Then connect from mysql shell to the mariaDB server:

\connect usertodos@localhost:3306 

