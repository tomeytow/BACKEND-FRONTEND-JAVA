# Guía para usar la consola H2 en Spring Boot

## 1. Configura tu archivo `application.properties`

Copia y pega esto en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:ecommerce_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console