import java.io._
import String._

object HexDump {
  
  def bytes(stream: InputStream): Stream[Int] = {
    val c = stream.read
    if (c == -1)
      Stream.empty
    else
      c #:: bytes(stream)
  }

  def toHex(x: Int) = f"$x%02x"
  
  def toStream(fileName: String) = 
    new BufferedInputStream(new FileInputStream(fileName))
  
  def formatLine(n: Int, values: Seq[Int]) = f"$n%07x " + values.map(toHex).mkString(" ")
  
  def formatLines(numbersPerLine: Int, numbers: Seq[Int]): String = {
    def f(numbers: Seq[Int], result: String, count: Int): String = {
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