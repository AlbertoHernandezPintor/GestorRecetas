
package views.xml

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.xml._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.data._
import play.core.j.PlayFormsMagicForJava._
import scala.jdk.CollectionConverters._

object recipeCreated extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[Recipe,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(recipe: Recipe):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<?xml version="1.0" encoding="utf-8" ?>
<content>
    <success>true</success>
    <message>La receta """),_display_(/*5.25*/recipe/*5.31*/.getName()),format.raw/*5.41*/(""" """),format.raw/*5.42*/("""ha sido creada con éxito</message>
</content>"""))
      }
    }
  }

  def render(recipe:Recipe): play.twirl.api.XmlFormat.Appendable = apply(recipe)

  def f:((Recipe) => play.twirl.api.XmlFormat.Appendable) = (recipe) => apply(recipe)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-12-20T18:58:21.342
                  SOURCE: /Volumes/LaCie/Alberto/Máster/Tecnologías del lado del servidor/Ejercicios/GestionRecetas/app/views/recipeCreated.scala.xml
                  HASH: 388bf3a7014df94edbc682ace5cbb69aa38d1097
                  MATRIX: 909->1|1018->18|1146->120|1160->126|1190->136|1218->137
                  LINES: 27->1|32->2|35->5|35->5|35->5|35->5
                  -- GENERATED --
              */
          