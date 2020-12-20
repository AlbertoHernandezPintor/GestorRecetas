
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

object allergen extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[String,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(allergen: String):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<allergen>"""),_display_(/*2.12*/allergen),format.raw/*2.20*/("""</allergen>"""))
      }
    }
  }

  def render(allergen:String): play.twirl.api.XmlFormat.Appendable = apply(allergen)

  def f:((String) => play.twirl.api.XmlFormat.Appendable) = (allergen) => apply(allergen)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-12-20T20:10:59.807
                  SOURCE: /Volumes/LaCie/Alberto/Máster/Tecnologías del lado del servidor/Ejercicios/GestionRecetas/app/views/allergen.scala.xml
                  HASH: 59b5dd584c19580a3394d686691a8f082888dab7
                  MATRIX: 904->1|1015->20|1052->31|1080->39
                  LINES: 27->1|32->2|32->2|32->2
                  -- GENERATED --
              */
          