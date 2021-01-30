// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/davidmsl/Documents/Gestor recetas/conf/routes
// @DATE:Sat Jan 30 16:57:28 CET 2021

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
  // @LINE:11
  TypeController_1: controllers.TypeController,
  // @LINE:14
  UserController_2: controllers.UserController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    RecipeController_0: controllers.RecipeController,
    // @LINE:11
    TypeController_1: controllers.TypeController,
    // @LINE:14
    UserController_2: controllers.UserController
  ) = this(errorHandler, RecipeController_0, TypeController_1, UserController_2, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, RecipeController_0, TypeController_1, UserController_2, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe""", """controllers.RecipeController.createRecipe(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe""", """controllers.RecipeController.getRecipe(request:Request)"""),
    ("""PATCH""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe""", """controllers.RecipeController.patchRecipe(request:Request)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe""", """controllers.RecipeController.deleteRecipe(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipes""", """controllers.RecipeController.getRecipes(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """type""", """controllers.TypeController.createType(request:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """types""", """controllers.TypeController.getTypes(request:Request)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """type""", """controllers.TypeController.deleteType(request:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """user""", """controllers.UserController.registerUser(request:Request)"""),
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
  private[this] lazy val controllers_RecipeController_patchRecipe2_route = Route("PATCH",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe")))
  )
  private[this] lazy val controllers_RecipeController_patchRecipe2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_0.patchRecipe(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "patchRecipe",
      Seq(classOf[play.mvc.Http.Request]),
      "PATCH",
      this.prefix + """recipe""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_RecipeController_deleteRecipe3_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe")))
  )
  private[this] lazy val controllers_RecipeController_deleteRecipe3_invoker = createInvoker(
    
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

  // @LINE:10
  private[this] lazy val controllers_RecipeController_getRecipes4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipes")))
  )
  private[this] lazy val controllers_RecipeController_getRecipes4_invoker = createInvoker(
    
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

  // @LINE:11
  private[this] lazy val controllers_TypeController_createType5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("type")))
  )
  private[this] lazy val controllers_TypeController_createType5_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      TypeController_1.createType(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.TypeController",
      "createType",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """type""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_TypeController_getTypes6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("types")))
  )
  private[this] lazy val controllers_TypeController_getTypes6_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      TypeController_1.getTypes(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.TypeController",
      "getTypes",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """types""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_TypeController_deleteType7_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("type")))
  )
  private[this] lazy val controllers_TypeController_deleteType7_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      TypeController_1.deleteType(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.TypeController",
      "deleteType",
      Seq(classOf[play.mvc.Http.Request]),
      "DELETE",
      this.prefix + """type""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_UserController_registerUser8_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("user")))
  )
  private[this] lazy val controllers_UserController_registerUser8_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      UserController_2.registerUser(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "registerUser",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """user""",
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
    case controllers_RecipeController_patchRecipe2_route(params@_) =>
      call { 
        controllers_RecipeController_patchRecipe2_invoker.call(
          req => RecipeController_0.patchRecipe(req))
      }
  
    // @LINE:9
    case controllers_RecipeController_deleteRecipe3_route(params@_) =>
      call { 
        controllers_RecipeController_deleteRecipe3_invoker.call(
          req => RecipeController_0.deleteRecipe(req))
      }
  
    // @LINE:10
    case controllers_RecipeController_getRecipes4_route(params@_) =>
      call { 
        controllers_RecipeController_getRecipes4_invoker.call(
          req => RecipeController_0.getRecipes(req))
      }
  
    // @LINE:11
    case controllers_TypeController_createType5_route(params@_) =>
      call { 
        controllers_TypeController_createType5_invoker.call(
          req => TypeController_1.createType(req))
      }
  
    // @LINE:12
    case controllers_TypeController_getTypes6_route(params@_) =>
      call { 
        controllers_TypeController_getTypes6_invoker.call(
          req => TypeController_1.getTypes(req))
      }
  
    // @LINE:13
    case controllers_TypeController_deleteType7_route(params@_) =>
      call { 
        controllers_TypeController_deleteType7_invoker.call(
          req => TypeController_1.deleteType(req))
      }
  
    // @LINE:14
    case controllers_UserController_registerUser8_route(params@_) =>
      call { 
        controllers_UserController_registerUser8_invoker.call(
          req => UserController_2.registerUser(req))
      }
  }
}
