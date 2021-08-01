FROM openjdk:16-jdk-alpine
LABEL maintainer="philippe.wanner+github@tomylab.com"
VOLUME /tmp
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
EXPOSE 8081
ADD helix-root/target/helix-root.jar app.jar
