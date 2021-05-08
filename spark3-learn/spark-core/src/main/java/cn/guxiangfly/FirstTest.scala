import org.apache.spark.{SparkConf, SparkContext}

object FirstTest {

  def main(args: Array[String]): Unit = {
    print("hello spark")

    val conf = new SparkConf().setMaster("local").setAppName("WordCount")
    val sc = new SparkContext()


  }
}