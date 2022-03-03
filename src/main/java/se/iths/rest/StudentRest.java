package se.iths.rest;

import se.iths.entity.Student;
import se.iths.error.ErrorMessage;
import se.iths.exceptions.EntityDataInvalidException;
import se.iths.exceptions.EntityNotFoundException;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.Optional;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    @Inject
    StudentService studentService;

    @Path("")
    @POST
    public Response createStudent(Student student) {
        if (student.getFirstName().isEmpty() || student.getLastName().isEmpty() || student.getEmail().isEmpty()) {
            throw new EntityDataInvalidException(new ErrorMessage("401",
                    "Required data is missing",
                    "/api/v1/students"));
        }

        studentService.createStudent(student);
        return Response.ok().status(Response.Status.CREATED).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        Optional<Student> foundStudent = studentService.getStudentById(id);

        if (foundStudent.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage("404", "Student with id: " + id + " was not found", "/api/v1/students/" + id));

        return Response.ok(foundStudent).status(Response.Status.FOUND).build();
    }

    @Path("")
    @GET
    public Response getAllStudents() {
        List<Student> foundStudents = studentService.getAllStudents();

        if (foundStudents.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage("404", "No students found", "/api/v1/students"));

        return Response.ok(foundStudents).build();
    }

    @Path("lastname")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getStudentByLastName(@QueryParam("lastname") String lastname) {
        List<Student> foundStudents = studentService.getStudentByLastName(lastname);

        if (foundStudents.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage("404"
                    , "No students with lastname: " + lastname + " was found!"
                    , "/api/v1/students/lastname?lastname=" + lastname));

        return Response.ok(foundStudents).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        Optional<Student> foundStudent = studentService.getStudentById(id);

        if (foundStudent.isEmpty())
            return Response.ok().status(Response.Status.NO_CONTENT).build();

        studentService.deleteStudent(id);
        return Response.ok().build();
    }

    @Path("{id}")
    @PUT
    public Response updateStudent(@PathParam("id") Long id, Student student) {
        Optional<Student> foundStudent = studentService.getStudentById(id);

        if (foundStudent.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage("404", "Student with id: " + id + " was not found", "/api/v1/students/" + id));

        studentService.updateStudent(student);
        return Response.ok(student).build();
    }
}
