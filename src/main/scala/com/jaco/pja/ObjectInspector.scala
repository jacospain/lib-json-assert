package com.jaco.pja

import com.jaco.pja.fields.FieldDef

trait ObjectInspector {
  def fields(): Set[FieldDef]
}
