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