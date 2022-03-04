package se.iths.rest;

import se.iths.entity.Teacher;
import se.iths.error.ErrorMessage;
import se.iths.exceptions.EntityDataInvalidException;
import se.iths.exceptions.EntityNotFoundException;
import se.iths.service.TeacherService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {

    @Inject
    TeacherService teacherService;

    @Path("")
    @POST
    public Response createTeacher(Teacher teacher) {
        if (teacher.getFirstName().isEmpty() || teacher.getLastName().isEmpty() || teacher.getEmail().isEmpty()) {
            throw new EntityDataInvalidException(new ErrorMessage("401",
                    "Required data is missing",
                    "/api/v1/teachers"));
        }

        teacherService.createTeacher(teacher);
        return Response.ok().status(Response.Status.CREATED).build();
    }

    @Path("{id}")
    @GET
    public Response getTeacher(@PathParam("id") Long id) {
        Optional<Teacher> foundTeacher = teacherService.getTeacherById(id);

        if(foundTeacher.isEmpty())
            throwEntityNotFoundException(id);

        return Response.ok(foundTeacher).build();
    }

    @Path("")
    @GET
    public Response getAllTeachers() {
        List<Teacher> foundTeachers = teacherService.getAllStudents();

        if (foundTeachers.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage("404",
                    "No teachers found",
                    "/api/v1/teachers"));

        return Response.ok(foundTeachers).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id) {
        Optional<Teacher> foundTeacher = teacherService.getTeacherById(id);

        if(foundTeacher.isEmpty())
            return Response.ok().status(Response.Status.NO_CONTENT).build();

        teacherService.deleteTeacher(id);
        return Response.ok().build();

    }

    @Path("{id}")
    @PUT
    public Response updateTeacher(@PathParam("id") Long id, Teacher teacher) {
        Optional<Teacher> foundTeacher = teacherService.getTeacherById(id);

        if(foundTeacher.isEmpty())
            throwEntityNotFoundException(id);

        teacherService.updateTeacher(teacher);
        return Response.ok(teacher).build();
    }

    @Path("lastname")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getTeacherByLastName(@QueryParam("lastname") String lastname) {
        List<Teacher> foundTeachers = teacherService.getTeacherByLastName(lastname);

        if (foundTeachers.isEmpty())
            throwEntityNotFoundException(lastname);

        return Response.ok(foundTeachers).build();
    }

    private void throwEntityNotFoundException(Long id) {
        throw new EntityNotFoundException((new ErrorMessage("404",
                "Teacher with ID: "+ id +" was not found!",
                "/api/v1/teachers/"+ id)));
    }

    private void throwEntityNotFoundException(String lastname) {
        throw new EntityNotFoundException(new ErrorMessage("404"
                , "No teachers with lastname: " + lastname + " was found!"
                , "/api/v1/teachers/lastname?lastname=" + lastname));
    }
}
