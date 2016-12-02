#!/bin/bash

set -e

if [ `expr $(git status --porcelain --untracked-files=no | wc -l)` != 0 ]; then
  echo "You have unstaged changes, cannot deploy."
  exit 1
fi

git branch -D deploy
git checkout -b deploy
gradle ngBuild
git add -f client/dist
git commit -m "Deploy @ `date`"
git push heroku deploy:master --force
git checkout master
