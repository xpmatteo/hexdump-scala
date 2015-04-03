import java.io._
import String._

object HexDump {
  
  def bytes(stream: InputStream): List[Int] = {
    var result = Nil: List[Int]
    var c = stream.read
    while (c != -1) {
      result = c :: result
      c = stream.read
    }
    return result.reverse
  }

  def toHex(x: Int) = f"$x%02x"
  
  def split[T](n: Int, values: List[T]): List[List[T]] = {
    if (values.isEmpty)
      Nil
    else
      List(values.take(n)) ::: split(n, values.drop(n))
  }
  
	def	main(args: Array[String]) {
		println(bytes(new FileInputStream(args(0))).map(toHex).mkString(" "))
	}
}