java -jar ./tools/file_processor-1.0-SNAPSHOT.jar \
            -d ~/delete_folder/dummy-project \
            -pr dummy-project \
            -g com.harishb2k \
            -p com.harishb2k.pack \
            -a dummy-project \
            -v 1.0-SNAPSHOT


rm -rf src/main/resources/archetype-resources/*
cp -rf ~/delete_folder/dummy-project/* ./src/main/resources/archetype-resources

mv ./src/main/resources/archetype-resources/app/src/main/java/com/harishb2k/pack/* ./src/main/resources/archetype-resources/app/src/main/java/
rm -rf ./src/main/resources/archetype-resources/app/src/main/java/com

mv ./src/main/resources/archetype-resources/common/src/main/java/com/harishb2k/pack/* ./src/main/resources/archetype-resources/common/src/main/java/
rm -rf ./src/main/resources/archetype-resources/common/src/main/java/com

mv ./src/main/resources/archetype-resources/core/src/main/java/com/harishb2k/pack/* ./src/main/resources/archetype-resources/core/src/main/java/
rm -rf ./src/main/resources/archetype-resources/core/src/main/java/com

mv ./src/main/resources/archetype-resources/persistence/src/main/java/com/harishb2k/pack/* ./src/main/resources/archetype-resources/persistence/src/main/java/
rm -rf ./src/main/resources/archetype-resources/persistence/src/main/java/com

mv ./src/main/resources/archetype-resources/persistence-mysql/src/main/java/com/harishb2k/pack/* ./src/main/resources/archetype-resources/persistence-mysql/src/main/java/
rm -rf ./src/main/resources/archetype-resources/persistence-mysql/src/main/java/com

mv ./src/main/resources/archetype-resources/resources/src/main/java/com/harishb2k/pack/* ./src/main/resources/archetype-resources/resources/src/main/java/
rm -rf ./src/main/resources/archetype-resources/resources/src/main/java/com

mv ./src/main/resources/archetype-resources/headers/src/main/java/com/harishb2k/pack/* ./src/main/resources/archetype-resources/headers/src/main/java/
rm -rf ./src/main/resources/archetype-resources/headers/src/main/java/com


mv ./src/main/resources/archetype-resources/persistence/src/test/java/com/harishb2k/pack/* ./src/main/resources/archetype-resources/persistence/src/test/java/
rm -rf ./src/main/resources/archetype-resources/persistence/src/test/java/com

mv ./src/main/resources/archetype-resources/persistence-mysql/src/test/java/com/harishb2k/pack/* ./src/main/resources/archetype-resources/persistence-mysql/src/test/java/
rm -rf ./src/main/resources/archetype-resources/persistence-mysql/src/test/java/com

mv ./src/main/resources/archetype-resources/external-services/src/main/java/com/harishb2k/pack/* ./src/main/resources/archetype-resources/external-services/src/main/java/
rm -rf ./src/main/resources/archetype-resources/external-services/src/main/java/com

mv ./src/main/resources/archetype-resources/external-services/src/test/java/com/harishb2k/pack/* ./src/main/resources/archetype-resources/external-services/src/test/java/
rm -rf ./src/main/resources/archetype-resources/external-services/src/test/java/com

mv ./src/main/resources/archetype-resources/tests/src/test/java/com/harishb2k/pack/* ./src/main/resources/archetype-resources/tests/src/test/java/
rm -rf ./src/main/resources/archetype-resources/tests/src/test/java/com
rm -rf ./src/main/resources/archetype-resources/tests/src/main/java/com


rm -rf ./src/main/resources/archetype-resources/app/target
rm -rf ./src/main/resources/archetype-resources/common/target
rm -rf ./src/main/resources/archetype-resources/core/target
rm -rf ./src/main/resources/archetype-resources/persistence/target
rm -rf ./src/main/resources/archetype-resources/persistence-mysql/target
rm -rf ./src/main/resources/archetype-resources/resources/target
rm -rf ./src/main/resources/archetype-resources/tests/target
rm -rf ./src/main/resources/archetype-resources/headers/target
rm -rf ./src/main/resources/archetype-resources/external-services/target
rm -rf ./src/main/resources/archetype-resources/target

cd ./src/main/resources/archetype-resources/  && find . -type f -name 'dependency-reduced-pom.xml' -delete && cd -
cd ./src/main/resources/archetype-resources/  && find . -type f -name '*.i*' -delete && cd -
