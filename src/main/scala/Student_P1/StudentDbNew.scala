package Student_P1
import java.io.IOException
import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet, Statement}
import scala.io.StdIn.readLine

class StudentDbNew{
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

  def getFees(): Unit ={
    println("********************************Fees*********************************")
    var cmd= "select s.Student_ID,s.First_Name,s.Last_Name,s.Contact_Number, sum(c.feeDeposit) as feeTotal from feeStudent c join student s on c.Student_ID = s.Student_ID group by s.Student_ID order by s.Student_ID";
    dataConnect()
    var getResult:ResultSet=executeQuery(cmd);
    //println("Student_Id\t\t"+"FirstName\t\t\t\t"+"Last_Name\t\t\t\t"+"SContact_Number\t\t")
    println("Student_Id\t\t"+"First_Name\t\t\t\t"+"Last_Name\t\t\t\t"+"Contact_Number\t\t"+"Total_Fees\t\t")
    while (getResult.next()) {

      println( getResult.getString("s.Student_ID")+"\t\t\t\t\t"+
        getResult.getString("s.First_Name")+"\t\t\t\t"+
        getResult.getString("s.Last_Name")+"\t\t\t\t\t"+
        getResult.getString("s.Contact_Number")+"\t\t\t"+
       getResult.getString("feeTotal"))+"\t\t\t";
    }

  }

  def  getAll(){
    println("***********************Student Detail************************")
    //var cmd= "select s.Student_ID, sum(c.feeDeposit) as feeTotal from feeStudent c join student s on c.feeID = s.Student_ID group by s.Student_ID order by s.Student_ID";
   val cmd ="Select Student_ID,First_Name,Last_Name,Contact_Number from student ORDER BY Student_ID DESC";

    //change into foregin key

   // val cmd ="Select Student_ID,First_Name,Last_Name,Contact_Number from student ORDER BY student_ID $Student_ID";
    dataConnect()
    var getResult:ResultSet=executeQuery(cmd);
    //println("Student_Id\t\t"+"FirstName\t\t\t\t"+"Last_Name\t\t\t\t"+"Contact_Number\t\t")
    println("Student_Id\t\t"+"FirstName\t\t\t\t"+"Last_Name\t\t\t\t"+"Contact_Number\t\t"+"Total_Fees\t\t")
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

        //getResult.getString("Contact_Number")+"\t\t\t\t\t"+
        //getResult.getString("Total_Fees"))+"\t\t\t";


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

  def addStudentFees(): Unit= {
    println("***********************Insert Student************************")
    //println("Add Student")
    try {
      println("Enter studentID")
      var Student_ID = scala.io.StdIn.readInt()
      println("Enter Fee")
      var feeDeposit = scala.io.StdIn.readInt()
      //change query
      //var cmd= "select s.Student_ID, sum(c.feeDeposit) as feeTotal from feeStudent c join student s on c.feeID = s.Student_ID group by s.Student_ID order by s.Student_ID";
      var cmd= "INSERT INTO feeStudent(feeDeposit,Student_ID)VALUES(?,?)";
      println(cmd);
      var db = dataConnect()
      myStmt = db.prepareStatement(cmd);
      myStmt.setInt(1, feeDeposit)
      myStmt.setInt(2, Student_ID)
      //myStmt.executeUpdate(cmd)
      myStmt.execute()
       println("executed")
    }
    catch {
      case e => e.printStackTrace
    }
    getFees()
  }

}





