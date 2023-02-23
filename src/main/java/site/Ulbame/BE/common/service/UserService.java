package site.Ulbame.BE.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import site.Ulbame.BE.common.entity.UserEntity;
import site.Ulbame.BE.common.entity.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();

        UserEntity userEntity = userRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        if (userEntity.getUserId().equals(username)) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return new User(userEntity.getUserId(), userEntity.getUserPw(), authorities);
    }

    public UserEntity insertUser(Map<String, String> paramMap) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String userId = paramMap.get("user_id");
        String userPw = paramMap.get("user_pw");
        String userName = paramMap.get("user_name");
        String encPassword = passwordEncoder.encode(userPw);
        UserEntity userEntity = UserEntity.builder()
                .userId(userId)
                .userPw(encPassword)
                .userName(userName)
                .build();
        try {
            userRepository.save(userEntity);
        } catch ( IllegalArgumentException e) {
            System.out.println("필수 항목 누락");
        } catch( DataIntegrityViolationException e) {
            System.out.println("중복 오류");
        } catch (Exception e ) {
            System.out.println("기타 예외 발생");
            e.printStackTrace();
        } finally {
            return userEntity;
        }
    }
}