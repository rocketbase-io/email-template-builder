#!/usr/bin/env bash

set -e

# only do deployment, when travis detects a new tag
if [ ! -z "$TRAVIS_TAG" ]
then
    echo "on a tag -> set pom.xml <version> to $TRAVIS_TAG"
    mvn versions:set -DnewVersion=$TRAVIS_TAG
    mvn versions:commit

    echo "version set now perform deploy"
    mvn --settings .travis/settings.xml clean deploy -DskipTests=true -B -U -Prelease
else
    echo "not on a tag -> keep snapshot version in pom.xml"
fi