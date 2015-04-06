import java.io._

object HexDump {
  
  val toJavaStream = (fileName: String) => 
    new BufferedInputStream(new FileInputStream(fileName))
  
  val bytes: InputStream => Stream[Int] = 
    stream => {
      val c = stream.read
      if (c == -1)
        Stream.empty
      else
        c #:: bytes(stream)
    }

  val toHex = (x: Int) => f"$x%02x"

  def formatLine(n: Int, values: Seq[Int]) = f"$n%07x " + values.map(toHex).mkString(" ")
  
  def formatLines(numbersPerLine: Int) = (numbers: Seq[Int]) => {
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
  
  val hexdump = toJavaStream andThen bytes andThen formatLines(16)
  
  
  def main(args: Array[String]) {
    hexdump(args(0)).foreach(println)
  }
}