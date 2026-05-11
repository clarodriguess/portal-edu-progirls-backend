<div align="center">
  <h1>
    Nome do Projeto
  </h1>
  
API backend desenvolvida com **[tecnologia principal]** para **[objetivo do sistema]**.

<p>
  <a href="#tecnologias">Tecnologias</a> •
  <a href="#estrutura">Estrutura</a> •
  <a href="#endpoints">Endpoints</a> •
  <a href="#como-executar">Como executar</a> •
  <a href="#variáveis-de-ambiente">Variáveis de ambiente</a> •
  <a href="#testes">Testes</a> •
  <a href="#arquitetura">Arquitetura</a> •
  <a href="#segurança">Segurança</a> •
  <a href="#banco-de-dados">Banco de Dados</a> •
  <a href="#licença">Licença</a> •
  <a href="#contribuidores">Contribuidores</a>
</p>
  
</div>

---

<!-- Explique o propósito da API -->

Este projeto foi desenvolvido para **[resolver problema X]**, permitindo **[resultado principal]**.

<!-- Contexto técnico -->

**Contexto:**

* API REST / Microsserviço / Monólito
* Integração com banco de dados
* Integração com outros serviços (se houver)

---

## Tecnologias

<!-- Liste apenas o que foi usado -->

✔ Exemplo:

* Java + Spring Boot
* Spring Security
* Hibernate / JPA

<!-- Evite listar coisas que não estão no projeto -->

---

## Estrutura

```bash
portal-edu-progirls-backend/
├── compose.yml                          # infraestrutura local (PostgreSQL etc.) via Docker Compose
├── docs/                                # documentação (DER, endpoints, etc.)
└── portal/                              # módulo Spring Boot (Gradle)
    ├── build.gradle
    ├── settings.gradle
    ├── gradlew
    └── src/
        ├── main/
        │   ├── java/
        │   │   └── br/com/progirls/api/portal/
        │   │       ├── PortalApplication.java  # inicialização da aplicação Spring Boot
        │   │       ├── controller/             # endpoints da API (camada web)
        │   │       ├── service/                # regras de negócio/orquestração
        │   │       ├── repository/             # acesso a dados (Spring Data)
        │   │       ├── model/
        │   │       │   ├── entity/             # entidades persistidas no banco de dados
        │   │       │   └── dto/                # DTOs de entrada/saída
        │   │       │       ├── ErrorResponse.java      # DTO padrão de erro
        │   │       │       ├── PageResponseDTO.java    # wrapper padrão de paginação
        │   │       │       ├── area/
        │   │       │       ├── categoria/
        │   │       │       ├── equipe/
        │   │       │       ├── referencia/
        │   │       │       └── tag/
        │   │       ├── mapper/                 # conversões entre entidades e DTOs
        │   │       └── exception/              # exceções + padronização de respostas de erro
        │   └── resources/
        │       ├── application.yml             # configurações padrão
        │       ├── application-dev.yml         # configurações de desenvolvimento
        │       └── application-test.yml        # configurações de testes
        └── test/
            └── java/
                └── br/com/progirls/api/portal/ # testes automatizados
```

---

## Endpoints

| Método | Endpoint                | Descrição                                                       |
|--------|-------------------------|-----------------------------------------------------------------|
| GET    | /api/v1/conteudos       | Lista conteúdos com paginação e filtros (área, categoria, etc). |
| GET    | /api/v1/conteudos/busca | Busca conteúdos por termo de texto (título/descrição).          |
| GET    | /api/v1/areas           | Lista todas as áreas de conhecimento.                           |
| GET    | /api/v1/categorias      | Lista as categorias de conteúdo.                                |
| GET    | /api/v1/tecnologias     | Lista as tecnologias cadastradas.                               |
| GET    | /api/v1/tags            | Lista as tags disponíveis.                                      |
| GET    | /api/v1/roadmaps        | Lista roadmaps com paginação e filtro por nível.                |
| GET    | /api/v1/roadmaps/{id}   | Retorna os detalhes e conteúdos de um roadmap específico.       |
| GET    | /api/v1/referencias     | Lista referências externas com paginação.                       |
| GET    | /api/v1/equipe          | Lista os membros da equipe do projeto.                          |
---

## Como executar

### Pré-requisitos

Antes de rodar o projeto, você precisa ter instalado:

* **Java 17**
* **Docker + Docker Compose**
* Uma IDE (IntelliJ IDEA, VS Code, etc)

---

### Variáveis de ambiente

Este projeto utiliza variáveis de ambiente para configuração.

Crie um arquivo `.env` na raiz do projeto:

```env
POSTGRES_DB=
POSTGRES_USER=
POSTGRES_PASSWORD=

SPRING_DATASOURCE_URL=
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
```

⚠️ **Importante:**

> O arquivo `.env` **não deve ser versionado** (já está no `.gitignore`)

> **Nunca subir dados sensíveis** como senha, tokens ou chaves

---

### Configurando na IDE (IntelliJ)

Se estiver usando o IntelliJ:

1. Vá em **More Actions > Edit**
2. Em **Environment Variables**
3. Aponte para o arquivo `.env`

Isso garante que o projeto consiga ler as variáveis corretamente.

---

### Subindo o banco de dados

```bash
docker-compose up -d
```

* O projeto utiliza **PostgreSQL (Alpine)** para ser mais leve
* Caso já tenha PostgreSQL instalado, pode usar sua instância local

---

### Rodando a aplicação

#### Opção 1 — Pela IDE (recomendado)

A forma mais simples:

* Clique em **Run** na sua IDE

---

#### Opção 2 — Via terminal (Gradle)

Se for rodar via terminal, pode ser necessário passar as variáveis:

```bash
./gradlew bootRun

# Build do projeto
./gradlew build

# Rodar testes
./gradlew test

# Limpar build
./gradlew clean
```

Caso dê erro com variáveis de ambiente, prefira rodar pela IDE.

---

### Acessando a API

Após subir a aplicação, acesse:

🟣 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## Boas práticas

* Não versionar `.env`
* Não expor credenciais no código
* Sempre validar se as variáveis de ambiente estão configuradas antes de rodar

---

## Testes

<!-- Incluir apenas se houver -->

```bash
./gradlew test
```

---

## Arquitetura

Descreva:

* Padrão utilizado (ex: camadas, hexagonal, clean architecture)
* Separação de responsabilidades
* Estratégias importantes

✔ Exemplos:

* Arquitetura em camadas (Controller → Service → Repository)
* Uso de DTO para isolamento de domínio
* Autenticação via JWT com Spring Security
* Tratamento global de exceções

---

## Segurança

<!-- 👉 Importante para backend -->

Descreva:

* Tipo de autenticação (JWT, OAuth, etc.)
* Como o token é validado
* Proteção de rotas

✔ Exemplo:

* Autenticação via JWT
* Rotas protegidas com Spring Security
* Token enviado no header Authorization

---

## Banco de Dados

<!-- 👉 Opcional, mas forte diferencial -->

Descreva:

* Qual banco foi utilizado
* Estratégia de migrations

✔ Exemplo:

* PostgreSQL
* Migrations controladas com Flyway

---

## Licença

<!-- TODO: linkar licença -->
Este projeto está sob a licença MIT.

---

## Contribuidores

- **Tech Leader:** [Natália](https://github.com/nataliatsi)
- **Backend:** [Nome](https://github.com/usuario)

<!-- Adicione:
- Função (Backend, Tech Lead, etc.)
- Nome
- Link do GitHub
-->
