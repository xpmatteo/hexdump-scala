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
    val expected = "0000000 01 02 03\n0000003 \n"
    assert(expected === formatLines(3, List(1,2,3)))
  }
  
  test("format three lines") {
    val expected = "0000000 00 01\n0000002 02 03\n0000004 04\n0000005 \n"
    assert(expected === formatLines(2, List(0,1,2,3,4)))
  }
  
}