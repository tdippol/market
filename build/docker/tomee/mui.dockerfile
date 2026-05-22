FROM tomee:jre8-alpine-plume

ARG UID

RUN apk --update --no-cache add --quiet shadow vim curl tzdata \
        && groupadd --gid ${UID} tomee \
        && adduser -h /usr/local/tomee -g "" -G tomee -D -u ${UID} tomee \
        && chown -R tomee:tomee /usr/local/tomee \
        && apk del shadow --quiet 
EXPOSE 8000
EXPOSE 9010
ENV TZ=

USER tomee:tomee

CMD ["catalina.sh", "jpda", "run"]

