
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

object recipes extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[List[Recipe],play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(recipes: List[Recipe]):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<?xml version="1.0" encoding="utf-8" ?>
<recipes>
    """),_display_(/*4.6*/for(r <- recipes) yield /*4.23*/{_display_(Seq[Any](format.raw/*4.24*/("""
        """),_display_(/*5.10*/recipe(r)),format.raw/*5.19*/("""
    """)))}),format.raw/*6.6*/("""
"""),format.raw/*7.1*/("""</recipes>"""))
      }
    }
  }

  def render(recipes:List[Recipe]): play.twirl.api.XmlFormat.Appendable = apply(recipes)

  def f:((List[Recipe]) => play.twirl.api.XmlFormat.Appendable) = (recipes) => apply(recipes)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-12-20T20:23:31.599
                  SOURCE: /Volumes/LaCie/Alberto/Máster/Tecnologías del lado del servidor/Ejercicios/GestionRecetas/app/views/recipes.scala.xml
                  HASH: 7e6328112dbc2c14c5769264c1841b3f89a18692
                  MATRIX: 909->1|1025->25|1105->80|1137->97|1175->98|1211->108|1240->117|1275->123|1302->124
                  LINES: 27->1|32->2|34->4|34->4|34->4|35->5|35->5|36->6|37->7
                  -- GENERATED --
              */
          