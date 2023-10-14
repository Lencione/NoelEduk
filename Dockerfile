FROM openjdk:11

WORKDIR /var/www/noel

RUN apt update && apt install maven -y && mvn -v

VOLUME /tmp

COPY target/NoelProject-0.0.1-SNAPSHOT.jar /var/www/noel/spring-noel.jar


RUN groupadd -g 1000 www
RUN useradd -u 1000 -ms /bin/bash -g www www
RUN chmod -R 777 /var/www/noel
USER www

ENTRYPOINT ["java","-jar","spring-noel.jar"]

