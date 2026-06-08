package br.com.progirls.api.portal.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/protected")
public class TestController {

    @GetMapping("/hello")
    public ResponseEntity<String> protectedEndpoint() {
        return ResponseEntity.ok("Acesso autorizado! Você está autenticado com JWT.");
    }
}
