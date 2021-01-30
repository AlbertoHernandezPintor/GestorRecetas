// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/davidmsl/Documents/Gestor recetas/conf/routes
// @DATE:Sat Jan 30 16:57:28 CET 2021


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
