/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.0.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package it.tramways.users.api;

import it.tramways.users.api.model.ChangePasswordRequest;
import java.util.List;
import it.tramways.users.api.model.LoginRequest;
import it.tramways.users.api.model.User;
import it.tramways.users.api.model.UserRequest;
import it.tramways.users.api.model.UserRole;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-02-07T17:36:39.887971+01:00[Europe/Rome]")
@Validated
@Api(value = "users", description = "the users API")
public interface UsersApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /users : Creates a new user
     *
     * @param userRequest  (optional)
     * @return Created (status code 201)
     */
    @ApiOperation(value = "Creates a new user", nickname = "createUser", notes = "", response = User.class, authorizations = {
        
        @Authorization(value = "bearerAuth")
         }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Created", response = User.class) })
    @PostMapping(
        value = "/users",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<User> createUser(@ApiParam(value = ""  )  @Valid @RequestBody(required = false) UserRequest userRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"roles\" : [ null, null ], \"uuid\" : \"uuid\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /users/{id} : Deletes a user
     *
     * @param id  (required)
     * @return OK (status code 200)
     */
    @ApiOperation(value = "Deletes a user", nickname = "deleteUser", notes = "", authorizations = {
        
        @Authorization(value = "bearerAuth")
         }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK") })
    @DeleteMapping(
        value = "/users/{id}"
    )
    default ResponseEntity<Void> deleteUser(@ApiParam(value = "",required=true) @PathVariable("id") String id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /users/{id}/password : Edit user&#39;s password
     *
     * @param id  (required)
     * @param changePasswordRequest  (optional)
     * @return OK (status code 200)
     */
    @ApiOperation(value = "Edit user's password", nickname = "editPassword", notes = "", authorizations = {
        
        @Authorization(value = "bearerAuth")
         }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK") })
    @PutMapping(
        value = "/users/{id}/password",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> editPassword(@ApiParam(value = "",required=true) @PathVariable("id") String id,@ApiParam(value = ""  )  @Valid @RequestBody(required = false) ChangePasswordRequest changePasswordRequest) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /users/{id}/roles : Edit user&#39;s roles
     *
     * @param id  (required)
     * @param userRole  (optional)
     * @return OK (status code 200)
     */
    @ApiOperation(value = "Edit user's roles", nickname = "editRoles", notes = "", authorizations = {
        
        @Authorization(value = "bearerAuth")
         }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK") })
    @PutMapping(
        value = "/users/{id}/roles",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> editRoles(@ApiParam(value = "",required=true) @PathVariable("id") String id,@ApiParam(value = ""  )  @Valid @RequestBody(required = false) List<UserRole> userRole) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /users/{id}/enable : Enable or disable a user
     *
     * @param id  (required)
     * @param body  (optional)
     * @return Ok (status code 200)
     */
    @ApiOperation(value = "Enable or disable a user", nickname = "enableUser", notes = "", authorizations = {
        
        @Authorization(value = "bearerAuth")
         }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Ok") })
    @PutMapping(
        value = "/users/{id}/enable",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> enableUser(@ApiParam(value = "",required=true) @PathVariable("id") String id,@ApiParam(value = ""  )  @Valid @RequestBody(required = false) Object body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /users/{id} : Gets a user
     *
     * @param id  (required)
     * @return User (status code 200)
     *         or User not found (status code 404)
     */
    @ApiOperation(value = "Gets a user", nickname = "getUser", notes = "", response = User.class, authorizations = {
        
        @Authorization(value = "bearerAuth")
         }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "User", response = User.class),
        @ApiResponse(code = 404, message = "User not found") })
    @GetMapping(
        value = "/users/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<User> getUser(@ApiParam(value = "",required=true) @PathVariable("id") String id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"roles\" : [ null, null ], \"uuid\" : \"uuid\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /users : Gets all users
     *
     * @return List of users (status code 200)
     */
    @ApiOperation(value = "Gets all users", nickname = "getUsers", notes = "", response = User.class, responseContainer = "List", authorizations = {
        
        @Authorization(value = "bearerAuth")
         }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of users", response = User.class, responseContainer = "List") })
    @GetMapping(
        value = "/users",
        produces = { "application/json" }
    )
    default ResponseEntity<List<User>> getUsers() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"roles\" : [ null, null ], \"uuid\" : \"uuid\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /users/logged : Gets logged user
     *
     * @return Ok (status code 200)
     */
    @ApiOperation(value = "Gets logged user", nickname = "logged", notes = "", response = User.class, authorizations = {
        
        @Authorization(value = "bearerAuth")
         }, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Ok", response = User.class) })
    @GetMapping(
        value = "/users/logged",
        produces = { "application/json" }
    )
    default ResponseEntity<User> logged() {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"roles\" : [ null, null ], \"uuid\" : \"uuid\", \"username\" : \"username\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /users/login : Logs a user in
     *
     * @param loginRequest  (optional)
     * @return Ok (status code 200)
     */
    @ApiOperation(value = "Logs a user in", nickname = "login", notes = "", response = it.tramways.commons.web.model.wrappers.StringWrapper.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Ok", response = it.tramways.commons.web.model.wrappers.StringWrapper.class) })
    @PostMapping(
        value = "/users/login",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<it.tramways.commons.web.model.wrappers.StringWrapper> login(@ApiParam(value = ""  )  @Valid @RequestBody(required = false) LoginRequest loginRequest) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "null";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /users/{id}/reset : Reset user&#39;s password
     *
     * @param id  (required)
     * @param body  (optional)
     * @return OK (status code 200)
     */
    @ApiOperation(value = "Reset user's password", nickname = "resetUser", notes = "", authorizations = {
        
        @Authorization(value = "bearerAuth")
         }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK") })
    @PutMapping(
        value = "/users/{id}/reset",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> resetUser(@ApiParam(value = "",required=true) @PathVariable("id") String id,@ApiParam(value = ""  )  @Valid @RequestBody(required = false) Object body) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}