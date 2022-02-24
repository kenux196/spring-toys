FROM adoptopenjdk:11-jre-alpine
ENV APP_HOME=/user/app
WORKDIR $APP_HOME
COPY build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]