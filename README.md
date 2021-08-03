# MovieApp

## Actividades no finalizadas
1. Por default una llamada directa a la API TMDb (v3) proporciona una "página" con 20 películas, esta app solo muestra dicho resultado catagorizado por Top Rated, Popular y Búsqueda. No llegué a completar la carga de páginas posteriores, la intención era agregar un ScrollListener al RecyclerView que al detectar una posición cercana al tamaño de los datos hiciera una llamada a la siguiente página de la lista (y la agregará sin borrar la información previa).
2. Mejorar el proceso de caché añadiendo una columna de categoría en la tabla "movies" de la base de datos para que no guarde únicamente la última lista consultada y poder filtrarlas mediante sqlite.
3. Implementar busqueda de peliculas en caché agregando una función de consulta a el MovieDao con la anotación @Query, apoyado en una declaración like '%busqueda%' de SQLite, y llamando dicha función desde el repository.
4. Implementar correctamente la verificación de conectividad a internet en DetailViewModel (utilizando NetworkCallbacks).
5. Completar el test de forma exhaustiva (hasta este momento solo he esbozado el proceso de testeo, opte por la estrategia de dobles de prueba), a continuación enlisto los pasos que debería de haber implementado a partir de donde me quedé:
   1. Extraer interface de MovieRepository.
   2. Crear “fake” MovieRepository tal como se hizo con MovieDaoFake y TmdbApiFake.
   3. Crear test de DetailViewModel y OverviewViewModel tal como se hizo en MovieRepositoryTest.
   4. Crear ServiceLocator que hará las veces de Hilt (inyectar dependencias de forma manual).
   5. Agregar dependencias de JUnit4, KotlinXCoroutinesTest y Espresso en build -> androidTestImplementation.
   6. Agregar dependencias de Fragment-Testing y AndroidXTest en build -> implementation.
   7. En el directorio AndroidTest crear clases de prueba de DetailFragment y OverviewFragment anotadas con @MediumTest y @RunWith(AndroidJUnit4::class). 
      1. Se proveerán las dependencias con ServiceLocator.
      2. Los fragments se ejecutan con launchFragmentInContainer.
      3. Se “navega” a través de la interfaz utilizando sentencias de espresso (onView, with, check, match, perform, etc).
   8. Para finalizar crear test de MainActivity anotada con @LargeTest y @RunWith(AndroidJUnit4::class).
      1. Ejecutar activity con ActivityTestRule (esta obsoleta, no he revisado la forma actual de realizarlo). No es necesario inyectar dependencias, pues se ejecuta directamente en el emulador o dispositivo seleccionado.
      2. Navegar a través de la interfaz utilizando sentencias de espresso.
      3. Normalmente utilizo la sentencia Thread.sleep para esperar por resultados.
