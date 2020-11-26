package cl.transbank.restaurante.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    @ApiModelProperty(dataType = "string", value = "Username")
    private String username;
    @ApiModelProperty(dataType = "string", value = "JWT Token for Bearer use")
    private String token;
}
