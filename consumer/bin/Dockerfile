FROM maven AS build

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:17

ENV ACTIVE_PROFILE=local \
    RABBITMQ_HOST=localhost \
    RABBITMQ_PORT=5672 \
    RABBITMQ_VIRTUALHOST=virtualhost \
    RABBITMQ_USERNAME=guest \
    RABBITMQ_PASSWORD=guest \
    RABBITMQ_QUEUE=birthday-queue \
    RABBITMQ_EXCHANGE=birthday-exchange \
    RABBITMQ_BROADCAST_QUEUE=broadcast-queue \
    RABBITMQ_BROADCAST_EXCHANGE=broadcast-exchange \
    EMAIL=sh@gmail.com \
    EMAIL_PASSWORD=abcd

COPY --from=build /home/app/target/*.jar /usr/local/lib/consumer.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/consumer.jar"]
EXPOSE 8082/tcp
