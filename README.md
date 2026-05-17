# 🏦 FinBank API

> API REST de sistema bancário digital desenvolvida com Java e Spring Boot, inspirada em fintechs.

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen?style=flat-square&logo=springboot)
![H2](https://img.shields.io/badge/H2-Database-blue?style=flat-square)
![Docker](https://img.shields.io/badge/Docker-ready-2496ED?style=flat-square&logo=docker)
![License](https://img.shields.io/badge/license-MIT-green?style=flat-square)

---

## 📋 Sobre o Projeto

O **FinBank** é uma API REST que simula as operações essenciais de um banco digital. O projeto foi desenvolvido com foco em boas práticas de arquitetura, segurança e organização de código — características valorizadas no mercado de fintechs.

---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 4.0.6**
- **Spring Security** — criptografia de senha com BCrypt
- **Spring Data JPA / Hibernate** — persistência de dados
- **H2 Database** — banco de dados em memória para desenvolvimento
- **Lombok** — redução de boilerplate
- **Docker + Docker Compose** — containerização da aplicação
- **Maven** — gerenciamento de dependências

---

## 🗂️ Arquitetura

O projeto segue uma arquitetura em camadas, padrão do mercado:

```
src/main/java/com/gabrielloterio/FinBank/
├── controller/       # Endpoints REST
├── service/          # Regras de negócio
├── repository/       # Acesso ao banco de dados
├── domain/
│   ├── model/        # Entidades JPA (Cliente, Conta, Transacao)
│   └── dto/          # Objetos de transferência de dados
├── security/         # Configuração do Spring Security
└── exception/        # Tratamento de erros
```

---

## ⚙️ Funcionalidades

- ✅ Cadastro de clientes com senha criptografada (BCrypt)
- ✅ Criação automática de conta bancária ao cadastrar cliente
- ✅ Consulta de saldo
- ✅ Depósito
- ✅ Saque com validação de saldo
- ✅ Transferência entre contas
- ✅ Extrato de transações

---

## 📌 Endpoints

### Clientes
| Método | Rota | Descrição |
|--------|------|-----------|
| `POST` | `/api/clientes/cadastrar` | Cadastra um novo cliente |

### Contas
| Método | Rota | Descrição |
|--------|------|-----------|
| `GET` | `/api/contas/{id}/saldo` | Consulta saldo da conta |
| `POST` | `/api/contas/{id}/depositar` | Realiza depósito |
| `POST` | `/api/contas/{id}/sacar` | Realiza saque |
| `POST` | `/api/contas/transferir` | Transferência entre contas |
| `GET` | `/api/contas/{id}/extrato` | Consulta extrato |

---

## ▶️ Como Rodar Localmente

### Pré-requisitos
- Java 21+
- Maven
- Docker (opcional)

### Sem Docker

```bash
# Clone o repositório
git clone https://github.com/GaLoterio/FinBank.git
cd FinBank

# Execute o projeto
./mvnw spring-boot:run
```

### Com Docker

```bash
# Clone o repositório
git clone https://github.com/GaLoterio/FinBank.git
cd FinBank

# Suba o container
docker compose up --build
```

A API estará disponível em: **http://localhost:8080**

O console do banco H2 estará em: **http://localhost:8080/h2-console**
- JDBC URL: `jdbc:h2:mem:finbank`
- User: `sa`
- Password: *(vazio)*

---

## 🧪 Exemplos de Uso

### Cadastrar cliente
```bash
curl -X POST http://localhost:8080/api/clientes/cadastrar \
  -H "Content-Type: application/json" \
  -d '{"nome": "Gabriel Loterio", "email": "gabriel@finbank.com", "senha": "123456"}'
```

### Depositar
```bash
curl -X POST http://localhost:8080/api/contas/1/depositar \
  -H "Content-Type: application/json" \
  -d '{"valor": 1000.00}'
```

### Transferir
```bash
curl -X POST http://localhost:8080/api/contas/transferir \
  -H "Content-Type: application/json" \
  -d '{"contaOrigemId": 1, "contaDestinoId": 2, "valor": 250.00}'
```

### Consultar extrato
```bash
curl http://localhost:8080/api/contas/1/extrato
```

---

## 🔮 Próximas Melhorias

- [ ] Autenticação JWT
- [ ] Documentação com Swagger/OpenAPI
- [ ] Testes unitários com JUnit 5 + Mockito
- [ ] Migração para PostgreSQL em produção

---

## 👨‍💻 Autor

**Gabriel Loterio**

[![GitHub](https://img.shields.io/badge/GitHub-GaLoterio-181717?style=flat-square&logo=github)](https://github.com/GaLoterio)

---

## 📄 Licença

Este projeto está sob a licença MIT.
