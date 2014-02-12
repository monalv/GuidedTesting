#! /bin/bash

#rm /var/lib/tomcat6/cobertura.ser
#rm -R /home/utambe/Downloads/cobertura/reports-bookstore
#cd /home/utambe/Downloads/cobertura/
#mkdir reports-bookstore
#rm /home/utambe/bookstore/cobertura.ser
#rm /tmp/cobertura/sb-initial-bookstore.ser
#rm /home/utambe/bookstore/web/WEB-INF/classes/org/apache/jsp/*.class

cd /home/utambe/bookstore
mysql --user=root --password=root < database/bookstore.sql
ant cleanup install
