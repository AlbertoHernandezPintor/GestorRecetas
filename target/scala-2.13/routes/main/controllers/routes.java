// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/davidmsl/Documents/Gestor recetas/conf/routes
// @DATE:Sat Jan 30 16:57:28 CET 2021

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseRecipeController RecipeController = new controllers.ReverseRecipeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseTypeController TypeController = new controllers.ReverseTypeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseUserController UserController = new controllers.ReverseUserController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseRecipeController RecipeController = new controllers.javascript.ReverseRecipeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseTypeController TypeController = new controllers.javascript.ReverseTypeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseUserController UserController = new controllers.javascript.ReverseUserController(RoutesPrefix.byNamePrefix());
  }

}
