// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/davidmsl/Documents/Gestor recetas/conf/routes
// @DATE:Sat Jan 30 16:57:28 CET 2021

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

  
    // @LINE:9
    def deleteRecipe: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.deleteRecipe",
      """
        function() {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "recipe"})
        }
      """
    )
  
    // @LINE:8
    def patchRecipe: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.patchRecipe",
      """
        function() {
          return _wA({method:"PATCH", url:"""" + _prefix + { _defaultPrefix } + """" + "recipe"})
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
  
    // @LINE:7
    def getRecipe: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.getRecipe",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "recipe"})
        }
      """
    )
  
    // @LINE:10
    def getRecipes: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.getRecipes",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "recipes"})
        }
      """
    )
  
  }

  // @LINE:11
  class ReverseTypeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def createType: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.TypeController.createType",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "type"})
        }
      """
    )
  
    // @LINE:12
    def getTypes: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.TypeController.getTypes",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "types"})
        }
      """
    )
  
    // @LINE:13
    def deleteType: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.TypeController.deleteType",
      """
        function() {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "type"})
        }
      """
    )
  
  }

  // @LINE:14
  class ReverseUserController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:14
    def registerUser: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.registerUser",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "user"})
        }
      """
    )
  
  }


}
