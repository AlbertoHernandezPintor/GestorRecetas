
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

object step extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[Step,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(step: Step):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<step>
    <name>"""),_display_(/*3.12*/step/*3.16*/.getName()),format.raw/*3.26*/("""</name>
    <description>"""),_display_(/*4.19*/step/*4.23*/.getDescription()),format.raw/*4.40*/("""</description>
</step>"""))
      }
    }
  }

  def render(step:Step): play.twirl.api.XmlFormat.Appendable = apply(step)

  def f:((Step) => play.twirl.api.XmlFormat.Appendable) = (step) => apply(step)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-01-30T16:57:28.922
                  SOURCE: /Users/davidmsl/Documents/Gestor recetas/app/views/step.scala.xml
                  HASH: 919b8ba558ac9a19d3aa5cbf500b04a4d8d2137b
                  MATRIX: 898->1|1003->14|1047->32|1059->36|1089->46|1141->72|1153->76|1190->93
                  LINES: 27->1|32->2|33->3|33->3|33->3|34->4|34->4|34->4
                  -- GENERATED --
              */
          