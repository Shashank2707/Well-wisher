FROM maven AS build

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
#
# Package stage
#
FROM openjdk:17

COPY --from=build /home/app/target/*.jar /usr/local/lib/producer.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/producer.jar"]
EXPOSE 8080/tcp