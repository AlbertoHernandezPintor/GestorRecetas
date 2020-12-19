// @GENERATOR:play-routes-compiler
// @SOURCE:/Volumes/LaCie/Alberto/Máster/Tecnologías del lado del servidor/Ejercicios/GestorRecetas/GestorRecetas/conf/routes
// @DATE:Sat Dec 19 11:33:59 CET 2020

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:6
  class ReverseUserController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def createUser(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "user")
    }
  
  }


}
