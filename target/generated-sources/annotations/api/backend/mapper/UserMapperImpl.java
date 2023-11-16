package api.backend.mapper;

import api.backend.dto.UserDTO;
import api.backend.dto.requestRecords.SignUpRequest;
import api.backend.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-16T14:56:06+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.firstName( user.getFirstName() );
        userDTO.lastName( user.getLastName() );
        userDTO.email( user.getEmail() );

        return userDTO.build();
    }

    @Override
    public User signUpToUser(SignUpRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setFirstName( request.firstName() );
        user.setLastName( request.lastName() );
        user.setEmail( request.email() );

        return user;
    }
}
