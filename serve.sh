#!/bin/bash

if [ ! -d "/opt/tomcat/webapps" ]; then
  echo "ERROR: Apache Tomcat Not Found"

  exit 1
fi

mvn clean package

if [ ! -d "target/classes" ]; then
  echo "ERROR: Cannot compile java project"

  exit 1
fi

sudo cp target/caravanas.war /opt/tomcat/webapps

sudo systemctl restart tomcat
