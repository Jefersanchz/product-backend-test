package ProductBackendTest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDTO {
    private String username;
    private String password;

    @Builder
    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
