FROM java:8
VOLUME /tmp
ADD target/spring-reactive-mongo.jar spring-reactive-mongo.jar
RUN bash -c 'touch /spring-reactive-mongo.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spring-reactive-mongo.jar"]