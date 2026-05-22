#/bin/bash
echo "creating temporary directories ..."
mkdir -p 18.4.0 &> /dev/null
cd 18.4.0 &> /dev/null
echo "dowloading content from Oracle git ..."
wget https://raw.githubusercontent.com/oracle/docker-images/main/OracleDatabase/SingleInstance/dockerfiles/18.4.0/Dockerfile.xe &> /dev/null
wget https://raw.githubusercontent.com/oracle/docker-images/main/OracleDatabase/SingleInstance/dockerfiles/18.4.0/checkDBStatus.sh &> /dev/null
wget https://raw.githubusercontent.com/oracle/docker-images/main/OracleDatabase/SingleInstance/dockerfiles/18.4.0/checkSpace.sh &> /dev/null
wget https://raw.githubusercontent.com/oracle/docker-images/main/OracleDatabase/SingleInstance/dockerfiles/18.4.0/oracle-xe-18c.conf &> /dev/null
wget https://raw.githubusercontent.com/oracle/docker-images/main/OracleDatabase/SingleInstance/dockerfiles/18.4.0/runOracle.sh &> /dev/null
wget https://raw.githubusercontent.com/oracle/docker-images/main/OracleDatabase/SingleInstance/dockerfiles/18.4.0/setPassword.sh &> /dev/null

cd .. &> /dev/null

wget https://raw.githubusercontent.com/oracle/docker-images/main/OracleDatabase/SingleInstance/dockerfiles/buildContainerImage.sh &> /dev/null
chmod +x ./buildContainerImage.sh  &> /dev/null

echo "building Oracle XE 18.4.0 docker image ..."
./buildContainerImage.sh -v 18.4.0 -t oracle:18.4.0-xe -x

echo "cleaning up"
rm -rf 18.4.0 &> /dev/null
rm ./buildContainerImage.sh &> /dev/null

