// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/davidmsl/Documents/Gestor recetas/conf/routes
// @DATE:Sat Jan 30 16:57:28 CET 2021

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

  
    // @LINE:9
    def deleteRecipe(): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "recipe")
    }
  
    // @LINE:8
    def patchRecipe(): Call = {
      
      Call("PATCH", _prefix + { _defaultPrefix } + "recipe")
    }
  
    // @LINE:6
    def createRecipe(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "recipe")
    }
  
    // @LINE:7
    def getRecipe(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recipe")
    }
  
    // @LINE:10
    def getRecipes(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recipes")
    }
  
  }

  // @LINE:11
  class ReverseTypeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:11
    def createType(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "type")
    }
  
    // @LINE:12
    def getTypes(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "types")
    }
  
    // @LINE:13
    def deleteType(): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "type")
    }
  
  }

  // @LINE:14
  class ReverseUserController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:14
    def registerUser(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "user")
    }
  
  }


}
