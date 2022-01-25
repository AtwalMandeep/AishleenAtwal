package Student_P1

import java.sql.{Connection, DriverManager, ResultSet}

class Data_Base_Connection {

  val url = "jdbc:mysql://localhost:3306/studentinformation"
  val username = "root"
  val password = "matwal"

  // there's probably a better way to do this
  def execQuery(cmd :String):Unit={

      var connection: Connection = null

      try {
        // make the connection
        //Class.forName(driver)
        connection = DriverManager.getConnection(url, username, password)

        // create the statement, and run the select query
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery("Select Student_ID,First_Name,Last_Name,Contact_Number,Balance_Fees from student")

        while (resultSet.next()) {
          var First_Name = resultSet.getString("First_Name")
          val user = resultSet.getString("F")
          println("Print query result = " + First_Name)
        }
      } catch {
        case e => e.printStackTrace
      }
      connection.close()
  }
}
