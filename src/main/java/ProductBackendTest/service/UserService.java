package ProductBackendTest.service;



import java.util.Optional;

import ProductBackendTest.dto.UserDTO;
import ProductBackendTest.dto.UserRegistrationDTO;


public interface UserService {
    UserDTO registerUser(UserRegistrationDTO userRegistrationDTO);
    Optional<UserDTO> login(String username, String password);

}
