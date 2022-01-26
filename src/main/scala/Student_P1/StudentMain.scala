package Student_P1
import scala.io.StdIn
object StudentMain extends StudentDB {

  //var s = new StudentDB()
  var s = new StudentDbNew()

  def main(args: Array[String]) {

  }
  while (true) {
      val option="""
         |SELECT YOUR OPTION
         |1.Student Detail
         |2.Add Student Detail
         |3.Delete Student Detail
         |4.Student Detail BY ID
         |5.Update Student
         |6.Display Student Fees
         |7.ADD Student Fees
         |8.Exit
          |""".stripMargin
      println(option)
      var lineInput = scala.io.StdIn.readInt()
      actionMatch(lineInput)

    def actionMatch(lineInput: Int) {
      //var i = lineInput
      lineInput match {
        case 1 => s.getAll()
        case 2 => s.addStudent()
        case 3 => s.deleteStudent()
        case 4 => s.getById()
        case 5 => s.updateStudent()
        case 6 => s.getFees()
        case 7=> s.addStudentFees()
        case 8 => exit()
          System.exit(0);
        case _ => println("Thank you")
      }

    }
  }
}