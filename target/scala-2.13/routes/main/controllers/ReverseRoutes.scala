// @GENERATOR:play-routes-compiler
// @SOURCE:/Volumes/LaCie/Alberto/Máster/Tecnologías del lado del servidor/Ejercicios/GestionRecetas/conf/routes
// @DATE:Sun Dec 20 20:23:31 CET 2020

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseRecipeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:8
    def deleteRecipe(): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "recipe")
    }
  
    // @LINE:6
    def createRecipe(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "recipe")
    }
  
    // @LINE:9
    def getRecipes(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recipes")
    }
  
    // @LINE:7
    def getRecipe(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recipe")
    }
  
  }


}
