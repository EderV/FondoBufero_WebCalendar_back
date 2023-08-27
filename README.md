
### Run app with maven ###
* ./mvnw spring-boot:run -P <id_of_the_profile>

### Package and repackage app in jar with spring boot maven plugin ###
* ./mvnw clean package spring-boot:repackage -P <id_of_the_profile>

### Run app with java ###
* Repackage app
* java -jar target/<name_of_the_app>.jar