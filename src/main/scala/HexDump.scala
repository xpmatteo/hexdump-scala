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
  
  def toStream(fileName: String) = new FileInputStream(fileName)
  
  def formatLine(n: Int, values: List[Int]) = f"$n%07x " + values.map(toHex).mkString(" ")
  
  def formatLines(num: Int, numbers: List[Int]): String = {
    formatLines(num, numbers, "", 0)
  }
  
  def formatLines(num: Int, numbers: List[Int], result: String, count: Int): String = {
    if (numbers.isEmpty)
      return result + formatLine(count, numbers) + "\n"
    else
      return formatLines(
        num, 
        numbers.drop(num), 
        result + formatLine(count, numbers.take(num)) + "\n", 
        count + numbers.take(num).size)
  }
  
	def	main(args: Array[String]) {
    print(formatLines(16, bytes(toStream(args(0)))))
	}
}