#! /bin/bash
cd /home/utambe/bookstore
#mysql --user=root --password=root < database/bookstore.sql
#ant install
cd /home/utambe/bookstore/scripts
javac UsageTesting.java
java UsageTesting