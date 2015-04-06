import org.scalatest.FunSuite
import java.io._
import HexDump._

class HexDumpSuite extends FunSuite {
  
  test("convert java stream to scala stream") {
    val stream = new ByteArrayInputStream("abc".getBytes);
    assert(Stream(97,98,99) === toScalaStream(stream))
  }
  
  test("format num to hex") {
    assert("20" === toHex(32))
    assert("0a" === toHex(10))
  }

  test("split the input over a pair of functions") {
    val f = (x:Int) => x*x
    val g = (x:Int) => x*x*x
    assert((9, 27) === split(f,g)(3))
  }

  test("merging streams") {
    val f = List("a", "b", "c")
    val g = List("x", "y", "z")
    assert(List("a x", "b y", "c z") === merge(f, g))
  }
  
  test("merging streams of different lengths") {
    val f = List("a")
    val g = List("x", "y", "z")
    assert(List("a x", "y", "z") === merge(f, g))
  }
  
  test("byte counts") {
    assert(List("0000000", "0000003") === byteCounts(List(0, 1, 2)))
    assert(List("0000000", "0000010", "0000011") === byteCounts(List.range(0, 17)))
  }
  
  test("byte dumps") {
    val expected = List("00 01 02 03 04 05 06 07 08 09 0a 0b 0c 0d 0e 0f", "10 11")
    assert(expected === byteDumps(List.range(0, 18)))
  }
  
  test("partially evaluate an infinite stream") {
    val expected = List("0000000 00 01 02 03 04 05 06 07 08 09 0a 0b 0c 0d 0e 0f")
    assert(expected === formatLines(Stream.from(0)).take(1))
  }

}