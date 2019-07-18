package com.jaco.pja.fields

case class FieldDef(name: String, typeInfo: TypeInfo)

sealed trait TypeInfo

object BooleanType extends TypeInfo

object StringType extends TypeInfo

object NumberType extends TypeInfo

object UnknownType extends TypeInfo

case class ObjectType(fields: Set[FieldDef]) extends TypeInfo

case class SeqType(containedTypes: Seq[TypeInfo]) extends TypeInfo

/* case class CustomType(typeName: String) extends TypeInfo */
