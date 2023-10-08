FROM openjdk:11

WORKDIR /noel

COPY target/NoelProject-0.0.1-SNAPSHOT.jar /noel/spring-noel.jar

ENTRYPOINT ["java","-jar","spring-noel.jar"]

