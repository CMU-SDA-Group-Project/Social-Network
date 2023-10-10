package api.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {


    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "password cannot be null")
    private String password;

}
