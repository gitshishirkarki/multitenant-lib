# Multitenancy Library for Spring Boot Application
## How to use?
### Step 1
Build the library project with 
- gradle clean
- gradle build

### Step 2
Copy the library jar multitenancy-1.0.jar to the root folder of Spring Boot project to be implemented.

### Step 3
add dependency in build.gradle as 
```
implementation file('multitenancy-1.0.jar')
```

### Step 4
Run your Spring Boot application. Your application.properties looks like this without database name mentioned.
```
spring.datasource.url=jdbc:mysql://localhost:3306
spring.datasource.username=root
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
```

### Step 5
The database name can be provided in the header as "tenantId". For e.g. If API call header key = tenantId and value = myServiceDB, then myServiceDB will be connected for the database transactions.

## Note
The library is currently tested for only MySQL database. The encryption to the database password and header value is still in progress.
