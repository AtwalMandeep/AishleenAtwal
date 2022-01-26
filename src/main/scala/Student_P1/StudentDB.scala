package Student_P1
import java.io.IOException
import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet, Statement}
import scala.io.StdIn.readLine

class StudentDB{
  var student = new Student()
  var resultSet: ResultSet = null
  var connection: Connection = null
  var myStmt: PreparedStatement=null

  def   dataConnect(): Connection={
    val url = "jdbc:mysql://localhost:3306/studentinformation"
    val username = "root";
    val password = "matwal";
    //var connection: Connection = null
   // var resultSet: ResultSet = null
    try {
      connection = DriverManager.getConnection(url, username, password)
     // println("connection created");
    } catch {
      case e => e.printStackTrace
    }
    return connection
  }

  def executeQuery(cmd: String): ResultSet ={
    var statement = connection.createStatement()
     resultSet = statement.executeQuery(cmd)
     resultSet
  }

  def  getAll(){
    println("***********************Student Detail************************")
    val cmd ="Select Student_ID,First_Name,Last_Name,Contact_Number from student"
    //val cmd ="Select Student_ID,First_Name,Last_Name,Contact_Number from student ORDER BY student_ID $Student_ID";
    dataConnect()
    var getResult:ResultSet=executeQuery(cmd);
    println("Student_Id\t\t"+"FirstName\t\t\t\t"+"Last_Name\t\t\t\t"+"Contact_Number\t\t")
    while (getResult.next()) {
             /* var Student_ID=student.getId()
               student.setId(Student_ID)
              var First_Name=student.getName()
               student.setName(First_Name)
               var Last_Name=student.getLastName()
               student.setLastName(Last_Name)
               var Contact_Number=student.getContact()
               student.setContact(Contact_Number)*/
               println( getResult.getString("Student_ID")+"\t\t\t\t\t"+
                       getResult.getString("First_Name")+"\t\t\t\t"+
                       getResult.getString("Last_Name")+"\t\t\t\t\t"+
                       getResult.getString("Contact_Number"))+"\t\t\t";

    }
  }

  def addStudent(): Unit={
    println("***********************Insert Student************************")
    //println("Add Student")
    try {

        println("Enter studentID")
        var Student_ID = scala.io.StdIn.readInt()
        println("Enter First_Name")
        var First_Name = scala.io.StdIn.readLine()
        println("Enter Last_Name")
        var Last_Name = scala.io.StdIn.readLine()
        println("Enter Contact_Number")
        var Contact_Number = scala.io.StdIn.readLine()
        var cmd= "INSERT INTO student(Student_ID,First_Name,Last_Name,Contact_Number) VALUES ( ?,?,?,? )";
        println(cmd);
        var db=dataConnect()
        myStmt = db.prepareStatement(cmd);
        myStmt.setInt(1,Student_ID)
        myStmt.setString(2,First_Name)
        myStmt.setString(3,Last_Name)
        myStmt.setString(4,Contact_Number)
        myStmt.executeUpdate()
       // println("executed")
    }
    catch {
      case e => e.printStackTrace
    }
    getAll()
  }

  def updateStudent(): Unit={
    println("***********************Update Student************************")
    //println("Update")
    var db=dataConnect()
    try {
      println("Enter Student_ID")
      var Student_ID = readLine()
      println("Enter First_Name")
      var First_Name = readLine()
      println("Enter Last_Name")
      var Last_Name = readLine()
      println("Enter Contact_Number")
      var Contact_Number = readLine()
      println("Student Id"+Student_ID);
      //  var cmd=s"UPDATE student set First_Name=$First_Name,Last_Name=$Last_Name,Contact_Number=$Contact_Number WHERE Student_ID=$Student_ID"
      var cmd= s"update student set First_Name='$First_Name',Last_Name='$Last_Name',Contact_Number='$Contact_Number' where Student_ID=$Student_ID";
      println(cmd)
      myStmt = db.prepareStatement(cmd);
      //println(cmd);
      myStmt.executeUpdate()
      //println(cmd)
    }
    catch {
      case e => e.printStackTrace
    }
    getAll()
   }

  def deleteStudent(): Unit ={
    println("***********************Delete Student************************")
   // println("Delete")
    var db=dataConnect()
    println("Enter studentID You Want To Delete")
    var Student_ID = scala.io.StdIn.readInt()
    try {
      var cmd=s"delete from student where Student_ID=$Student_ID"
      println(cmd);
      myStmt = db.prepareStatement(cmd);
      myStmt.executeUpdate()
      println("deleted")
    }
    catch {
      case e => e.printStackTrace
    }
    getAll()
  }

  def getById():Unit= {
    //println("GetByID")
    println("***********************Select Student By ID************************")
    var db=dataConnect()
    println("Enter studentID For Record Check")
    var Student_ID = scala.io.StdIn.readInt()
    try
    {
      var cmd=s"Select Student_ID,First_Name,Last_Name,Contact_Number from student where Student_ID=$Student_ID"
      var getResult:ResultSet=executeQuery(cmd);
      while (getResult.next()) {
        var Student_ID = student.getId()
        student.setId(Student_ID)
        var First_Name = student.getName()
        student.setName(First_Name)
        var Last_Name = student.getLastName()
        student.setLastName(Last_Name)
        var Contact_Number = student.getContact()
        student.setContact(Contact_Number)

        println(" Student_ID:  " +getResult.getString("Student_ID"),
          " First_Name:  " +getResult.getString("First_Name"),
          " Last_Name:  " +getResult.getString("Last_Name"),
          " Contact_Number:  " +getResult.getString("Contact_Number"));
      }
    }
    catch {
      case e => e.printStackTrace
    }
  }

  def exit(){
    println("Exit thank you")
  }

  /*def quote(str:String): String={
    return "'"+str+"'";
  }*/


}





