#! /bin/bash
mysql --user=root --password=root < /home/utambe/bookstore/database/bookstore.sql
rm -r /home/utambe/bookstore/wget/UC1 
 mkdir /home/utambe/bookstore/wget/UC1
wget --directory-prefix /home/utambe/bookstore/wget/UC1  --load-cookies cookies.txt --post-data 'name=Data&category_id=' http://localhost:8080/bookstore/Books.jsp
mysql --user=root --password=root < /home/utambe/bookstore/database/bookstore.sql
rm -r /home/utambe/bookstore/wget/UC2 
 mkdir /home/utambe/bookstore/wget/UC2
wget --directory-prefix /home/utambe/bookstore/wget/UC2  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/Default.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC2  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/Registration.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC2  --load-cookies cookies.txt --post-data 'first_name=guest1&phone=121212121&PK_member_id=&email=guest1@noweb.com&address=guest&card_number=121212121212&member_password=guest&FormAction=insert&member_login=guest1&last_name=guest1&member_password2=guest&card_type_id=2&FormName=Reg&member_id=' http://localhost:8080/bookstore/Registration.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC2  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/Default.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC2  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/Login.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC2 --keep-session-cookies --save-cookies cookies.txt --post-data 'Login=guest1&querystring=&FormAction=login&Password=guest&ret_page=&FormName=Login' http://localhost:8080/bookstore/Login.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC2  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/ShoppingCart.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC2  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/ShoppingCart.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC2  --load-cookies cookies.txt --post-data 'querystring=&ret_page=/bookstore/ShoppingCart.jsp' http://localhost:8080/bookstore/Login.jsp
mysql --user=root --password=root < /home/utambe/bookstore/database/bookstore.sql
rm -r /home/utambe/bookstore/wget/UC3 
 mkdir /home/utambe/bookstore/wget/UC3
wget --directory-prefix /home/utambe/bookstore/wget/UC3  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/Login.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC3 --keep-session-cookies --save-cookies cookies.txt --post-data 'Login=guest&querystring=&FormAction=login&Password=guest&ret_page=&FormName=Login' http://localhost:8080/bookstore/Login.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC3  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/ShoppingCart.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC3  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/AdminMenu.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC3  --load-cookies cookies.txt --post-data 'querystring=&ret_page=/bookstore/AdminMenu.jsp' http://localhost:8080/bookstore/Login.jsp
mysql --user=root --password=root < /home/utambe/bookstore/database/bookstore.sql
rm -r /home/utambe/bookstore/wget/UC4 
 mkdir /home/utambe/bookstore/wget/UC4
wget --directory-prefix /home/utambe/bookstore/wget/UC4  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/Login.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC4 --keep-session-cookies --save-cookies cookies.txt --post-data 'Login=guest&querystring=&FormAction=login&Password=guest&ret_page=&FormName=Login' http://localhost:8080/bookstore/Login.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC4  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/ShoppingCart.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC4  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/Default.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC4  --load-cookies cookies.txt --post-data 'item_id=1' http://localhost:8080/bookstore/BookDetail.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC4  --load-cookies cookies.txt --post-data 'item_id=1&FormAction=insert&quantity=1&order_id=&FormName=Order&PK_order_id=' http://localhost:8080/bookstore/BookDetail.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC4  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/ShoppingCart.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC4  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/Default.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC4  --load-cookies cookies.txt --post-data 'item_id=22' http://localhost:8080/bookstore/BookDetail.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC4  --load-cookies cookies.txt --post-data 'item_id=22&FormAction=insert&quantity=1&order_id=&FormName=Order&PK_order_id=' http://localhost:8080/bookstore/BookDetail.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC4  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/ShoppingCart.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC4  --load-cookies cookies.txt --post-data 'order_id=1' http://localhost:8080/bookstore/ShoppingCartRecord.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC4  --load-cookies cookies.txt --post-data 'FormAction=delete&quantity=1&order_id=1&FormName=ShoppingCartRecord&PK_order_id=1&member_id=2' http://localhost:8080/bookstore/ShoppingCartRecord.jsp
wget --directory-prefix /home/utambe/bookstore/wget/UC4  --load-cookies cookies.txt --post-data '' http://localhost:8080/bookstore/ShoppingCart.jsp
