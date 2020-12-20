
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

object recipe extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[Recipe,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(recipe: Recipe):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""<recipe>
    <name>"""),_display_(/*3.12*/recipe/*3.18*/.getName()),format.raw/*3.28*/("""</name>
    <type>"""),_display_(/*4.12*/recipe/*4.18*/.getType()),format.raw/*4.28*/("""</type>
    <time>"""),_display_(/*5.12*/recipe/*5.18*/.getTime()),format.raw/*5.28*/("""</time>
    <difficulty>"""),_display_(/*6.18*/recipe/*6.24*/.getDifficulty()),format.raw/*6.40*/("""</difficulty>
    <ingredients>
        """),_display_(/*8.10*/for(i <- recipe.getIngredients()) yield /*8.43*/{_display_(Seq[Any](format.raw/*8.44*/("""
            """),_display_(/*9.14*/ingredient(i)),format.raw/*9.27*/("""
        """)))}),format.raw/*10.10*/("""
    """),format.raw/*11.5*/("""</ingredients>
    <steps>
        """),_display_(/*13.10*/for(s <- recipe.getSteps()) yield /*13.37*/{_display_(Seq[Any](format.raw/*13.38*/("""
            """),_display_(/*14.14*/step(s)),format.raw/*14.21*/("""
        """)))}),format.raw/*15.10*/("""
    """),format.raw/*16.5*/("""</steps>
</recipe>"""))
      }
    }
  }

  def render(recipe:Recipe): play.twirl.api.XmlFormat.Appendable = apply(recipe)

  def f:((Recipe) => play.twirl.api.XmlFormat.Appendable) = (recipe) => apply(recipe)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: 2020-12-20T20:25:34.225
                  SOURCE: /Volumes/LaCie/Alberto/Máster/Tecnologías del lado del servidor/Ejercicios/GestionRecetas/app/views/recipe.scala.xml
                  HASH: b51c52a8c79becb2f8973340ce1025fd79d4d3f8
                  MATRIX: 902->1|1011->18|1057->38|1071->44|1101->54|1146->73|1160->79|1190->89|1235->108|1249->114|1279->124|1330->149|1344->155|1380->171|1447->212|1495->245|1533->246|1573->260|1606->273|1647->283|1679->288|1742->324|1785->351|1824->352|1865->366|1893->373|1934->383|1966->388
                  LINES: 27->1|32->2|33->3|33->3|33->3|34->4|34->4|34->4|35->5|35->5|35->5|36->6|36->6|36->6|38->8|38->8|38->8|39->9|39->9|40->10|41->11|43->13|43->13|43->13|44->14|44->14|45->15|46->16
                  -- GENERATED --
              */
          