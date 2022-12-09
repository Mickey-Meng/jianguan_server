FROM minio/minio:RELEASE.2022-05-26T05-48-41Z

MAINTAINER Lion Li

RUN mkdir -p /ruoyi/server
RUN mkdir -p /ruoyi/server/logs
RUN mkdir -p /ruoyi/server/temp
RUN chmod 777 /ruoyi/server/logs

WORKDIR /ruoyi/server

ENV SERVER_PORT=8080

EXPOSE ${SERVER_PORT}

ADD ./target/ruoyi-admin.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dserver.port=${SERVER_PORT}","-jar", "app.jar"]
