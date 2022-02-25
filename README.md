# Student management system - Laboration 1

A simple JAX-RS application using a Payara server for student managing in a MySQL database.

   GETTING STARTED:
   -

   1. Download Payara Server (full)
   2. Unzip the Payara download to a folder where you have admin access
   3. Clone this project to your local computer:
        Open a terminal in the folder you want the project to end up
        Run git clone https://github.com/Philippevial/student-management-system.git
   4. Package the project using Maven:
        Open a terminal in the project folder
        Run ./mvnw clean package
   5. Start the application on Payara Server
        Open a terminal in the payara/bin directory where you'll find the asadmin file
        Run asadmin deploy <path to war file directory>/student-management-system.war

   How To Use
- 
- Download and use Insomnia  https://insomnia.rest/download.
- Add one of the endpoints listed below in the URL box and hit SEND.
 
ENDPOINTS 
-
Create a student
- 
**POST** http://localhost:8080/student-management-system/api/v1/students
   
- Enter the student credentials in JSON format as shown below:
Mandatory fields: **firstName, lastName, email.**
  

    {
     "firstName" : "Name",
     "lastName" : "Lastname",
     "email" : "MAIL@MAIL.com",
     "phoneNumber" : "1234567890"
     }

Get all students**
- 
**GET** http://localhost:8080/student-management-system/api/v1/students

- Returns a list with all students added
 

Get student by ID
   -
**GET** http://localhost:8080/student-management-system/api/v1/students/{id}
- Returns student with the ID given in the URL.


Get all students by lastname
-
**GET** http://localhost:8080/student-management-system/api/v1/students/lastname?lastname={lastname}
- Returns a list of students with lastname added in the URL
   

Update student
- 
**PUT** http://localhost:8080/student-management-system/api/v1/students/{id}
- Updates student with given ID in the URL **_AND_** the JSON-body.

    {
      "firstName" : "Name",
      "lastName" : "Lastname",
      "id"  : 1,
      "email" : "MAIL@MAIL.com",
      "phoneNumber" : "1234567890"
     }


Delete student
-  
http://localhost:8080/student-management-system/api/v1/students/{id}
- Deletes student with ID given in URL.
