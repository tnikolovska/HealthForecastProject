FROM openjdk:8-jdk-alpine
ADD target/healthforecastproject-0.0.1-SNAPSHOT.jar healthforecastproject-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","healthforecastproject-0.0.1-SNAPSHOT.jar" ]
