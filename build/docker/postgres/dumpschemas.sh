#!/bin/sh
DATA=../temp_data

if [ ! -d ${DATA} ]; then
    mkdir ${DATA}
fi

echo "Starting database dump ..."

rm -f ${DATA}/02_public.sql && PGPASSWORD="password" pg_dump -h localhost -U postgres -c --if-exists -f ${DATA}/02_public.sql -n public postgres
rm -f ${DATA}/03_tomee.sql && PGPASSWORD="password" pg_dump -h localhost -U postgres -f ${DATA}/03_tomee.sql -n tomee postgres
rm -f ${DATA}/04_dbpromo.sql && PGPASSWORD="password" pg_dump -h localhost -U postgres -f ${DATA}/04_dbpromo.sql -n dbpromo postgres
rm -f ${DATA}/05_muipromo.sql && PGPASSWORD="password" pg_dump -h localhost -U postgres -f ${DATA}/05_muipromo.sql -n muipromo postgres

rm -f ${DATA}/01_users.sql
cat <<EOF >> ${DATA}/01_users.sql
create user muipromo with password 'muipromo';
create user dbpromo with password 'dbpromo';
create user tomee with password 'tomee';
EOF

rm -f ${DATA}/06_grants.sql
cat <<EOF >> ${DATA}/06_grants.sql
grant usage on schema dbpromo to muipromo;
EOF

echo "Database dumped successfully inside directory ${DATA}"

exit 0