package com.example.skillback.common.security;

import com.example.skillback.common.domain.user.entity.User;
import com.example.skillback.common.domain.user.repository.UserRepository;
import com.example.skillback.common.domain.user.service.UserService;
import com.example.skillback.common.enums.RollEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userIdentifier) throws UsernameNotFoundException {
        User user = userRepository.findByUserIdentifier(userIdentifier)
            .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다"));

        return (UserDetails) user;
    }

    public RollEnum RollLoadUserByUsername(String userIdentifier) throws UsernameNotFoundException {
        User user = userRepository.findByUserIdentifier(userIdentifier)
            .orElseThrow(() -> new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다"));

        return user.getRollEnum();
    }
}
