package kasv.gunda.myexpense.models.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorResponseDto {
    private List<String> errors;
}
