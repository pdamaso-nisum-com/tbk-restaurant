package cl.transbank.restaurant.domain.login;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {
    @ApiModelProperty(dataType = "string", value = "Username")
    private String username;
    @ApiModelProperty(dataType = "string", value = "Password", example = "password")
    private String password;
}
