This is a simple CRUD API implementation built using Spring Boot, Spring Data, and Java 8. It uses an in-memory H2 database
</br>
It will manage basic 'User' data.
</br>
</br>
It exposes 7 RESTful endpoints:
<ul>
    <li>create - This will let you create a new User</li>
    <li>findAll - This will let you see ALL Users stored.</li>
    <li>findByEmail - This will let you search for Users by Email </li>
    <li>findByForename - This will let you search for Users by Forename</li>
    <li>findBySurname - This will let you search for Users by Surname</li>
    <li>update - This will let you update an existing User</li>
    <li>delete - This will let you delete an existing User</li>
</ul>
</br>
Each endpoint will accept JSON. 

For more information on these endpoints you can visit the swagger page at http://ip-address:8080/swagger-ui.html
</br>
</br>
You can clone this and build it yourself. It uses Maven as its dependency management tool. 
</br>
It will generate a runnable Spring Boot Jar. 
</br>
<ol>
    <li>mvn clean install</li>
    <li>java -jar crud-api-1.0.0-SNAPSHOT-spring-boot.jar</li>
    <li>go to http://localhost:8080/swagger-ui.html</li>
</ol>
</br>
</br>
Next steps would be to:
    <ul>
        <li>Secure the endpoints using certificates.</li>
        <li>Develop integration tests for Controller.</li>
        <li>Develop BDD tests using Cucumber.</li>
        <li>Set up a Jenkins pipeline to manage build and deployment.</li>
    </ul>
