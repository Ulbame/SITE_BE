package site.Ulbame.BE.common.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class AuthController {

    @PostMapping("/token")
    public ResponseEntity<Map<String, Object>> checkToken (@RequestBody Map<String, String> paramMap) {
        Map<String, Object> map = new HashMap<>();
        return ResponseEntity.ok(map);
    }
}
