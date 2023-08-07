FROM openjdk:8-jre-slim

ENV TZ=Asia/Shanghai

RUN mkdir -p /ruoyi/server
RUN mkdir -p /ruoyi/server/logs
RUN mkdir -p /ruoyi/server/temp
RUN chmod 777 /ruoyi/server/logs


WORKDIR /ruoyi/server

ENV SERVER_PORT=8088

EXPOSE ${SERVER_PORT}

ADD ./target/ruoyi-admin.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=dev", "-Dserver.port=${SERVER_PORT}","-jar", "app.jar"]
