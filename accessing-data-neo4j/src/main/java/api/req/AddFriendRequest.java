package api.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class AddFriendRequest {

    @NotNull(message = "userName cannot be null")
    private String userName;
    @NotNull(message = "friendName cannot be null")
    private String friendName;
}
