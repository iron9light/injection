//package iron9light.tools.injection.scalaguice
//
//import com.google.inject.TypeLiteral
//import com.google.inject.util.Types
//import com.google.inject.Key
//
//object Helper {
//
//  def getClassType[T: Manifest]: Class[T] = manifest[T].erasure.asInstanceOf[Class[T]]
//
//  def ??? = sys.error("to implement")
//
//  def typeLiteral[A: Manifest]: TypeLiteral[A] = {
//    val a = manifest[A]
//    val targs = a.typeArguments.map(_.erasure)
//    TypeLiteral.get(Types.newParameterizedType(a.erasure, targs: _*)).asInstanceOf[TypeLiteral[A]]
//  }
//
//  def key[A: Manifest] = Key.get(typeLiteral[A])
//}