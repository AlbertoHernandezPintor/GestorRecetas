
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
    <allergens>
        """),_display_(/*13.10*/for(a <- recipe.getAllergens()) yield /*13.41*/{_display_(Seq[Any](format.raw/*13.42*/("""
            """),_display_(/*14.14*/allergen(a)),format.raw/*14.25*/("""
        """)))}),format.raw/*15.10*/("""
    """),format.raw/*16.5*/("""</allergens>
    <steps>
        """),_display_(/*18.10*/for(s <- recipe.getSteps()) yield /*18.37*/{_display_(Seq[Any](format.raw/*18.38*/("""
            """),_display_(/*19.14*/step(s)),format.raw/*19.21*/("""
        """)))}),format.raw/*20.10*/("""
    """),format.raw/*21.5*/("""</steps>
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
                  DATE: 2021-01-30T16:57:28.901
                  SOURCE: /Users/davidmsl/Documents/Gestor recetas/app/views/recipe.scala.xml
                  HASH: 0c474732beaa3da14f7bd14723a2d3476785230a
                  MATRIX: 902->1|1011->18|1057->38|1071->44|1101->54|1146->73|1160->79|1190->89|1235->108|1249->114|1279->124|1330->149|1344->155|1380->171|1447->212|1495->245|1533->246|1573->260|1606->273|1647->283|1679->288|1746->328|1793->359|1832->360|1873->374|1905->385|1946->395|1978->400|2039->434|2082->461|2121->462|2162->476|2190->483|2231->493|2263->498
                  LINES: 27->1|32->2|33->3|33->3|33->3|34->4|34->4|34->4|35->5|35->5|35->5|36->6|36->6|36->6|38->8|38->8|38->8|39->9|39->9|40->10|41->11|43->13|43->13|43->13|44->14|44->14|45->15|46->16|48->18|48->18|48->18|49->19|49->19|50->20|51->21
                  -- GENERATED --
              */
          