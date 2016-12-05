#!/bin/bash

set -e

if [ `expr $(git status --porcelain --untracked-files=no | wc -l)` != 0 ]; then
  echo "You have unstaged changes, cannot deploy."
  exit 1
fi

git branch -D deploy
git checkout -b deploy
(
  cd client ;
  ng build --prod
)
bazel build java/bookr:bookr_deploy.jar
mkdir -p bin
cp bazel-bin/java/bookr/bookr{,_deploy.jar} bin
echo 'task stage()' > build.gradle
git add -f client/dist
git add -f bin
git add -f build.gradle
git commit -m "Deploy @ `date`"
git push heroku deploy:master --force
git checkout master
