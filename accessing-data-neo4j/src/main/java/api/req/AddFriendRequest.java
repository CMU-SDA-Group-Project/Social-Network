package api.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class AddFriendRequest {

    @NotNull(message = "userId cannot be null")
    private Long userId;
    @NotNull(message = "friendId cannot be null")
    private Long friendId;
}
