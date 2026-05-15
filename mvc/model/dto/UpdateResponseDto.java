package mvc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateResponseDto {
    String name;
    String email;
    String password;
    String profile;

    public UpdateResponseDto(String name, String email, String password) {
    }
}
