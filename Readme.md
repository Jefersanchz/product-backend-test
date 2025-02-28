
# Instrucciones para Ejecutar el Contenedor Docker

Este proyecto está contenerizado para facilitar la ejecución en un entorno aislado. A continuación se detallan los pasos para construir y ejecutar el contenedor Docker, y para probar los endpoints en Swagger.

## Prerrequisitos
Asegúrate de tener Docker y Docker Compose instalados en tu máquina. Si estás utilizando Docker Desktop, ya viene con Docker Compose integrado.

## Paso 1: Ejecutar proyecto
En la raíz del proyecto, abre una terminal y ejecuta el siguiente comando para construir la imagen Docker, Mysql y ejecutar el proyecto:

```bash
docker-compose up --build
```

Este comando compilará el código y creará la imagen Docker llamada `product-backend`, que contiene todas las dependencias necesarias y tambien creará una imagen mysql para la base de datos.


- Construye la imagen si aún no existe o si ha habido cambios.
- Ejecuta el contenedor en el puerto `9000`.

La aplicación estará accesible en `http://localhost:9000`.

## Paso 3: Probar los Endpoints en Swagger
La aplicación utiliza Springdoc OpenAPI para generar documentación automática de la API en Swagger. Una vez que la aplicación esté ejecutándose, sigue estos pasos para acceder y probar los endpoints:

1. Abre un navegador y ve a `http://localhost:9000/swagger-ui.html`.
2. En la interfaz de Swagger, encontrarás la documentación de todos los endpoints disponibles.
3. Haz clic en **Try it out** junto a cada endpoint para probarlo directamente desde la interfaz de Swagger.

### Endpoints Disponibles
#### Users (Usuarios)
- `POST/api/users/register`: Registrar nuevo usuario
- `POST/api/users/login`: Ingresar con usuario creado.

#### Orders (Pedidos)

- `GET /api/orders`: Obtiene todos los pedidos.
- `GET /api/orders/{id}`: Obtiene un pedido específico por su ID.
- `POST /api/orders`: Crea un nuevo pedido.
- `DELETE /api/orders/{id}`: Elimina un pedido específico.
- `GET /api/orders/date`: Obtiene los pedidos por fecha específica.
- `GET /api/orders/date-range`: Obtiene los pedidos dentro de un rango de fechas.
- `GET /api/orders/product/{productId}`: Obtiene los pedidos asociados a un producto específico.
- `GET /api/orders/quantity`: Obtiene los pedidos con una cantidad mayor a la especificada.
- `GET /api/orders/total-sales`: Obtiene el total de ventas dentro de un rango de fechas.

#### Products (Productos)

- `GET /api/products`: Obtiene todos los productos.
- `GET /api/products/{id}`: Obtiene un producto específico por su ID.
- `POST /api/products`: Crea un nuevo producto.
- `PUT /api/products/{id}`: Actualiza un producto existente.
- `DELETE /api/products/{id}`: Elimina un producto específico.

## Paso 4: Detener el Contenedor
Para detener y eliminar el contenedor una vez que hayas terminado de probar el backend, ejecuta:

```bash
docker-compose down
```
## Paso 5: Desplegar en Kubernetes

Este comando apagará y eliminará el contenedor.
Paso 1: Crear los Archivos de Configuración YAML
Aquí tienes los archivos YAML necesarios para desplegar tu aplicación en Kubernetes.
Aplica los Archivos YAML
Ejecuta los siguientes comandos para crear los deployments y servicios de la aplicación y de MySQL:

## Paso 6: Desplegar en Kubernetes

kubectl apply -f product-backend-deployment.yml
kubectl apply -f product-backend-service.yml
kubectl apply -f mysql-deployment.yml
kubectl apply -f mysql-service.yml

## Paso 6: Acceso al Backend desde Fuera del Clúster
kubectl get svc product-backend-service
minikube service product-backend-service --url

## Paso 7: Acceso al Backend desde Fuera del Clústerkubectl 
export-forward svc/product-backend-service 9000:80

