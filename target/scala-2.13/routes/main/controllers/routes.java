// @GENERATOR:play-routes-compiler
// @SOURCE:/Volumes/LaCie/Alberto/Máster/Tecnologías del lado del servidor/Ejercicios/GestionRecetas/conf/routes
// @DATE:Sun Dec 20 20:23:31 CET 2020

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseRecipeController RecipeController = new controllers.ReverseRecipeController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseRecipeController RecipeController = new controllers.javascript.ReverseRecipeController(RoutesPrefix.byNamePrefix());
  }

}
