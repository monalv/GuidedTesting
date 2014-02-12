#! /bin/bash

cd /home/utambe/bookstore
mysql --user=root --password=root < database/bookstore.sql
ant cleanup install
