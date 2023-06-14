// needs play and cats libraries

import cats.implicits.catsSyntaxEq
import play.api.libs.json._

object JsonKeyChanger {
  implicit class JsObjectStuff(private val jsObject: JsObject) extends AnyVal {
    def transformIfFieldName(fieldName: String, transformation: JsValue => JsValue): JsObject = {
        @SuppressWarnings(Array("org.wartremover.warts.Recursion"))
        def transform(currentFieldNameAndJsValue: (String, JsValue)): (String, JsValue) = {
          val (currentFieldName, currentJsValue) = currentFieldNameAndJsValue
          currentJsValue match {
            case JsObject(underlying) =>
              val transformedJsObject = JsObject(underlying.map(transform))
              if (currentFieldName === fieldName) {
                (currentFieldName, transformation(transformedJsObject))
              } else {
                (currentFieldName, transformedJsObject)
              }
            case JsArray(arr) =>
              val transformedJsArray: JsArray = JsArray(arr.map(j => transform((currentFieldName, j))._2))
              if (currentFieldName === fieldName) {
                (currentFieldName, transformation(transformedJsArray))
              } else {
                (currentFieldName, transformedJsArray)
              }
            case value if currentFieldName === fieldName => (currentFieldName, transformation(value))
            case value if currentFieldName =!= fieldName => (currentFieldName, value)
            case value: JsValue                          => (currentFieldName, value)
          }
        }

      JsObject(jsObject.fieldSet.map(transform).toSeq)
    }
  }
}
  
object ExampleOfUsing {
  
  final case class SomeCaseClass(name: String, age: Int)
  
  //just some dumb method for example
  private def add10(jsValue: JsValue): JsNumber = jsValue match {
    case j: JsNumber => JsNumber(j.value * 100)
    case e           => throw new IllegalArgumentException(s"Expected JsNumber but got ${e.getClass.toString}")
  }
  
 implicit val writes: OWrites[SomeCaseClass] = {
    val w: OWrites[SomeCaseClass] = Json.writes[SomeCaseClass]
    w.transform { jsObject: JsObject =>
      jsObject
        .transformIfFieldName("name", add10)
    }
  } 
}
