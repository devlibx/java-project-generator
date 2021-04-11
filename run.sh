mvn clean install
cd ~/delete_folder && rm -rf dummy-project && mvn archetype:generate \
  -DarchetypeGroupId=io.github.harishb2k.tools.java.maven \
  -DarchetypeArtifactId=project-generator \
  -DarchetypeVersion=1.0.29-SNAPSHOT \
  -DgroupId=com.harishb2k \
  -DartifactId=dummy-project \
  -Dpackage=com.harishb2k.pack \
  -DinteractiveMode=false && cd dummy-project && \
  xmllint --format pom.xml > pom.new && mv pom.new pom.xml && mvn clean install
