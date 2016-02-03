#!/bin/bash

# The script only accepts one parameter.
if [ $# -ne 1 ]; then
    echo "Bad use of $0" 1>&2
    echo "You must enter one parameter." 1>&2
    exit 1
fi

# We check if the database name is valid.
if [[ ! $1 =~ ^([a-zA-Z0-9_]+)$ ]]; then
    echo "Bad use of $0" 1>&2
    echo "The database name is not valid." 1>&2 
    exit 1
fi

# If everything is OK, we can create the database and inject the data.
mysql -e "CREATE DATABASE IF NOT EXISTS \`$1\` CHARACTER SET utf8 COLLATE utf8_general_ci;"
mysql $1 < e_artisan.sql
echo "OK"
