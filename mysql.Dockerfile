FROM mysql:5.7

ENV MYSQL_DATABASE=users \
    MYSQL_ROOT_PASSWORD=root

ADD ./java-base/support/db-init.sql /docker-entrypoint-initdb.d/

EXPOSE 3306