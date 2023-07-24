FROM maven AS build

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
#
# Package stage
#
FROM openjdk:17

ENV ACTIVE_PROFILE=local \
	DB_URL=dburl \
	DB_PORT=5672 \
	DB_USERNAME=username \
	DB_PASSWORD=password \
    RABBITMQ_HOST=localhost \
    RABBITMQ_PORT=5672 \
    RABBITMQ_VIRTUALHOST=virtualhost \
    RABBITMQ_USERNAME=guest \
    RABBITMQ_PASSWORD=guest \
    RABBITMQ_QUEUE=birthday-queue \
    RABBITMQ_EXCHANGE=birthday-exchange \
    RABBITMQ_BROADCAST_QUEUE=broadcast-queue \
    RABBITMQ_BROADCAST_EXCHANGE=broadcast-exchange

COPY --from=build /home/app/target/*.jar /usr/local/lib/producer.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/producer.jar"]
EXPOSE 8080/tcp