#! /bin/bash

rm /var/lib/tomcat6/cobertura.ser
rm -R /home/utambe/Downloads/cobertura/reports-bookstore
cd /home/utambe/Downloads/cobertura/
mkdir reports-bookstore
rm /home/utambe/bookstore/cobertura.ser
rm /tmp/cobertura/sb-initial-bookstore.ser
rm /home/utambe/bookstore/web/WEB-INF/classes/org/apache/jsp/*.class

cd /home/utambe/bookstore
ant cleanup
ant install
rm -R /home/utambe/bookstore/wget
mkdir /home/utambe/bookstore/wget
cd /home/utambe/bookstore/wget

wget http://localhost:8080/bookstore/Default.jsp
wget http://localhost:8080/bookstore/Registration.jsp
wget http://localhost:8080/bookstore/Login.jsp?querystring=&ret_page=%2Fbookstore%2FShoppingCart.jsp
wget http://localhost:8080/bookstore/Login.jsp
wget http://localhost:8080/bookstore/Login.jsp?querystring=&ret_page=%2Fbookstore%2FAdminMenu.jsp
wget http://localhost:8080/bookstore/ShoppingCart.jsp?
wget http://localhost:8080/bookstore/Restart.jsp

cd /home/utambe/Downloads/cobertura
./cobertura-merge.sh /var/lib/tomcat6/cobertura.ser /tmp/cobertura/sb-initial-bookstore.ser --datafile /home/utambe/bookstore/cobertura.ser
./cobertura-report.sh --datafile /home/utambe/bookstore/cobertura.ser --destination  /home/utambe/Downloads/cobertura/reports-bookstore/ /home/utambe/bookstore/src