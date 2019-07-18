package com.jaco.pja

import play.api.libs.json.{JsObject, Json}

import scala.reflect.runtime.{universe => ru}


object Application extends App {
  val mirror = ru.runtimeMirror(this.getClass.getClassLoader)

/*  val inspector = new CaseClassReflectiveInspector(mirror, ru.typeOf[SteveCase].typeSymbol)
  inspector.fields().foreach(a => println(s"field: $a"))

  val inspector4 = new PlayJsonObjectInspector(Json.parse("""{"a":7}""").asInstanceOf[JsObject])
  inspector4.fields().foreach(a => println(s"field: $a"))
*/
}

