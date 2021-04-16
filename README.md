## Requisitos
- Java 11 ou superior
- MySQL Database
- Maven
- MailDev
- NodeJS
- IntelliJ ou outra IDE
- Postman

## Como executar?

 1 - Instale e rode o Mail Dev através dos comandos: <br>
 ```npm install -g maildev``` <br>
 ```maildev``` <br>

 2 - Crie um banco de dados no MySQL, chamado ```dbregistration``` <br>
 3 - Abra o projeto na sua IDE e execute o arquivo ```RegistrationprojectApplication``` <br>
 4 - Se tudo der certo, o programa irá iniciar em: ```http://localhost:8080/api/v1/registration/``` <br>
 5 - Abra o postman, coloque ```http://localhost:8080/api/v1/registration/```, selecione POST e coloque o json a seguir:
 ```json
 {
  "firstname": "João",
  "lastname": "Silva",
  "email": "joaosilva@gmail.com",
  "password": "senhaexemplo"
}
 ```
6 - Ao executar o passo anterior, abra: ```http://localhost:1080/``` no seu navegador <br>
7 - Observe que um e-mail será enviado para você <br>
8 - Entre no e-mail enviado e clique no token de confirmação. Pronto! Sua conta está confirmada no sistema. <br>
9 - Agora basta logar em ```http://localhost:8080/api/v1/registration/```, no seu navegador, utilizando o e-mail e a senha colocados no json anterior