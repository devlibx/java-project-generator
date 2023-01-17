#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.proto;

import ${package}.headers.pub.proto.pojo.SampleRequest;
import ${package}.headers.pub.proto.pojo.SampleResponse;
import lombok.extern.slf4j.Slf4j;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Slf4j
@Path("/api/v2/user")
@Consumes({"application/x-protobuf", "application/x-protobuf-text-format", "application/x-protobuf-json-format"})
@Produces({"application/x-protobuf", "application/x-protobuf-text-format", "application/x-protobuf-json-format"})
public class UserProtoResource {

    @POST
    public Response handleSampleRequest(
            @HeaderParam("Content-Type") @DefaultValue("application/x-protobuf") String contentType,
            SampleRequest request
    ) {
        SampleResponse response = SampleResponse.newBuilder()
                .setStatus("Ok")
                .setStringPassedInInput(request.getInput())
                .build();
        return Response.ok()
                .type(contentType)
                .entity(response)
                .build();
    }
}
