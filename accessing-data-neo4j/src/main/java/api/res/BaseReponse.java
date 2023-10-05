package api.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BaseReponse {
    private Boolean success;
    private String message;
}
