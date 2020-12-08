FROM openjdk:8-jre-alpine

EXPOSE 8080
EXPOSE 8000

ARG JAR_FILE=new-merchant-quote-service-api/target/new-merchant-quote-service-api-0-SNAPSHOT-exec.jar
COPY ${JAR_FILE} app.jar

ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,address=8000,server=y,suspend=n"
ENTRYPOINT exec java ${JAVA_OPTS} -jar app.jar