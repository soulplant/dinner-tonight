#!/bin/bash

set -e

git branch -D deploy
git checkout -b deploy
gradle ngBuild
git add -f client/dist
git commit -m "Deploy @ `date`"
git push heroku deploy:master --force
git checkout master
