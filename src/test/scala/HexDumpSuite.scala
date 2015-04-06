import org.scalatest.FunSuite
import java.io._
import HexDump._

class HexDumpSuite extends FunSuite {
  
  test("return a list of chars") {
    val stream = new ByteArrayInputStream("abc".getBytes);
    assert(List(97,98,99) === bytes(stream))
  }
  
  test("format num to hex") {
    assert("20" === toHex(32))
    assert("0a" === toHex(10))
  }

  test("format one line") {
    assert("0000021 10 11 12 04 05" === formatLine(33, List(16,17,18,4,5)))
  }

  test("format two lines") {
    val expected = List("0000000 01 02 03", "0000003 ")
    assert(expected === formatLines(3)(List(1,2,3)))
  }
  
  test("format three lines") {
    val expected = List("0000000 00 01", "0000002 02 03", "0000004 04", "0000005 ")
    assert(expected === formatLines(2)(List(0,1,2,3,4)))
  }
 
  test("format an infinite stream") {
    def foreverZero: Stream[Int] = 0 #:: foreverZero
    val expected = List("0000000 00 00", "0000002 00 00", "0000004 00 00")
    assert(expected === formatLines(2)(foreverZero).take(3))
  }
  
  def split[A,B](f: A=>B, g: A=>B) = (x:A) => (f(x), g(x))
  
  test("split the input over a pair of functions") {
    val f = (x:Int) => x*x
    val g = (x:Int) => x*x*x
    assert((9, 27) === split(f,g)(3))
  }

}