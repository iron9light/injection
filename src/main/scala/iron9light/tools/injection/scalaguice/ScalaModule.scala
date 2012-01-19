package iron9light.tools.injection.scalaguice

import net.liftweb.common.Loggable
import com.google.inject.{Provides, AbstractModule}
import iron9light.tools.injection.Injector

class ScalaModule(val configurations: Configuration*) extends AbstractModule with Loggable {
  def configure() {
    configurations.foreach {
      c =>
        logger.debug("configuring " + c)
        c.bind(binder())
    }
  }

  @Provides
  def provideInjector(gInjector: com.google.inject.Injector): Injector = gInjector
}

class ScalaMergedModule(val configurations: Configuration*) extends AbstractModule with Loggable {
  def configure() {

    logger.debug("configuring merged module")

    import ConfigurationMonoid._
    // merge all configurations into one
    // they will override themselves
    val mergedConfig = configurations.foldLeft(mzero)(mappend)

    mergedConfig.bind(binder())
  }

  @Provides
  def provideInjector(gInjector: com.google.inject.Injector): Injector = gInjector
}