# 🛠 Lambda de Gerenciador de Oficina - Fase 3

[![Java](https://img.shields.io/badge/Java-17+-orange?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-green?logo=spring)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Apache_Maven-3.9.5-red?logo=apachemaven)](https://maven.apache.org/)
[![AWS SAM](https://img.shields.io/badge/AWS_SAM-1.87.0-yellow?logo=amazonaws)](https://aws.amazon.com/serverless/sam/)
[![JUnit 5](https://img.shields.io/badge/JUnit_5-5.9.3-blue?logo=junit5)](https://junit.org/junit5/)
[![JWT](https://img.shields.io/badge/JWT-JSON_Web_Token-purple?logo=jsonwebtokens)](https://jwt.io/)
[![Lambda](https://img.shields.io/badge/AWS_Lambda-v4-yellow?logo=amazonaws)](https://aws.amazon.com/lambda/)
[![Docker](https://img.shields.io/badge/Docker-24.0+-blue?logo=docker)](https://www.docker.com/)
[![AWS](https://img.shields.io/badge/AWS-EKS-orange?logo=amazon-aws)](https://aws.amazon.com/eks/)
[![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-Automation-black?logo=githubactions)](https://github.com/thomaserick/gerenciador-oficina-core-fase-2/actions/workflows/pipeline.yml)

Esta aplicação é uma **AWS Lambda (Function Serverless)** responsável por realizar a **autenticação de clientes via CPF
**, sem necessidade de usuário/senha.

A Lambda executa as seguintes responsabilidades:

- Validar o CPF informado;
- Consultar a API do **Core (Gerenciador de Oficina)** para verificar:
    - Existência do cliente;
    - Status do cliente;
- Gerar e retornar um **token JWT** válido para consumo das APIs protegidas do sistema.

Esta Lambda **complementa** o fluxo tradicional de autenticação por usuário/senha já existente no Core, sendo utilizada
exclusivamente para **consultas externas por CPF**.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **AWS Lambda**
- **AWS SAM (Serverless Application Model)**
- **Maven**
- **JWT (JSON Web Token)**
- **API Gateway (HTTP)**
- **JUnit 5** (testes)
- **Docker** (execução local via SAM)

---

## ▶️ Execução Local

### ✅ Pré-requisitos

Certifique-se de ter instalado:

```bash
java -version     # Java 17
mvn -version      # Maven
sam --version     # AWS SAM CLI
docker --version  # Docker
```

## 🔧 Variáveis de Ambiente

Para executar a Lambda localmente, é necessário configurar as seguintes variáveis de ambiente no arquivo `env.json`:

- `CORE_API_URL`: URL base da API do Core (Gerenciador de Oficina).
- `JWT_SECRET`: Chave secreta para assinatura dos tokens JWT.
- `INTERNAL_API_KEY`: Chave de API para autenticação interna com o Core.

### 🚀 Executando a Lambda Localmente

Para executar a Lambda localmente usando o AWS SAM, utilize o seguinte comando:

Precisaamos subir a aplicação Gerenciador de Oficina Core localmente (via Docker ou IDE) para que a Lambda consiga se
comunicar com ela,
para validação do CPF e geração do token JWT.

```bash
 sam build
 sam local invoke AuthCpfFunction --event event.json --env-vars env.json --add-host host.docker.internal:

```bash
 mvn clean package -DskipTests
 sam build --debug
 sam local invoke AuthCpfFunction --event event.json --env-vars env.json --add-host host.docker.internal:172.17.0.1 --debug
```