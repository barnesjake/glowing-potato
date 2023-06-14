
/**
 * Simple safe equals so you don't have to import cats library - unless you already do then ignore
 */
object SafeEquals {
  implicit class EqualsOps[A](v: A) {
    def ===(other: A): Boolean = v == other
    def =!=(other: A): Boolean = v != other
  }
}
