# Usa la imagen base de Eclipse Temurin con JDK 17
FROM eclipse-temurin:17.0.12_7-jdk

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 9000

# Establecer el directorio de trabajo
WORKDIR /root

# Copiar los archivos de configuración de Maven y el archivo POM
COPY pom.xml /root
COPY .mvn /root/.mvn
COPY mvnw /root/mvnw

# Verificar que mvnw se copió correctamente y darle permisos de ejecución
RUN ls -l /root/mvnw && chmod +x /root/mvnw && ls -l /root/mvnw

# **Instalar Bash y dos2unix para corregir mvnw**
RUN apt-get update && apt-get install -y bash dos2unix && dos2unix /root/mvnw

# Descargar dependencias de Maven usando Bash
RUN /bin/bash /root/mvnw dependency:go-offline

# Copiar el código fuente de la aplicación
COPY src /root/src

# Compilar la aplicación usando Bash
RUN /bin/bash /root/mvnw clean install -DskipTests

# Configurar las variables de entorno para la conexión a la base de datos
ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql-db:3307/producto
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=mypassword

# Iniciar la aplicación
ENTRYPOINT ["java", "-jar", "/root/target/ProductBackendTest-0.0.1-SNAPSHOT.jar"]
