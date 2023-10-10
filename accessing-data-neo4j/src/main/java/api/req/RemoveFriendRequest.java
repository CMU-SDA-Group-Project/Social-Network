package api.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveFriendRequest {
    @NotNull(message = "userId cannot be null")
    private String userName;
    @NotNull(message = "friendId cannot be null")
    private String friendName;
}
