FROM maven:3.6-jdk-8-slim AS packager

RUN mkdir -p /transaction

WORKDIR /transaction

ADD ./transaction/pom.xml .
ADD ./transaction/docker-entrypoint-transaction.sh /

RUN mvn install -B -f ./pom.xml

COPY ./ .

RUN mvn package -Dmaven.test.skip=true && \
    mv ./transaction/target/*.jar /run/transaction.jar

FROM openjdk:8-slim

COPY --from=packager /run/transaction.jar /var/picpay/transaction/transaction.jar
COPY --from=packager /docker-entrypoint-transaction.sh /docker-entrypoint-transaction.sh

RUN chmod +x /docker-entrypoint-transaction.sh

EXPOSE 8080

ENTRYPOINT [ "bash", "/docker-entrypoint-transaction.sh" ]