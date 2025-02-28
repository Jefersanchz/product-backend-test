package ProductBackendTest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ProductBackendTest.dto.UserDTO;
import ProductBackendTest.dto.UserRegistrationDTO;

import ProductBackendTest.entity.UserEntity;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(UserEntity userEntity);

    UserEntity toEntity(UserRegistrationDTO userRegistrationDTO);
}
