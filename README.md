# MovieApp

## Actividades no finalizadas
1) Por default una llamada directa a la API TMDb (v3) proporciona una "página" con 20 películas, esta app solo muestra dicho resultado catagorizado por Top Rated, Popular y Búsqueda. No llegué a completar la carga de páginas posteriores, la intención era agregar un ScrollListener al RecyclerView que al detectar una posición cercana al tamaño de los datos hiciera una llamada a la siguiente página de la lista (y la agregará sin borrar la información previa).
2) Mejorar el proceso de caché añadiendo una columna de categoría en la tabla "movies" de la base de datos para que no guarde únicamente la última lista consultada y poder filtrarlas mediante sqlite.
3) Implementar busqueda de peliculas en caché agregando una función de consulta a el MovieDao con la anotación @Query, apoyado en una declaración like '%busqueda%' de SQLite, y llamando dicha función desde el repository.
4) Testear de forma más exhaustiva.
5) Implementar correctamente la verificación de conectividad a internet en DetailViewModel (utilizando NetworkCallbacks).
