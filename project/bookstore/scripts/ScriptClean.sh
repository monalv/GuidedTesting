#! /bin/bash

rm /var/lib/tomcat6/cobertura.ser
rm /home/utambe/bookstore/cobertura.ser
rm /tmp/cobertura/sb-initial-bookstore.ser
cd /home/utambe/bookstore
mysql --user=root --password=root < database/bookstore.sql