package iron9light.tools.injection.scalaguice

import javax.inject.Inject
import com.google.inject.{Injector => GInjector}
import iron9light.tools.injection.Injector

/**
 * @author il
 */

class GuiceInjector @Inject()(private val gInjector: GInjector) extends Injector {
  def inject[T: Manifest] = gInjector.getInstance(manifest[T])

  def injectOption[T: Manifest] = try {
    Some(inject[T])
  } catch {
    case _ => None
  }
}

object GuiceInjector {
  implicit def toGInjector(injector: GuiceInjector) = injector.gInjector
}