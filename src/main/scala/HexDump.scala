import java.io._

object HexDump {
  
  val toJavaStream = (fileName: String) => 
    new BufferedInputStream(new FileInputStream(fileName))
  
  val toScalaStream: InputStream => Stream[Int] = 
    stream => {
      val c = stream.read
      if (c == -1)
        Stream.empty
      else
        c #:: toScalaStream(stream)
    }

  def split[A,B](f: A=>B, g: A=>B) = (x:A) => (f(x), g(x))

  val merge: (Seq[String], Seq[String]) => Stream[String] = 
  (a,b) => 
    if (a.isEmpty && b.isEmpty)
      Stream.empty
    else if (a.isEmpty)
      b.head #:: merge(Nil, b.tail)
    else if (b.isEmpty)
      a.head #:: merge(a.tail, Nil)
    else
      (a.head + " " + b.head) #:: merge(a.tail, b.tail)
  
  def byteCounts: Seq[Int] => Stream[String] = s =>
  {
    def loop(count: Int, s: Seq[Int]): Stream[String] = 
      if (s.isEmpty)
        f"$count%07x" #:: Stream.empty
      else 
        f"$count%07x" #:: loop(count + s.take(16).length, s.drop(16))
 
    loop(0, s)
  }

  val toHex = (x: Int) => f"$x%02x"
  
  val byteDumps: Seq[Int] => Stream[String] = 
    s =>
    if (s.isEmpty) 
      Stream.empty 
    else
      s.take(16).map(toHex).mkString(" ") #:: byteDumps(s.drop(16))

  val formatLines = split(byteCounts, byteDumps) andThen merge.tupled
  
  val hexdump = toJavaStream andThen toScalaStream andThen formatLines
  
  
  def main(args: Array[String]) {
    hexdump(args(0)).foreach(println)
  }
}