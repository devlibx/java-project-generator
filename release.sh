mvn -B clean install -Dtag=2.0.0 -Dmaven.test.skip=true release:prepare release:perform

# Rollback
# mvn release:rollback
# git tag -d 0.0.2
# git push --delete origin 0.0.2