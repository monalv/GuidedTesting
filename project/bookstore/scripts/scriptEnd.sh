#! /bin/bash

wget http://localhost:8080/bookstore/Restart.jsp

cd /home/utambe/Downloads/cobertura
./cobertura-merge.sh /var/lib/tomcat6/cobertura.ser /tmp/cobertura/sb-initial-bookstore.ser --datafile /home/utambe/bookstore/cobertura.ser
./cobertura-report.sh  --format html --datafile /home/utambe/bookstore/cobertura.ser --destination  /home/utambe/Downloads/cobertura/reports-bookstore/ /home/utambe/bookstore/src
./cobertura-report.sh  --format xml --datafile /home/utambe/bookstore/cobertura.ser --destination  /home/utambe/Downloads/cobertura/reports-bookstore/ /home/utambe/bookstore/src