package ProductBackendTest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class  UserRegistrationDTO
{
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @Builder
    public UserRegistrationDTO(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
}
