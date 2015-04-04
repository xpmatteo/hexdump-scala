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
  
  def toStream(fileName: String) = 
    new BufferedInputStream(new FileInputStream(fileName))
  
  def formatLine(n: Int, values: List[Int]) = f"$n%07x " + values.map(toHex).mkString(" ")
  
  def formatLines(numbersPerLine: Int, numbers: List[Int]): String = {
    def f(numbers: List[Int], result: String, count: Int): String = {
      if (numbers.isEmpty)
        result + formatLine(count, numbers) + "\n"
      else
        f(
          numbers.drop(numbersPerLine), 
          result + formatLine(count, numbers.take(numbersPerLine)) + "\n", 
          count + numbers.take(numbersPerLine).size)
    }
    
    f(numbers, "", 0)
  }
  
  
	def	main(args: Array[String]) {
    print(formatLines(16, bytes(toStream(args(0)))))
	}
}