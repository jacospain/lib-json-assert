package com.jaco.pja

import com.jaco.pja.fields._

import scala.reflect.runtime.{universe => ru}

class CaseClassReflectiveInspector(mirror: ru.Mirror, caseClassCompanionSymbol: ru.Symbol) extends ObjectInspector {
  def fields(): Set[FieldDef] = fieldsImpl(caseClassCompanionSymbol).toSet

  private def fieldsImpl(obj: AnyRef): Seq[FieldDef] = caseClassFieldDefs(caseClassCompanionSymbol)

  private def caseClassFieldDefs(typeSymbol: ru.Symbol): Seq[FieldDef] = {
    val configCaseClassValSymbols = companionApplyParameterSymbols(typeSymbol)
    val fields = configCaseClassValSymbols.map{ symb =>
      val field = FieldDef(symb.asTerm.name.decodedName.toString, _)
      field(if (
        symb.typeSignature =:= ru.typeOf[Int] ||
        symb.typeSignature =:= ru.typeOf[Long] ||
        symb.typeSignature =:= ru.typeOf[Float] ||
        symb.typeSignature =:= ru.typeOf[Double]
      ) NumberType else if (
        symb.typeSignature =:= ru.typeOf[String]
      ) StringType else if (
        symb.typeSignature =:= ru.typeOf[Boolean]
      )
        BooleanType else if (
        symb.typeSignature <:< ru.typeOf[Seq[_]]
      ) SeqType(Seq(/* TBD */))
      else if (companionApplyParameterSymbols(symb.typeSignature.typeSymbol).nonEmpty)
        ObjectType(caseClassFieldDefs(symb.typeSignature.typeSymbol).toSet) /* TBD */
      else UnknownType
      )
    }
    fields
  }

  private def companionApplyParameterSymbols(typeSymbol: ru.Symbol): Seq[ru.Symbol] = {
    typeSymbol.companion.typeSignature.members.collectFirst {
      case symbol if symbol.isMethod && symbol.name == ru.TermName("apply") =>
        symbol.asMethod
    }.map { applyMethod =>
      applyMethod.paramLists.flatten
    }.getOrElse(Seq())
  }

}
