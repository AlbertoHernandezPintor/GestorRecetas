// @GENERATOR:play-routes-compiler
// @SOURCE:/Volumes/LaCie/Alberto/Máster/Tecnologías del lado del servidor/Ejercicios/GestionRecetas/conf/routes
// @DATE:Sun Dec 20 20:23:31 CET 2020


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
