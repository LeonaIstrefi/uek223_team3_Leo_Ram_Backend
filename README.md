# README #

## Starter Project Spring Boot



### Docker command
```
docker run --name postgres_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres
```

### Gradle 
<p> Um Ihr Projekt zu starten, müsssen Sie auf Bootrun klicken. Bootrun finden Sie in Ihrem Backend Projekt auf der rechten Seite. Klicken Sie auf den Gradle Gradle Button und befolgen Sie dann diesen Anweisungen: demo>Tasks>application>bootrun. Klicken Sie auf die Schaltfläche bootrun, um das Programm zu starten. </p>

![image](https://github.com/user-attachments/assets/309c98b1-44c6-4fd8-968a-ca9f32b77137)

### Swagger
<p> Mit Swagger haben wir unsere Endpoints getestet. Mit diesem Link können Sie sich die Dokumentation anschauen: http://localhost:8080/swagger-ui/index.html </p>

### Troubleshooting

```
org.postgresql.util.PSQLException: ERROR: relation "role_authority" does not exist
```
Simply restart the application. Hibernate sometimes does not initialize the tables fast enough and causes this error. Restarting the application fixes this.

### Testing 
<p> Unser Backend haben wir mit Postman getestet. Wir haben eien Collection erstellt und somit hatten wir auch einen guten Überblick. </p>


#### Testing Files
<p> Die Exportierten Postman Testing files könne Sie im Ordner "Testing" begutachten.</p>


#### Testing  Collection 
<p> Wie bereits erwähnt haben wir eine Collection erstellt, um einen guten Überblick auf unsere Testing files zu haben. So sieht unsere Collection aus:</p>

![image](https://github.com/user-attachments/assets/dc13d793-fd48-41c9-ba6b-cf6e4fe3a621)

