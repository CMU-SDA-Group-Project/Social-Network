package api.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddCreditRequest {

    @NotNull(message = "name cannot be null")
    private String name;
    @NotNull(message = "credit cannot be null")
    private Long credit;
}
