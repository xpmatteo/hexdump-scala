import java.io._

object HexDump {
  
  def bytes(stream: InputStream): Stream[Int] = {
    val c = stream.read
    if (c == -1)
      Stream.empty
    else
      c #:: bytes(stream)
  }

  def toStream(fileName: String) = 
    new BufferedInputStream(new FileInputStream(fileName))
  
  def toHex(x: Int) = f"$x%02x"

  def formatLine(n: Int, values: Seq[Int]) = f"$n%07x " + values.map(toHex).mkString(" ")
  
  def formatLines(numbersPerLine: Int, numbers: Seq[Int]): Stream[String] = {
    def loop(numbers: Seq[Int], count: Int): Stream[String] = {
      if (numbers.isEmpty)
        formatLine(count, numbers) #:: Stream.empty
      else {
        val prefix = numbers.take(numbersPerLine)
        formatLine(count, prefix) #:: loop(numbers.drop(numbersPerLine), count + prefix.size)
      }
    }
    
    loop(numbers, 0)
  }
  
  
  def main(args: Array[String]) {
    formatLines(16, bytes(toStream(args(0)))).foreach(println)
  }
}