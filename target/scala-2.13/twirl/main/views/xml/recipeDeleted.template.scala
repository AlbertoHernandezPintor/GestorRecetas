
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

object recipeDeleted extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template2[Recipe,play.i18n.Messages,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(recipe: Recipe, messages: play.i18n.Messages):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<?xml version="1.0" encoding="utf-8" ?>
<content>
    <success>true</success>
    <message>"""),_display_(/*5.15*/messages/*5.23*/.at("info.message-recipe-deleted", recipe.getName())),format.raw/*5.75*/("""</message>
</content>"""))
      }
    }
  }

  def render(recipe:Recipe,messages:play.i18n.Messages): play.twirl.api.XmlFormat.Appendable = apply(recipe,messages)

  def f:((Recipe,play.i18n.Messages) => play.twirl.api.XmlFormat.Appendable) = (recipe,messages) => apply(recipe,messages)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-01-30T16:57:29.132
                  SOURCE: /Users/davidmsl/Documents/Gestor recetas/app/views/recipeDeleted.scala.xml
                  HASH: cb7f3b17db218705fa202f98cd95b021df2a3f3e
                  MATRIX: 928->1|1067->48|1185->140|1201->148|1273->200
                  LINES: 27->1|32->2|35->5|35->5|35->5
                  -- GENERATED --
              */
          