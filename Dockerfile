FROM openjdk:17-jdk-alpine

ARG APP_FINAL_NAME

WORKDIR /app

COPY target/${APP_FINAL_NAME}.jar webcalendar.jar

ENTRYPOINT ["java", "-jar", "/app/webcalendar.jar"]