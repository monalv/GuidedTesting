#! /bin/bash
sh null/workspace/GuidedTesting/scripts/scriptStart.sh
rm -r null/workspace/GuidedTesting/wget/olduc/UC1 
 mkdir null/workspace/GuidedTesting/wget/olduc/UC1
wget --directory-prefix null/workspace/GuidedTesting/wget/olduc/UC1 --keep-session-cookies --save-cookies cookies.txt --post-data 'Login=admin&querystring=&FormAction=login&Password=admin&ret_page=&FormName=Login' http://localhost:8080/bookstore/Login.jsp
wget --directory-prefix null/workspace/GuidedTesting/restartwget http://localhost:8080/null/Restart.jsp
cd null/Downloads/cobertura
 ./cobertura-merge.sh /var/lib/tomcat6/cobertura.ser /tmp/cobertura/sb-initial-null.ser --datafile null/null/cobertura.ser
./cobertura-report.sh --datafile null/null/cobertura.ser --destination  null/workspace/GuidedTesting/usecases/olduc/reports-null/ null/null/src
./cobertura-report.sh --format xml --datafile null/null/cobertura.ser --destination  null/workspace/GuidedTesting/usecases/olduc/reports-null/ null/null/src
rm -r null/workspace/GuidedTesting/restartwget 
 mkdir null/workspace/GuidedTesting/restartwget
sh null/workspace/GuidedTesting/scripts/scriptStart.sh
rm -r null/workspace/GuidedTesting/wget/olduc/UC2 
 mkdir null/workspace/GuidedTesting/wget/olduc/UC2
wget --directory-prefix null/workspace/GuidedTesting/wget/olduc/UC2 --keep-session-cookies --save-cookies cookies.txt --post-data 'Login=guest&querystring=&FormAction=login&Password=guest&ret_page=Default.jsp&FormName=Login' http://localhost:8080/bookstore/Login.jsp
wget --directory-prefix null/workspace/GuidedTesting/restartwget http://localhost:8080/null/Restart.jsp
cd null/Downloads/cobertura
 ./cobertura-merge.sh /var/lib/tomcat6/cobertura.ser /tmp/cobertura/sb-initial-null.ser --datafile null/null/cobertura.ser
./cobertura-report.sh --datafile null/null/cobertura.ser --destination  null/workspace/GuidedTesting/usecases/olduc/reports-null/ null/null/src
./cobertura-report.sh --format xml --datafile null/null/cobertura.ser --destination  null/workspace/GuidedTesting/usecases/olduc/reports-null/ null/null/src
rm -r null/workspace/GuidedTesting/restartwget 
 mkdir null/workspace/GuidedTesting/restartwget
sh null/workspace/GuidedTesting/scripts/scriptStart.sh
rm -r null/workspace/GuidedTesting/wget/olduc/UC3 
 mkdir null/workspace/GuidedTesting/wget/olduc/UC3
wget --directory-prefix null/workspace/GuidedTesting/wget/olduc/UC3  --load-cookies cookies.txt --post-data 'Login=admin&querystring=&FormAction=login&Password=123&ret_page=&FormName=Login' http://localhost:8080/bookstore/Login.jsp
wget --directory-prefix null/workspace/GuidedTesting/restartwget http://localhost:8080/null/Restart.jsp
cd null/Downloads/cobertura
 ./cobertura-merge.sh /var/lib/tomcat6/cobertura.ser /tmp/cobertura/sb-initial-null.ser --datafile null/null/cobertura.ser
./cobertura-report.sh --datafile null/null/cobertura.ser --destination  null/workspace/GuidedTesting/usecases/olduc/reports-null/ null/null/src
./cobertura-report.sh --format xml --datafile null/null/cobertura.ser --destination  null/workspace/GuidedTesting/usecases/olduc/reports-null/ null/null/src
