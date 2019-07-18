package com.jaco.pja
import org.scalatest.{FunSpec, Matchers}
import play.api.libs.json.{JsObject, Json}

import scala.reflect.runtime.{universe => ru}

class ModelContentParitySpec extends FunSpec with Matchers {
  describe("static content") {
    it("should have all fields modeled in code and only fields modeled in code") {
      val mirror = ru.runtimeMirror(this.getClass.getClassLoader)

      val caseClassInspector = new CaseClassReflectiveInspector(mirror, ru.typeOf[Configuration].typeSymbol)

      val jsObject = Json.parse(this.getClass.getClassLoader.getResourceAsStream("staticContent-a.json")).asInstanceOf[JsObject]
      val jsonInspector = new PlayJsonObjectInspector(jsObject)

      withClue(){ caseClassInspector.fields() shouldBe jsonInspector.fields()}
    }
  }
}
