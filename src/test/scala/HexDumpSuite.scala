import org.scalatest.FunSuite
import java.io._

class HexDumpSuite extends FunSuite {
  def bytes(stream: InputStream): List[Int] = {
    var result = Nil: List[Int]
    var c = stream.read
    while (c != -1) {
      result = c :: result
      c = stream.read
    }
    return result.reverse
  }

	def toHex(x: Int) = Integer.toString(x, 16)
	
	def	split[T](n: Int, values: List[T]): List[List[T]] = {
		if (values.isEmpty)
			Nil
		else
			List(values.take(n)) ::: split(n, values.drop(n))
	}
	
	def	formatLine(values: List[String]): String = {
		"%s %s %s" format values
	}

	// -----

  test("return a list of chars") {
    val stream = new ByteArrayInputStream("abc".getBytes);
    assert(List(97,98,99) == bytes(stream))
  }
  
  test("format num to hex") {
    assert("20" === toHex(32))
  }

  test("format list of numbers to hex") {
    assert(List("aa", "a", "11") === List(170, 10, 17).map(toHex))
  }

	test("format list to 3 columns") {
   	assert(List() === split(3, List()));
    assert(List(List(1)) === split(3, List(1)));
    assert(List(List(1,2,3)) === split(3, List(1,2,3)));
    assert(List(List(1,2,3), List(4)) === split(3, List(1,2,3,4)));
   	assert(List(List(1,2,3), List(4,5,6)) === split(3, List(1,2,3,4,5,6)))
	}

	test("format list of strings to output") {
		assert("aa bb cc" === formatLine(List("aa", "bb", "cc")))
	}
	
}