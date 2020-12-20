
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
    <allergens>
        """),_display_(/*6.10*/for(a <- ingredient.getAllergens()) yield /*6.45*/{_display_(Seq[Any](format.raw/*6.46*/("""
            """),_display_(/*7.14*/allergen(a)),format.raw/*7.25*/("""
        """)))}),format.raw/*8.10*/("""
    """),format.raw/*9.5*/("""</allergens>
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
                  DATE: 2020-12-20T20:10:59.800
                  SOURCE: /Volumes/LaCie/Alberto/Máster/Tecnologías del lado del servidor/Ejercicios/GestionRecetas/app/views/ingredient.scala.xml
                  HASH: 774b29287a5ad2de1670af2de8ce01fa303802b1
                  MATRIX: 910->1|1027->26|1077->50|1095->60|1125->70|1170->89|1188->99|1218->109|1277->142|1327->177|1365->178|1405->192|1436->203|1476->213|1507->218
                  LINES: 27->1|32->2|33->3|33->3|33->3|34->4|34->4|34->4|36->6|36->6|36->6|37->7|37->7|38->8|39->9
                  -- GENERATED --
              */
          