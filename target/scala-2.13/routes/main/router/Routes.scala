// @GENERATOR:play-routes-compiler
// @SOURCE:/Volumes/LaCie/Alberto/Máster/Tecnologías del lado del servidor/Ejercicios/GestionRecetas/conf/routes
// @DATE:Sun Dec 20 20:23:31 CET 2020

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  RecipeController_0: controllers.RecipeController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    RecipeController_0: controllers.RecipeController
  ) = this(errorHandler, RecipeController_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, RecipeController_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe""", """controllers.RecipeController.createRecipe(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe""", """controllers.RecipeController.getRecipe(request:Request)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe""", """controllers.RecipeController.deleteRecipe(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipes""", """controllers.RecipeController.getRecipes(request:Request)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_RecipeController_createRecipe0_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe")))
  )
  private[this] lazy val controllers_RecipeController_createRecipe0_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_0.createRecipe(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "createRecipe",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """recipe""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:7
  private[this] lazy val controllers_RecipeController_getRecipe1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe")))
  )
  private[this] lazy val controllers_RecipeController_getRecipe1_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_0.getRecipe(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "getRecipe",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """recipe""",
      """""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_RecipeController_deleteRecipe2_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe")))
  )
  private[this] lazy val controllers_RecipeController_deleteRecipe2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_0.deleteRecipe(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "deleteRecipe",
      Seq(classOf[play.mvc.Http.Request]),
      "DELETE",
      this.prefix + """recipe""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_RecipeController_getRecipes3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipes")))
  )
  private[this] lazy val controllers_RecipeController_getRecipes3_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_0.getRecipes(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "getRecipes",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """recipes""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_RecipeController_createRecipe0_route(params@_) =>
      call { 
        controllers_RecipeController_createRecipe0_invoker.call(
          req => RecipeController_0.createRecipe(req))
      }
  
    // @LINE:7
    case controllers_RecipeController_getRecipe1_route(params@_) =>
      call { 
        controllers_RecipeController_getRecipe1_invoker.call(
          req => RecipeController_0.getRecipe(req))
      }
  
    // @LINE:8
    case controllers_RecipeController_deleteRecipe2_route(params@_) =>
      call { 
        controllers_RecipeController_deleteRecipe2_invoker.call(
          req => RecipeController_0.deleteRecipe(req))
      }
  
    // @LINE:9
    case controllers_RecipeController_getRecipes3_route(params@_) =>
      call { 
        controllers_RecipeController_getRecipes3_invoker.call(
          req => RecipeController_0.getRecipes(req))
      }
  }
}
