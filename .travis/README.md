# how to deploy to sonartype

the travis build will perform an deploy in case of an tag

````bash
git tag ${versionNumber}
git push --tags
````

the given tag-name will be used as version...