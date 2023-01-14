
This utility helps you to create a ready to run project with following:
1. Dropwizard app
2. Prometheus Metrics 
3. MySql database support
4. Rest Apis (Json over HTTP 1.1)
5. Rest Apis (Proto-buf over HTTP 1.1)
6. Database health checks
7. EasyHttp support - easy http provides resilient http sync/async client
   https://github.com/harishb2k/easy/wiki/Http-Module 


Generate Project
=====
Configuration for creating project:

1. groupId - your project group id
2. artifactId - your project artifact id
3. package - package name for you for java source code 

```shell script
 mvn archetype:generate \
  -DarchetypeGroupId=io.github.devlibx.tools.java.maven \
  -DarchetypeArtifactId=project-generator \
  -DarchetypeVersion= v2.0.8.java19 \
  -DgroupId=com.dummy \
  -DartifactId=dummy-project \
  -Dpackage=com.dummy.pack \
  -DinteractiveMode=false

See "Known Issue" to reformat the generated pom.xml to remove extra spaces.
>> cd <project>
>> xmllint --format pom.xml > pom.new ; mv pom.new pom.xml
```

#### Run Application
Create a sample DB

If you run `mvn clean install` then you also need the mysql database running since one of the test writes to the following table). 

Modify Jdbc Url in following file:
`com.harish.dummy.persistence.base.PersistenceTestState.PersistenceTestContext`

```shell script
CREATE DATABASE users;

CREATE TABLE `users` (
   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
   `name` varchar(265) DEFAULT NULL,
   PRIMARY KEY (`id`)
 );
``` 

```shell script
> cd <new project dir which is created>

> mvn clean install -DskipTests
    Or
> mvn clean install 

# Update the DB related variable in config/run_local.sh file
> sh config/run_local.sh
``` 
  
### Test you application
Calling sample api - Json
```shell script
curl -X POST 'http://127.0.0.1:8080/api/v1/user' --header 'Content-Type: application/json' \
--data-raw '{
    "id": 1234
}'

Response:
{
    "id": 115,
    "some_data_from_external_http_service": {
        "userId": 1,
        "id": 1,
        "title": "some text",
        "body": "some text"
    }
}
```
  
  
Calling sample proto-buf api:

We support proto-buffer APIs with Json request. However, you should use proto-buf client to call this same api.
    
```shell script
curl --location --request POST 'http://127.0.0.1:8080/api/v2/user' \
--header 'Content-Type: application/x-protobuf-json-format' \
--data-raw '{
  "input": "c99764c9-ef5f-4ac3-81be-b0887d0930aa"
}
'

Response:
{
    "status": "Ok",
    "string_passed_in_input": "c99764c9-ef5f-4ac3-81be-b0887d0930aa"
}
```
  
##### Known Issue:
The main pom.xml file may have extra spaces. You can reformat it online:
```shell script
https://www.liquid-technologies.com/online-xml-formatter
Copy the pom.xml and remove unnessassery spaces
```  
OR
```shell script
If you have xmllint lint installed 
xmllint --format pom.xml > pom.new ; mv pom.new pom.xml
```
  
Generate from Source
===
If you want to generate this from soruce to customize then you can do from following:
```shell script
git clone https://github.com/devlibx/java-project-generator.git
mvn clean install
```  
