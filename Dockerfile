FROM openjdk:8-jdk-alpine
ENV PORT 8083
EXPOSE 8083
COPY target/*.jar /opt/app.jar
WORKDIR /opt
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar