
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

object user extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[User,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(user: User):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<?xml version="1.0" encoding="utf-8" ?>
<user>
    <name>"""),_display_(/*4.12*/user/*4.16*/.getName()),format.raw/*4.26*/("""</name>
    <age>"""),_display_(/*5.11*/user/*5.15*/.getAge()),format.raw/*5.24*/("""</age>
    """),_display_(/*6.6*/if(user.getAge() >= 18)/*6.29*/ {_display_(Seq[Any](format.raw/*6.31*/("""
        """),format.raw/*7.9*/("""<adult/>
    """)))}),format.raw/*8.6*/("""
"""),format.raw/*9.1*/("""</user>"""))
      }
    }
  }

  def render(user:User): play.twirl.api.XmlFormat.Appendable = apply(user)

  def f:((User) => play.twirl.api.XmlFormat.Appendable) = (user) => apply(user)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-12-09T19:25:18.832
                  SOURCE: /Volumes/LaCie/Alberto/Máster/Tecnologías del lado del servidor/Ejercicios/PlayExample/app/views/user.scala.xml
                  HASH: 3f8e14326121d2b72ea940942cd54c5a9ee5a9ee
                  MATRIX: 898->1|1003->14|1087->72|1099->76|1129->86|1173->104|1185->108|1214->117|1251->129|1282->152|1321->154|1356->163|1399->177|1426->178
                  LINES: 27->1|32->2|34->4|34->4|34->4|35->5|35->5|35->5|36->6|36->6|36->6|37->7|38->8|39->9
                  -- GENERATED --
              */
          