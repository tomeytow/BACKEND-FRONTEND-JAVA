# BACKEND-FRONTEND-JAVA

Proyecto de ejemplo Full Stack: **Spring Boot (backend Java) + React (frontend JavaScript)**  
Repositorio: [@tomeytow/BACKEND-FRONTEND-JAVA](https://github.com/tomeytow/BACKEND-FRONTEND-JAVA)

---

## Requisitos

- **Java 17** o superior
- **Node.js 18** o superior y **npm**
- **Maven** (si usás Spring Boot con Maven)
- (Opcional) **H2 Console** para ver la base de datos en memoria

---

## Estructura del proyecto

```
BACKEND-FRONTEND-JAVA/
├── backend/         # Código Java Spring Boot
├── frontend/        # Código React
└── README.md
```

---

## 1. Configuración y ejecución del **Backend** (Spring Boot)

1. **Entrá a la carpeta del backend:**
   ```bash
   cd backend
   ```

2. **Configurá la base de datos H2 (opcional):**  
   El backend ya está listo para correr con H2 en memoria o archivo.  
   Podés cambiar la configuración en `src/main/resources/application.properties`.  
   Ejemplo para H2 en memoria:
   ```
   spring.datasource.url=jdbc:h2:mem:ecommerce_db;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
   spring.datasource.username=sa
   spring.datasource.password=
   spring.jpa.hibernate.ddl-auto=update
   spring.h2.console.enabled=true
   ```
   Si querés que los datos persistan:
   ```
   spring.datasource.url=jdbc:h2:file:~/mi-bd-h2
   ```

3. **Instalá dependencias y compilá (con Maven):**
   ```bash
   mvn clean install
   ```

4. **Levantá el backend:**
   ```bash
   mvn spring-boot:run
   ```
   o ejecutá el jar:
   ```bash
   java -jar target/*.jar
   ```

5. **Accedé a la API:**  
   El backend expone endpoints REST en: [http://localhost:8080](http://localhost:8080)

6. **H2 Console (opcional):**  
   Disponible en: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
   Usá la URL configurada en el paso 2.

---

## 2. Configuración y ejecución del **Frontend** (React)

1. **Entrá a la carpeta del frontend:**
   ```bash
   cd ../frontend
   ```

2. **Instalá dependencias:**
   ```bash
   npm install
   ```

3. **Levantá el frontend:**
   ```bash
   npm start
   ```
   El frontend estará disponible en: [http://localhost:3000](http://localhost:3000)

---

## 3. Uso común

- El frontend se comunica por defecto con el backend en [http://localhost:8080](http://localhost:8080).
- Si usás otra URL/puerto, modificá el archivo de configuración del frontend (`.env` o donde se defina la API base URL).

---

## 4. Datos de ejemplo

Podés cargar datos de ejemplo en la base agregando un archivo `data.sql` en `backend/src/main/resources/`  
Ejemplo:

```sql
INSERT INTO cliente (nombre, apellido, email, password) VALUES ('Tomas', 'Gomez', 'gomeztomassalvador@gmail.com', '123');
INSERT INTO producto (id, nombre, precio, imagen_url, stock) VALUES (101, 'Producto especial', 99.99, 'https://via.placeholder.com/150', 10);
```

---

## 5. Problemas comunes

- **"productos.map is not a function":** El backend debe devolver un array JSON, no un objeto.
- **"Table not found" en H2:** Verificá que la tabla exista y que las entidades coincidan con las tablas.
- **Cambios en entidades:** Si cambias los modelos Java, borra la base de datos H2 o usá `spring.jpa.hibernate.ddl-auto=create-drop`.

---

## 6. Contacto

Proyecto creado por [@tomeytow](https://github.com/tomeytow)

---

¿Dudas?  
- Revisá la consola de Java y la de Node para errores.
- Revisá la documentación de Spring Boot y React.
- Usá la [H2 Console](http://localhost:8080/h2-console) para inspeccionar la base de datos.

---