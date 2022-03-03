package se.iths.rest;

import se.iths.entity.Subject;
import se.iths.error.ErrorMessage;
import se.iths.exceptions.EntityDataInvalidException;
import se.iths.exceptions.EntityNotFoundException;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

    @Inject
    SubjectService subjectService;

    @Path("")
    @POST
    public Response createSubject(Subject subject) {
        if (subject.getSubjectName().isEmpty())
            throw new EntityDataInvalidException(new ErrorMessage("401",
                    "Required data is missing",
                    "/api/v1/subjects"));

        subjectService.createSubject(subject);
        return Response.ok().status(Response.Status.CREATED).build();
    }

    @Path("{id}")
    @GET
    public Response getSubject(@PathParam("id") Long id) {
        Optional<Subject> foundSubject = subjectService.getSubjectById(id);

        if (foundSubject.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage("404",
                    "Subject with ID: "+id+" was not found!",
                    "/api/v1/subjects/"+id));

        return Response.ok(foundSubject).status(Response.Status.FOUND).build();
    }

    @Path("")
    @GET
    public Response getAllSubject() {
        List<Subject> foundSubjects = subjectService.getAllSubjects();

        if (foundSubjects.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage("404",
                    "No subjects found",
                    "/api/v1/students"));

        return Response.ok(foundSubjects).build();
    }

    @Path("subjectname")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getSubjectByName(@QueryParam("subjectname") String subjectName) {
        List<Subject> foundSubjects = subjectService.getSubjectByName(subjectName);

        if(foundSubjects.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage("404",
                    "No subjects with name: "+subjectName+" was found!",
                    "/api/v1/subjects/subjectname?subjectname="+subjectName));

        return Response.ok(foundSubjects).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id, Subject subject) {
        Optional<Subject> foundSubject = subjectService.getSubjectById(id);

        if(foundSubject.isEmpty())
            return Response.ok().status(Response.Status.NO_CONTENT).build();

        subjectService.deleteSubject(id);
        return Response.ok().build();
    }

    @Path("{id}")
    @PUT
    public Response updateSubject(@PathParam("id") Long id, Subject subject) {
        Optional<Subject> foundSubject = subjectService.getSubjectById(id);

        if (foundSubject.isEmpty())
            throw new EntityNotFoundException(new ErrorMessage("404",
                    "Subject with ID: "+id+" was not found!",
                    "/api/v1/subjects/"+id));

        subjectService.updateSubject(subject);
        return Response.ok(subject).build();
    }
}
