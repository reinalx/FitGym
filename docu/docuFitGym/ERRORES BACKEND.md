
# No encuentra el archivo de la aplicación en los test

El error que salia en las clases de prueba era que no era capaz de inyectar los beans a los servicios porque no lo encontraba: "Could not autowire. No beans of 'UserService' type found. ".

En la terminal aparecía

``` shell

[ERROR] Errors:
[ERROR]   UserServiceTest » IllegalState Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
[INFO]
[ERROR] Tests run: 1, Failures: 0, Errors: 1, Skipped: 0
```

Nos dice que no encuentra ninguna clase con la configuración del springboot. Por lo que las soluciones ya nos lo esta diciendo ahí. O usamos una clase con esa anotación o indicamos en la anotación de nuestra clase de prueba donde se encuentra las clases de nuestra clase de la aplicación


## Solución:

Añadir esta linea de codigo:


```java

@SpringBootTest(classes = com.fitGym.backend.FitgymServerApplication.class)

Ejemplo:

@SpringBootTest(classes = com.fitGym.backend.FitgymServerApplication.class)  
@ActiveProfiles("test")  
@Transactional  
public class UserServiceTest {

...

}

```

