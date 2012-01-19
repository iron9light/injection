package iron9light.tools.injection

/**
 * @author il
 */

trait Injector {
  def inject[T: Manifest]: T

  def injectOption[T: Manifest]: Option[T]
}