# Bizi - API de Gerenciamento de Oportunidades de Trabalho

## 📋 Descrição
Bizi é uma API REST desenvolvida em Kotlin com Spring Boot que gerencia oportunidades de trabalho, conectando empresas e funcionários. O sistema oferece funcionalidades de autenticação, gerenciamento de perfis de empresas e funcionários, e comunicação em tempo real via WebSocket.

## 🚀 Funcionalidades Principais

### Autenticação e Segurança
- Sistema de registro e login de usuários
- Autenticação JWT (JSON Web Token)
- Proteção de rotas com Spring Security

### Empresas
- Cadastro e atualização de perfil empresarial
- Upload e gerenciamento de logos
- Publicação de oportunidades de trabalho

### Funcionários
- Cadastro e atualização de perfil profissional
- Sistema de disponibilidade
- Geolocalização integrada

### Oportunidades de Trabalho
- Criação e gerenciamento de vagas
- Sistema de categorização
- Localização geográfica das oportunidades

### Comunicação em Tempo Real
- Sistema de WebSocket para notificações
- Atualização em tempo real de novas oportunidades

## 🛠️ Tecnologias Utilizadas

- **Kotlin** - Linguagem principal
- **Spring Boot 3.4.1** - Framework base
- **Spring Security** - Segurança e autenticação
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados
- **WebSocket** - Comunicação em tempo real
- **JWT** - Autenticação baseada em tokens
- **Gradle** - Gerenciamento de dependências

## 📋 Pré-requisitos

- JDK 21
- PostgreSQL
- Gradle 8.11.1+

## 🔧 Configuração do Ambiente

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/bizi.git
```

2. Configure o banco de dados PostgreSQL no arquivo `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/bizidb
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

3. Execute o projeto:
```bash
./gradlew bootRun
```

## 📚 Endpoints Principais

### Autenticação
- `POST /api/auth/register` - Registro de novo usuário
- `POST /api/auth/login` - Login de usuário

### Empresas
- `POST /api/companies` - Criar perfil de empresa
- `GET /api/companies` - Obter perfil da empresa
- `POST /api/companies/upload-logo` - Upload de logo

### Funcionários
- `POST /api/employees` - Criar perfil de funcionário
- `GET /api/employees` - Obter perfil do funcionário

### Oportunidades
- `POST /api/jobs` - Criar nova oportunidade
- `GET /api/jobs/{companyId}` - Listar oportunidades por empresa

## 📦 Estrutura do Projeto

```
bizi/
├── src/main/kotlin/bizi/bizi/
│   ├── config/         # Configurações do Spring
│   ├── controller/     # Controladores REST
│   ├── dto/           # Objetos de Transferência de Dados
│   ├── model/         # Entidades
│   ├── repository/    # Repositórios JPA
│   ├── security/      # Configurações de Segurança
│   ├── service/       # Lógica de Negócios
│   └── websocket/     # Configuração WebSocket
```

## 🤝 Contribuindo

1. Faça o fork do projeto
2. Crie sua branch de feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença [MIT](LICENSE).

## 👥 Autores

* **André Azeredo** - [DevAzeredo](https://github.com/DevAzeredo)

---
⌨️ com ❤️ por [André Azeredo](https://github.com/DevAzeredo)
