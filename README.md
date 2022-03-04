# Student management system - Laboration 2

A simple JAX-RS application using a Payara server for student managing in a MySQL database.

Two new entities added with relevant endpoints add CRUD operations
 - Teacher
 - Subject.

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
Create
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

**POST** http://localhost:8080/student-management-system/api/v1/teachers
- Enter the teacher credentials in JSON format as shown below:
  Mandatory fields: **firstName, lastName, email.**


    {
     "firstName" : "Name",
     "lastName" : "Lastname",
     "email" : "MAIL@MAIL.com",
     "phoneNumber" : "1234567890"
     }

**POST** http://localhost:8080/student-management-system/api/v1/subjects
   
- Enter the subject credentials in JSON format as shown below:

- Mandatory field: **subjectName**
  

    {
     "subjectName" : "Name",
    }

Get all
- 
**GET** http://localhost:8080/student-management-system/api/v1/students
- Returns a list with all students added

**GET** http://localhost:8080/student-management-system/api/v1/teachers
- Returns a list with all teachers added

**GET** http://localhost:8080/student-management-system/api/v1/subjects

- Returns a list with all subjects added


Get by ID
   -
**GET** http://localhost:8080/student-management-system/api/v1/students/{id}
- Returns student with the ID given in the URL.

**GET** http://localhost:8080/student-management-system/api/v1/teachers/{id}
- Returns teacher with the ID given in the URL.

**GET** http://localhost:8080/student-management-system/api/v1/subjects/{id}
- Returns subject with complete information of students attending and teacher.

Get all by lastname
 - 

**GET** http://localhost:8080/student-management-system/api/v1/students/lastname?lastname={lastname}
- Returns a list of students with lastname added in the URL

**GET** http://localhost:8080/student-management-system/api/v1/teachers/lastname?lastname={lastname}
- Returns a list of teachers with lastname added in the URL

Get all subjects by name
- 

http://localhost:8080/student-management-system/api/v1/subjects/subjectname?subjectname={subjectname}
 - Returns a list of subjects with name added in the URL

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

**PUT** http://localhost:8080/student-management-system/api/v1/teachers/{id}
- Updates teacher with given ID in the URL **_AND_** the JSON-body.



    {
      "firstName" : "Name",
      "lastName" : "Lastname",
      "id"  : 1,
      "email" : "MAIL@MAIL.com",
      "phoneNumber" : "1234567890"
     }


**PUT** http://localhost:8080/student-management-system/api/v1/subjects/{id}
- Updates teacher with given ID in the URL **_AND_** the JSON-body.

- **Possible to add Students and/or teacher to a subject via this endpoint, use the format below:**


    {
	"id": 123,
    "subjectName": "Subject1",
	"student": [
		{
			"email": "Student@test.se",
			"firstName": "Student",
			"id": 123,
			"lastName": "Student",
			"phoneNumber": "123456789"
		},
		{
			"email": "Student2@test.se",
			"firstName": "Student2",
			"id": 124,
			"lastName": "Student2",
			"phoneNumber": "123456789"
		}
	],
	"teacher": {
		"email": "Teacher@test.se",
		"firstName": "Teacher",
		"id": 123,
		"lastName": "Teacher",
		"phoneNumber": "123456789"
	}
}

Delete student
-  
**DELETE** http://localhost:8080/student-management-system/api/v1/students/{id}
- Deletes student with ID given in URL.

**DELETE** http://localhost:8080/student-management-system/api/v1/teachers/{id}
- Deletes teacher with ID given in URL.

**DELETE** http://localhost:8080/student-management-system/api/v1/subjects/{id}
- Deletes subject with ID given in URL.


