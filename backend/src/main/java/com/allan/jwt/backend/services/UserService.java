package com.allan.jwt.backend.services;

import com.allan.jwt.backend.dto.CredentialsDTO;
import com.allan.jwt.backend.dto.SignUpDto;
import com.allan.jwt.backend.dto.UserDTO;
import com.allan.jwt.backend.entities.User;
import com.allan.jwt.backend.exceptions.AppException;
import com.allan.jwt.backend.mappers.UserMapper;
import com.allan.jwt.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDTO login(CredentialsDTO credentialsDTO){
        User user = userRepository.findByLogin(credentialsDTO.login())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.password()),user.getPassword())){
            return userMapper.toUserDTO(user);
        }
        throw new AppException("User information is not valid", HttpStatus.BAD_REQUEST);
    }

    public UserDTO register(SignUpDto signUpDto) {
        Optional<User> oUser = userRepository.findByLogin(signUpDto.login());

        if(oUser.isPresent()){
            throw new AppException("Login already exists",HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.signUpToUser(signUpDto);

        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        User savedUser = userRepository.save(user);
        return userMapper.toUserDTO(savedUser);
    }
}
