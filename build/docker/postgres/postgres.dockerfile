FROM postgres:14.7
ARG UID
RUN usermod -u ${UID} postgres && groupmod -g ${UID} postgres