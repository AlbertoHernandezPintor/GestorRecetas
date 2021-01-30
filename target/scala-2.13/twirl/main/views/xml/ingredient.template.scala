
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

object ingredient extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[Ingredient,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(ingredient: Ingredient):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<ingredient>
    <name>"""),_display_(/*3.12*/ingredient/*3.22*/.getName()),format.raw/*3.32*/("""</name>
    <type>"""),_display_(/*4.12*/ingredient/*4.22*/.getType()),format.raw/*4.32*/("""</type>
</ingredient>"""))
      }
    }
  }

  def render(ingredient:Ingredient): play.twirl.api.XmlFormat.Appendable = apply(ingredient)

  def f:((Ingredient) => play.twirl.api.XmlFormat.Appendable) = (ingredient) => apply(ingredient)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-01-30T16:57:29.008
                  SOURCE: /Users/davidmsl/Documents/Gestor recetas/app/views/ingredient.scala.xml
                  HASH: ea0cb265f210c7c00a1094778c96cad9a91912e2
                  MATRIX: 910->1|1027->26|1077->50|1095->60|1125->70|1170->89|1188->99|1218->109
                  LINES: 27->1|32->2|33->3|33->3|33->3|34->4|34->4|34->4
                  -- GENERATED --
              */
          