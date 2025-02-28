package ProductBackendTest.service.Impl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProductBackendTest.dto.UserDTO;
import ProductBackendTest.dto.UserRegistrationDTO;

import ProductBackendTest.entity.UserEntity;
import ProductBackendTest.mapper.UserMapper;
import ProductBackendTest.repository.UserRepository;
import ProductBackendTest.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    private UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public UserDTO registerUser(UserRegistrationDTO userRegistrationDTO) {
        try {
            UserEntity userEntity = userMapper.toEntity(userRegistrationDTO);
            UserEntity savedUser = userRepository.save(userEntity);
            return userMapper.toDTO(savedUser);
        } catch (DataIntegrityViolationException e) {
            logger.error("Error registering user: username already exists.", e);
            throw new IllegalArgumentException("The username is already in use.");
        }
    }
    
    @Override
    public Optional<UserDTO> login(String username, String password) {
        logger.info("Attempting login for user: {}", username);
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent() && userOptional.get().getPassword().equals(password)) {
            logger.info("Login successful.");
            return Optional.of(userMapper.toDTO(userOptional.get()));
        }
        logger.warn("Incorrect username or password.");
        return Optional.empty();
    }
    
}
