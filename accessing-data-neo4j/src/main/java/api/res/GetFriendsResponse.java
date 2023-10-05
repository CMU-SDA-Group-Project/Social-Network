package api.res;

import core.Friend;
import core.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class GetFriendsResponse extends BaseReponse{
    private List<Person> friends;
}
