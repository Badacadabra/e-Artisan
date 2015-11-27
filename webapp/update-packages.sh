#!/bin/bash

# Package users
cd ../userslib/
ant dist
cp dist/users.jar ../webapp/lib/

# Package services
cd ../serviceslib/
ant dist
cp dist/services.jar ../webapp/lib/

# Exécution
cd ../webapp
ant deploy

# Fin du script
echo "Script exécuté avec succès !"
