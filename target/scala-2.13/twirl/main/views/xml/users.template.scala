
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

object users extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[List[User],play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(users: List[User]):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<?xml version="1.0" encoding="UTF-8" ?>
<users>
    """),_display_(/*4.6*/for(u <- users) yield /*4.21*/{_display_(Seq[Any](format.raw/*4.22*/("""
        """),_display_(/*5.10*/user(u)),format.raw/*5.17*/("""
    """)))}),format.raw/*6.6*/("""
"""),format.raw/*7.1*/("""</users>
"""))
      }
    }
  }

  def render(users:List[User]): play.twirl.api.XmlFormat.Appendable = apply(users)

  def f:((List[User]) => play.twirl.api.XmlFormat.Appendable) = (users) => apply(users)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-12-09T19:25:18.845
                  SOURCE: /Volumes/LaCie/Alberto/Máster/Tecnologías del lado del servidor/Ejercicios/PlayExample/app/views/users.scala.xml
                  HASH: 80ac825944b86af0fee62f168552a6f000fd7cd0
                  MATRIX: 905->1|1017->21|1095->74|1125->89|1163->90|1199->100|1226->107|1261->113|1288->114
                  LINES: 27->1|32->2|34->4|34->4|34->4|35->5|35->5|36->6|37->7
                  -- GENERATED --
              */
          