package site.Ulbame.BE.common.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import site.Ulbame.BE.common.entity.UserEntity;
import site.Ulbame.BE.common.service.UserService;
import site.Ulbame.BE.common.util.JwtUtil;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> paramMap) {
        String userId = paramMap.get("user_id");
        String userPw = paramMap.get("user_pw");

        UserDetails loginUser = userService.loadUserByUsername(userId); //userId로 정보 가져오기

        Authentication authentication = authenticationManager.authenticate(     //가져온 정보와 입력한 비밀번호로 검증
                new UsernamePasswordAuthenticationToken(loginUser, userPw)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);   // 검증 통과 후 authentication 세팅

        String accessToken = jwtUtil.createToken(loginUser.getUsername(), loginUser.getUsername());     //accessToken 생성

        Map<String, Object> result = new HashMap<>();
        result.put("user_id", loginUser.getUsername());
        result.put("user_token", accessToken);
        result.put("user_role", loginUser.getAuthorities().stream().findFirst().get().getAuthority());

        return ResponseEntity.ok(result);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signIn(@RequestBody Map<String, String> paramMap) {
        UserEntity user=null;
        if(paramMap.containsValue(null) || paramMap.containsValue(""))
            return new ResponseEntity<UserEntity>(user, HttpStatus.BAD_REQUEST);
        user = userService.insertUser(paramMap);
        HttpHeaders headers= new HttpHeaders();
        if(user.getIdx() == null) {
            return new ResponseEntity<UserEntity>(user, headers, HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<UserEntity>(user, headers, HttpStatus.CREATED);
        }

    }
}