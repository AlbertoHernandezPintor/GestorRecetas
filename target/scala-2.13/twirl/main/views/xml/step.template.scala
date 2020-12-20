
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

object step extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[String,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(step: String):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<step>"""),_display_(/*2.8*/step),format.raw/*2.12*/("""</step>"""))
      }
    }
  }

  def render(step:String): play.twirl.api.XmlFormat.Appendable = apply(step)

  def f:((String) => play.twirl.api.XmlFormat.Appendable) = (step) => apply(step)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-12-20T20:09:24.599
                  SOURCE: /Volumes/LaCie/Alberto/Máster/Tecnologías del lado del servidor/Ejercicios/GestionRecetas/app/views/step.scala.xml
                  HASH: 5699e212392efd107f37769363b7edf6d3ada9f0
                  MATRIX: 900->1|1007->16|1039->23|1063->27
                  LINES: 27->1|32->2|32->2|32->2
                  -- GENERATED --
              */
          