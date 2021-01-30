
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

object allergen extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[Allergen,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(allergen: Allergen):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<allergen>
    <name>"""),_display_(/*3.12*/allergen/*3.20*/.getName()),format.raw/*3.30*/("""</name>
    <diseases>"""),_display_(/*4.16*/allergen/*4.24*/.getDiseases()),format.raw/*4.38*/("""</diseases>
</allergen>"""))
      }
    }
  }

  def render(allergen:Allergen): play.twirl.api.XmlFormat.Appendable = apply(allergen)

  def f:((Allergen) => play.twirl.api.XmlFormat.Appendable) = (allergen) => apply(allergen)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2021-01-30T16:57:28.950
                  SOURCE: /Users/davidmsl/Documents/Gestor recetas/app/views/allergen.scala.xml
                  HASH: 43044eb9f4080e71ffb51b3853bd43a00e3c08d9
                  MATRIX: 906->1|1019->22|1067->44|1083->52|1113->62|1162->85|1178->93|1212->107
                  LINES: 27->1|32->2|33->3|33->3|33->3|34->4|34->4|34->4
                  -- GENERATED --
              */
          