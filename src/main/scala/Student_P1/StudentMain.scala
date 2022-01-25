package Student_P1
import scala.io.StdIn
object StudentMain extends StudentDB {

  var s = new StudentDB()

  //var student=new Student()
  def main(args: Array[String]) {
    //mainMenu();
  }

  //def mainMenu(): Unit = {
    while (true) {
      println("1.Student Record")
      println("2.Add Student")
      println("3.Delete Student")
      println("4.Check By StudentID")
      println("5.Update Student")
      println("6.Exit")
      println("Enter your choice:")
      var lineInput = scala.io.StdIn.readInt()
      actionMatch(lineInput)
   // }

    def actionMatch(lineInput: Int) {
      //var i = lineInput
      lineInput match {
        case 1 => s.getAll()
        case 2 => s.addStudent()
        case 3 => s.deleteStudent()
        case 4 => s.getById()
        case 5 => s.updateStudent()
        case 6 => exit()
                  System.exit(0);
        case _ => println("Thank you")
      }

    }
  }
}