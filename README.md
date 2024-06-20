
## Introducción API demo taller de vehículos

API creada como ejercicio de programacíón en Java. Se requiere docker para la bd.

Url base para vehículos cuando tiene el proyecto arriba: http://localhost:8080/api/vehicles

La API tiene actualmente 4 endpoints disponibles:

* Registrar un vehículo
  * Método POST
  * Request: objeto: VehiculoDto
* Reconvertir un vehículo
  * Método POST
  * url: /reconvert
  * Request: objeto: VehiculoDto

* Listar vehículos
  * Método GET
  * Parámetros opcionales:
    * carType: String (ELECTRIC, GASOLINE, DIESEL)
    * startDate: String
    * endDate: String
* Obtener información de registro de un vehículo
  * Método GET
  * url: /information
  * Parámetros obligatorios:
    * vin: String
    * carType: String (ELECTRIC, GASOLINE, DIESEL)


Adicionales:

Json VehiculoDto:

```
{
    "vin": String,
    "registrationPlate": String,
    "battery": String (LITHIUM o GEL),
    "voltage": String,
    "current": String,
    "injectionPump": String (ROTARY o LINEAR),
    "fuels": String[],
    "carType": Stirng (ELECTRIC, GASOLINE, DIESEL)
}
```