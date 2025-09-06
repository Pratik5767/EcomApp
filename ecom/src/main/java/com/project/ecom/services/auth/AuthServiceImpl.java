package com.project.ecom.services.auth;

import com.project.ecom.dtos.SignupRequest;
import com.project.ecom.dtos.UserDto;
import com.project.ecom.entity.UserEntity;
import com.project.ecom.enums.UserRole;
import com.project.ecom.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        UserEntity user = convertToEntity(signupRequest);
        user = userRepository.save(user);
        return convertToDto(user);
    }

    @Override
    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

    @PostConstruct
    public void createAdminAccount() {
        UserEntity adminAccount = userRepository.findByRole(UserRole.ADMIN);
        if (adminAccount == null) {
            UserEntity user = UserEntity.builder()
                    .email("admin@test.com")
                    .name("admin")
                    .role(UserRole.ADMIN)
                    .password(passwordEncoder.encode("admin123"))
                    .build();
            userRepository.save(user);
        }
    }

    private UserEntity convertToEntity(SignupRequest signupRequest) {
         return UserEntity.builder()
                .email(signupRequest.getEmail())
                .name(signupRequest.getName())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .role(UserRole.CUSTOMER)
                .build();
    }

    private UserDto convertToDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .build();
    }
}
