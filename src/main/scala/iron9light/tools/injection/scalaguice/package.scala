package iron9light.tools.injection

import com.google.inject.util.Types
import com.google.inject.{Key, TypeLiteral}

package object scalaguice {
  implicit def toInjector(gInjector: com.google.inject.Injector) = new GuiceInjector(gInjector)

//  implicit def toTypeLiteral[T: Manifest] = Helper.typeLiteral

  implicit def classType[T](mf: Manifest[T]): Class[T] = mf.erasure.asInstanceOf[Class[T]]

  implicit def typeLiteral[A: Manifest]: TypeLiteral[A] = {
    val a = manifest[A]
    val targs = a.typeArguments.map(_.erasure)
    TypeLiteral.get(Types.newParameterizedType(a.erasure, targs: _*)).asInstanceOf[TypeLiteral[A]]
  }

  implicit def key[A: Manifest] = Key.get(typeLiteral[A])
}