rm -rf src/main/resources/archetype-resources/
cp -rf ../dummy-project/target/generated-sources/archetype/src/main/resources/archetype-resources src/main/resources

sed  's/com.dummy.pack/${package}/g' src/main/resources/archetype-resources/headers/src/main/proto/api.proto > src/main/resources/archetype-resources/headers/src/main/proto/api1.proto
rm src/main/resources/archetype-resources/headers/src/main/proto/api.proto
mv src/main/resources/archetype-resources/headers/src/main/proto/api1.proto src/main/resources/archetype-resources/headers/src/main/proto/api.proto

sed  's/com.dummy.pack.app.RestApplication/${package}.app.RestApplication/g' src/main/resources/archetype-resources/app/pom.xml > src/main/resources/archetype-resources/app/pom1.xml
rm src/main/resources/archetype-resources/app/pom.xml
mv src/main/resources/archetype-resources/app/pom1.xml src/main/resources/archetype-resources/app/pom.xml

rm -rf ./src/main/resources/archetype-resources/.idea
rm -rf ./src/main/resources/archetype-resources/dependency-reduced-pom.xml
rm -rf ./src/main/resources/archetype-resources/*.iml
rm -rf ./src/main/resources/archetype-resources/*.ipr
rm -rf ./src/main/resources/archetype-resources/*.iws