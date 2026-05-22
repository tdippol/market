FROM tomee:8.0.6-plume

ARG UID
ARG EARFILE

RUN apt update && apt install vim -y
# Postgres DB lib
COPY build/support/image/postgresql/*.jar /usr/local/tomee/lib/ 
##configurazione librerie, datasources e utenti/ruoli
COPY build/support/image/conf/catalina.properties /usr/local/tomee/conf/catalina.properties
COPY build/support/image/conf/server.xml /usr/local/tomee/conf/server.xml
COPY build/support/image/conf/tomee.xml /usr/local/tomee/conf/tomee.xml
COPY build/support/image/bin/setenv.sh /usr/local/tomee/bin/setenv.sh
##questo deve diventare dinamico
COPY $EARFILE /usr/local/tomee/apps/mui.ear

RUN groupadd -g $UID tomee &&\
	adduser --home=/usr/local/tomee --gecos "" --gid $UID --disabled-password -u $UID tomee &&\
	chown -R tomee:tomee /usr/local/tomee
EXPOSE 8000
EXPOSE 9010
USER tomee:tomee

CMD ["catalina.sh", "jpda", "run"]