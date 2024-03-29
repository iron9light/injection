package iron9light.tools.injection.scalaguice

import binder.ScalaAnnotatedBindingBuilder

trait ConfigurationBuilder {

  private val threadLocalBuilders = new ThreadLocal[Map[Class[_], ScalaAnnotatedBindingBuilder[_]]] {
    override def initialValue() = Map.empty[Class[_], ScalaAnnotatedBindingBuilder[_]]
  }

  def config(config: => Unit): Configuration = {
    config
    val builders = threadLocalBuilders.get
    threadLocalBuilders.remove()
    new Configuration(builders)
  }

  def bind[T](implicit manifT: Manifest[T]): ScalaAnnotatedBindingBuilder[T] = {
    val builder = new ScalaAnnotatedBindingBuilder[T]
    threadLocalBuilders.set(threadLocalBuilders.get + (classType(manifT) -> builder))
    builder
  }
}

object ConfigurationBuilder extends ConfigurationBuilder