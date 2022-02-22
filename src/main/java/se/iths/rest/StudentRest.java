package se.iths.rest;

import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
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
        studentService.createStudent(student);
        return Response.ok().status(Response.Status.CREATED).build();
    }

    @Path("{id}")
    @GET
    public Response getStudent(@PathParam("id") Long id) {
        Optional<Student> foundItem = studentService.getStudentById(id);

        var item = foundItem.orElseThrow(
                () -> new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                        .entity("Student with ID " + id + " was not found in database.").type(MediaType.TEXT_PLAIN_TYPE).build()));

        return Response.ok(item).status(Response.Status.FOUND).build();
    }

    @Path("")
    @GET
    public Response getAllStudents() {
        List<Student> foundStudents = studentService.getAllStudents();
        return Response.ok(foundStudents).status(Response.Status.FOUND).build();
    }

    @Path("lastname")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getStudentByLastName(@QueryParam("lastname") String lastname) {
        List<Student> students = studentService.getStudentByLastName(lastname);

        List<Student> foundStudents = new ArrayList<>();

        for (Student s : students)
            if (s.getLastName().equals(lastname))
                foundStudents.add(s);

        if (foundStudents.isEmpty())
            throw new NotFoundException("No student with lastname: " + lastname + " was found.");

        return Response.ok(foundStudents).status(Response.Status.FOUND).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
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
