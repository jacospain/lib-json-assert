package com.jaco.pja

import com.jaco.pja.fields._
import play.api.libs.json._

class PlayJsonObjectInspector(obj: JsObject) extends ObjectInspector {

  def fields(): Set[FieldDef] = fields(obj).toSet

  private def fields(obj: JsObject): Seq[FieldDef] = obj.fields.map { field =>
    val partialFieldDef = FieldDef(field._1, _)
    partialFieldDef(fieldType(field._2))
  }.toSeq // TODO

  private def fieldType(jsValue: JsValue): TypeInfo = {
    jsValue match {
      case v: JsBoolean =>
        BooleanType
      case v: JsNumber =>
        NumberType
      case v: JsString =>
        StringType
      case v: JsObject =>
        ObjectType(fields(v).toSet)
      case v: JsArray =>
        SeqType(v.value.map(fieldType).toSeq) // TODO
      case JsNull =>
        UnknownType
    }
  }
}

// how are dates decoded by play-json?

// optional param to prepend to match expression in fieldType method?
