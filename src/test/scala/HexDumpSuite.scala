import org.scalatest.FunSuite
import java.io._
import HexDump._

class HexDumpSuite extends FunSuite {
  
  test("return a list of chars") {
    val stream = new ByteArrayInputStream("abc".getBytes);
    assert(List(97,98,99) == bytes(stream))
  }
  
  test("format num to hex") {
    assert("20" === toHex(32))
    assert("0a" === toHex(10))
  }

  test("format list of numbers to hex") {
    assert(List("aa", "0a", "11") === List(170, 10, 17).map(toHex))
  }

  test("format list to 3 columns") {
    assert(List() === split(3, List()));
    assert(List(List(1)) === split(3, List(1)));
    assert(List(List(1,2,3)) === split(3, List(1,2,3)));
    assert(List(List(1,2,3), List(4)) === split(3, List(1,2,3,4)));
    assert(List(List(1,2,3), List(4,5,6)) === split(3, List(1,2,3,4,5,6)))
  }
 
}