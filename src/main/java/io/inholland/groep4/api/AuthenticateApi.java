/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.25).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.inholland.groep4.api;

import io.inholland.groep4.api.model.DTO.LoginResponseDTO;
import io.inholland.groep4.api.model.DTO.LoginDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-05-24T18:28:14.004Z[GMT]")
@Validated
public interface AuthenticateApi {

    @Operation(summary = "Authenticate", description = "Get a bearer token to authenticate with the API", tags={ "Auth", "Customers", "Employees" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "authentication successful", content = @Content(schema = @Schema(implementation = LoginResponseDTO.class))),
        
        @ApiResponse(responseCode = "400", description = "Bad input parameter(s)"),
        
        @ApiResponse(responseCode = "403", description = "You are forbidden to view this content") })
    @RequestMapping(value = "/authenticate",
        produces = { "application/json" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<LoginResponseDTO> authenticate(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody LoginDTO body);

}

