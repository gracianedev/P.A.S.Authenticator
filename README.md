# P.A.S. Authenticator One-Time Password Generator
Sistema web gerador de senhas de uso único (OTP - One-Time Password ) 

**P (Password)**  
**A (Authentication)**  
**S (Security)**  

---

## Status do Projeto
Em desenvolvimento

---

## Tecnologias Empregadas

### Backend
- Java (Spring Boot) para lógica de negócios e APIs REST.

### Banco de Dados
- MySQL para armazenar usuários, OTPs e logs.

### Frontend
- HTML/CSS e JavaScript para as telas de login, validação de OTP e painel.

### Controle de Versão
- Git para gerenciamento do código.

---

## Time de Desenvolvedores
- graciane.dev@gmail.com

---

## Objetivo do Software
Sistema web que gere senhas de uso único (OTP - One-Time Password) para autenticação ou verificação, oferecendo um método seguro para proteger transações ou acessos.

---

## Uso Pretendido

### Login Sem Senha (Passwordless Login)
1. O usuário insere apenas o e-mail ou o número de telefone para iniciar o login.
2. O sistema gera um OTP e o envia para o usuário.
3. O usuário insere o código recebido para acessar o sistema.

**Benefícios:**
- Evita o uso de senhas, que podem ser esquecidas ou roubadas.
- Conveniente e rápido para o usuário.
- Oferece uma camada adicional de proteção e modernidade ao processo de autenticação.

### Outros Possíveis Usos

#### Autenticação Multifator (MFA)
1. Após o usuário inserir sua senha, o sistema gera um OTP e o envia para o e-mail ou telefone do usuário.
2. O login só é concluído quando o usuário insere o código correto no sistema.

**Benefício:**  
Adiciona uma camada de segurança, mesmo que a senha do usuário seja comprometida.

#### Recuperação de Conta
1. Quando o usuário esquece a senha, o sistema pode enviar um OTP para verificar a identidade antes de permitir a redefinição da senha.

**Benefício:**  
Aumenta a segurança durante o processo de recuperação de conta.

#### Acesso Temporário
1. Gere um OTP para permitir acesso temporário ao sistema sem a necessidade de uma conta permanente.

**Benefício:**  
Útil em sistemas onde usuários não cadastrados precisam de acesso limitado.

---

## Vantagens de Usar OTP em Login
1. **Segurança Melhorada:** Mesmo que um invasor tenha acesso ao e-mail ou telefone, ele precisará do OTP para concluir o login.
2. **Conveniência:** Usuários não precisam memorizar senhas complexas.
3. **Aplicabilidade Flexível:** Pode ser usado tanto como uma camada adicional quanto como substituto de senhas.
4. **Adaptabilidade:** Pode ser implementado em várias situações, como login, redefinição de senha ou autenticação de transações.

---

## Requisitos do Sistema

### Funcionais
- O sistema deve apresentar uma interface simples e intuitiva, com campos para o usuário informar seu e-mail e um botão para enviar o código de acesso.
- Mensagens de feedback devem ser exibidas ao usuário, como:
  - "E-mail inválido";
  - "Código inválido ou expirado";
  - "Limite de tentativas de login atingido".

### Não Funcionais
- O sistema deve ser acessado via navegador como uma aplicação web.
- Deve haver integração com serviço de e-mail através de uma API confiável (ex.: JavaMail).
- Um código de acesso único deve ser gerado, armazenado no banco de dados, juntamente com o timestamp de criação.
- O código deve ser gerado utilizando um gerador de números aleatórios seguro (ex.: `java.security.SecureRandom`).
- O sistema deve implementar o padrão Time-Based One-Time Password (TOTP), garantindo que o código tenha um tempo de expiração predefinido.
- Deve verificar se o código informado está correto e dentro do prazo de validade para permitir o login.
- Após múltiplas tentativas de login falhas, o envio de novos códigos deve ser temporariamente bloqueado como medida de proteção contra ataques de força bruta.

