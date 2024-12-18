package com.rossim.eletron.Controller;

import com.rossim.eletron.DTO.AuthDTO;
import com.rossim.eletron.DTO.LoginResponseDTO;
import com.rossim.eletron.DTO.RegisterDTO;
import com.rossim.eletron.Config.Security.TokenService;
import com.rossim.eletron.Model.Usuario;
import com.rossim.eletron.Repository.UsuarioRepository;
import com.rossim.eletron.Service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/validate-token")
    @Operation(description = "Realiza a validação do token e restaura a sessão do usuário")
    public ResponseEntity<Void> validateToken() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((Usuario) auth.getPrincipal());

            UserDetails usuario = authService.loadUserByUsername(data.login());

            return ResponseEntity.ok(new LoginResponseDTO(usuario, token));
        }
        catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Credenciais incorretas");
        }
        catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO data) {
        if (this.usuarioRepository.findByLogin(data.login()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login inválido/existente");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario newUser = new Usuario(data.login(), encryptedPassword, data.nome(), data.role());

        this.usuarioRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}