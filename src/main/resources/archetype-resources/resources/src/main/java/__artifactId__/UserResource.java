#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId};

import ${package}.core.service.IUserService;
import ${package}.external.service.jsonplaceholder.IJsonPlaceholderService;
import ${package}.headers.pub.AddUserRequest;
import ${package}.headers.pub.AddUserResponse;
import ${package}.headers.pub.GetUserRequest;
import ${package}.persistence.domain.User;
import ${package}.${artifactId}.lock.ResourceWithLocking;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.Map;

@Slf4j
@Path("/api/v1/user")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    private final IUserService userService;
    private final IJsonPlaceholderService jsonPlaceholderService;
    private final ResourceWithLocking resourceWithLocking;

    @Inject
    public UserResource(IUserService userService, IJsonPlaceholderService jsonPlaceholderService, ResourceWithLocking resourceWithLocking) {
        this.userService = userService;
        this.jsonPlaceholderService = jsonPlaceholderService;
        this.resourceWithLocking = resourceWithLocking;
    }

    @GET
    public Response getUser(GetUserRequest request) {
        User user = userService.getUser(request.getId());
        return Response.ok(user).build();
    }

    @POST
    public Response addUser(AddUserRequest request) {
        log.info("Using virtual Thread = " + Thread.currentThread().isVirtual());
        
        User user = User.builder().name(request.getName()).build();
        userService.persist(user);

        // Call json-placeholder API and get the data
        // It is added here to demo how to use external services
        Map result = jsonPlaceholderService.getPost(1);

        return Response.ok(
                AddUserResponse
                        .builder()
                        .id(user.getId())
                        .someDataFromExternalHttpService(result)
                        .build()
        ).build();
    }


    @POST
    @Path("/with_lock")
    public Response methodWhichShouldBeLocked(AddUserRequest request) {
        Map<String, Object> result = resourceWithLocking.methodWhichShouldBeLocked(request.getName());
        return Response.ok(request).build();
    }

}
