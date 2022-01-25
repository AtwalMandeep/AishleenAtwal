package Student_P1

 class Student() {
   var Student_ID: String=" "
  var First_Name: String=" "
  var Last_Name: String=" "
  var Contact_Number: String=" "

   def getId(): String = {
    return Student_ID
  }

   def setId(Student_Id: String) {
    this.Student_ID = Student_ID
  }

  def getName(): String ={
    return First_Name
  }
   def setName(First_Name: String) {
    this.First_Name = First_Name
  }

  def getLastName(): String ={
    return Last_Name
  }
   def setLastName(Last_Name: String) {
    this.Last_Name = Last_Name
  }
   def getContact(): String = {
    return Contact_Number
  }
   def setContact(Contact_Number: String){
    this.Contact_Number = Contact_Number
  }
 }


