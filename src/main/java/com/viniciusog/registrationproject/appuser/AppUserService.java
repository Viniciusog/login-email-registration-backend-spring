package com.viniciusog.registrationproject.appuser;

import com.viniciusog.registrationproject.registration.RegistrationService;
import com.viniciusog.registrationproject.registration.token.ConfirmationToken;
import com.viniciusog.registrationproject.registration.token.ConfirmationTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {
    //Aqui nessa classe de serviço que encounters os usuários que estão tentando logar no sistema

    private final static String USER_NOT_FOUND_MSG = "User with email %s not found!";
    private final AppUserRepository appUserRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final RegistrationService registrationService;

    private final BCryptPasswordEncoder cryptPasswordEncoder;

    //Nesse caso, o username seria o e-mail do usuário
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    //Cadastrar usuário
    public String signUpUser(AppUser appUser) {
        //Verificar se já tem usuário com determinado e-mail
        Optional<AppUser> user = appUserRepository.findByEmail(appUser.getEmail());

        if (user.isPresent()) {
            //TODO: Verificar se a pessoa não está disponível (ENABLED) envia e-mail de confirmação

            if (!user.get().isEnabled()) {
                //Criar token
                //Salvar token
                //Formar link com token
                //TODO: Enviar e-mail
                //Fazer com que o e-mail que é retornado nesse método seja o criado agora
            }
            //Se não, gere uma exceção
            throw new IllegalStateException("E-mail already registered!");
        }
        //Criptografa a senha
        String encodedPassword = cryptPasswordEncoder.encode(appUser.getPassword());
        //Substitui a senha do usuário pela mesma, porém criptografada
        appUser.setPassword(encodedPassword);

        AppUser appCreatedUser = appUserRepository.save(appUser);

        //Criando CONFIRMATION TOKEN E SALVANCO NO BANCO DE DADOS
        //Enquanto o nosso usuário não confirmar o token pelo e-mail, a conta não estará ENABLED
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(token);
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        confirmationToken.setAppUser(appCreatedUser);

        confirmationTokenService.save(confirmationToken);

        //ENVIAR EMAIL DIZENDO PARA CONFIRMAR O TOKEN
        return token;
    }

    //Atualiza o usuário para estar disponível
    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
