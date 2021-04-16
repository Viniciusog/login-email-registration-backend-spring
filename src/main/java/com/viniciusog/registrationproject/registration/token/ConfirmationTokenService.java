package com.viniciusog.registrationproject.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;


    public void save(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    //PAREI AQUI, TEM QUE CONFIGURAR A PARTE DE ENVIAR E-MAIL COM O TOKEN AINDA E VER 1:22:10 NO VÍDEO
    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.
                updateConfirmedAt(token, LocalDateTime.now());
    }
}