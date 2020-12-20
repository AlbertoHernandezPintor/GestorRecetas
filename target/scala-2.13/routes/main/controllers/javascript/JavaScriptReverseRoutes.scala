// @GENERATOR:play-routes-compiler
// @SOURCE:/Volumes/LaCie/Alberto/Máster/Tecnologías del lado del servidor/Ejercicios/GestionRecetas/conf/routes
// @DATE:Sun Dec 20 20:23:31 CET 2020

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {

  // @LINE:6
  class ReverseRecipeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def deleteRecipe: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.deleteRecipe",
      """
        function() {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "recipe"})
        }
      """
    )
  
    // @LINE:6
    def createRecipe: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.createRecipe",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "recipe"})
        }
      """
    )
  
    // @LINE:9
    def getRecipes: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.getRecipes",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "recipes"})
        }
      """
    )
  
    // @LINE:7
    def getRecipe: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.getRecipe",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "recipe"})
        }
      """
    )
  
  }


}
