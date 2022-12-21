#!/bin/bash

# code-drills deployment script
# This script DOES not pulls the git repo. It only packages, copies and deploys

# function to check success of last command
check() {
    if [ $? -eq 0 ]; then
        echo
        echo "$1 successful"
        echo
    else
        echo
        echo "$1 failed!"
        echo
        exit -1
    fi
}

# Display branch
branch=`git symbolic-ref --short HEAD`
echo "Deploying branch $branch"
sleep 2

# compile and create a package with prod profile
mvn clean package 
check "Packaging"

# tag with current time stamp
echo "Pushing tag"
tagname=prod-$(date +%Y%m%d.%H%M%S)
git tag -a $tagname -m "Deployment script"
check "Tag"
#git push origin $tagname
#check "Push tag"


# copy the created jar file
sudo cp target/code-drills.jar /var/codedrills-recommender/
check "Copying jar"

# stop currently running service
sudo /etc/init.d/code-drills stop
check "Stopping service"

# start newly copied service
sudo /etc/init.d/code-drills start
check "Starting service"

# sleep a bit
echo "Sleeping for 60 seconds"
sleep 60
check "Sleeping"

# ping the site
echo "pinging for status"
response=`curl https://recommender.codedrills.io/status`
if [ "$response" != "OK" ]; then
    echo "Status not OK"
    exit -1
fi
echo

# done!
echo "code-drills succesfully deployed"
