package se.iths.rest;

import se.iths.entity.Student;
import se.iths.error.ErrorMessage;
import se.iths.exceptions.BadRequestException;
import se.iths.exceptions.StudentNotFoundException;
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
            throw new BadRequestException(new ErrorMessage("400", "One of the required fields is empty", "/api/v1/students"));
        }
        studentService.createStudent(student);
        return Response.ok().status(Response.Status.CREATED).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        Optional<Student> foundStudent = studentService.getStudentById(id);

        if (foundStudent.isEmpty())
            throw new StudentNotFoundException(new ErrorMessage("404", "Student with id: " + id + " was not found", "/api/v1/students/" + id));
        return Response.ok(foundStudent).status(Response.Status.FOUND).build();
    }

    @Path("")
    @GET
    public Response getAllStudents() {
        List<Student> foundStudents = studentService.getAllStudents();

        if (foundStudents.isEmpty())
            throw new StudentNotFoundException(new ErrorMessage("404", "No students found", "123"));
        return Response.ok(foundStudents).build();
    }

    @Path("lastname")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getStudentByLastName(@QueryParam("lastname") String lastname) {
        List<Student> foundStudents = studentService.getStudentByLastName(lastname);

        if (foundStudents.isEmpty())
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessage("404", "Found no students with lastname: " + lastname, "/api/v1/students/lastname"))
                    .type(MediaType.TEXT_PLAIN_TYPE).build());

        return Response.ok(foundStudents).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        Optional<Student> foundStudent = studentService.getStudentById(id);

        if (foundStudent.isEmpty())
            throw new StudentNotFoundException(new ErrorMessage("404", "Student with id: " + id + " was not found", "/api/v1/students/" + id));

        studentService.deleteStudent(id);
        return Response.ok().build();
    }

    @Path("")
    @PUT
    public Response updateStudent(Student student) {
        studentService.updateStudent(student);
        return Response.ok(student).build();
    }
}
