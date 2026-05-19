# Endpoints da API

**Projeto:** Portal de Conteúdos Educacionais  
**Versão:** v1  
**Base URL:** `/api/v1`  
**Responsável:** Natália S. Gomes | Líder Técnica  
**Escopo:** MVP (somente leitura - GET)

---

## Conteúdos

### GET `/api/v1/conteudos`

**Descrição:** Lista conteúdos com paginação (por padrão, retorna os 12 mais recentes).

**Query Params:**

* `page` (opcional) → número da página (default: 0)
* `size` (opcional) → quantidade por página (default: 12)
* `areaId` (opcional)
* `categoriaId` (opcional)
* `tecnologiaId` (opcional)
* `tagId` (opcional)
* `dataInicio` (opcional) → YYYY-MM-DD
* `dataFim` (opcional) → YYYY-MM-DD

**Status HTTP:**

* `200 OK` → sucesso
* `400 Bad Request` → parâmetros inválidos

**Exemplo de resposta:**

```json
{
  "content": [
    {
      "id": 1,
      "titulo": "Introdução ao Spring Boot",
      "descricao": "Aprenda os fundamentos do Spring Boot",
      "link": "https://...",
      "imagemUrl": "https://...",
      "dataPublicacao": "2026-04-01",
      "area": {
        "id": 1,
        "nome": "Backend"
      },
      "categoria": {
        "id": 1,
        "nome": "Artigo"
      },
      "tecnologias": [
        { "id": 1, "nome": "Java" }
      ],
      "tags": [
        { "id": 1, "nome": "iniciante" }
      ]
    }
  ],
  "totalElements": 100,
  "totalPages": 9,
  "currentPage": 0,
  "size": 12,
  "numberOfElements": 1
}
```

---

### GET `/api/v1/conteudos/busca`

**Descrição:** Busca conteúdos por texto (título e descrição), com filtros opcionais.

**Query Params:**

* `q` (opcional) → termo de busca (título e descrição)
* `areaId` (opcional)
* `categoriaId` (opcional)
* `tecnologiaId` (opcional)
* `tagId` (opcional)

**Status HTTP:**

* `200 OK`
* `400 Bad Request`

**Exemplo de resposta:**

```json
{
  "content": [],
  "totalElements": 0,
  "totalPages": 0,
  "currentPage": 0,
  "size": 12,
  "numberOfElements": 0
}
```

---

## Áreas

### GET `/api/v1/areas`

**Descrição:** Lista todas as áreas de conhecimento.

**Status HTTP:**

* `200 OK`

**Exemplo:**

```json
[
  { "id": 1, "nome": "Backend" },
  { "id": 2, "nome": "Frontend" }
]
```

---

## Categorias

### GET `/api/v1/categorias`

**Descrição:** Lista categorias de conteúdo.

**Status HTTP:**

* `200 OK`

**Exemplo:**

```json
[
  { "id": 1, "nome": "Artigo" },
  { "id": 2, "nome": "Curso" }
]
```

---

## Tecnologias

### GET `/api/v1/tecnologias`

**Descrição:** Lista tecnologias cadastradas.

**Status HTTP:**

* `200 OK`

**Exemplo:**

```json
[
  { "id": 1, "nome": "Java" },
  { "id": 2, "nome": "React" }
]
```

---

## Tags

### GET `/api/v1/tags`

**Descrição:** Lista tags disponíveis.

**Status HTTP:**

* `200 OK`

**Exemplo:**

```json
[
  { "id": 1, "nome": "iniciante" },
  { "id": 2, "nome": "avançado" }
]
```

---

## Roadmaps

### GET `/api/v1/roadmaps`

**Descrição:** Lista roadmaps com paginação.

**Query Params:**

* `page` (opcional)
* `size` (opcional)
* `nivel` (opcional) → **INICIANTE** | **INTERMEDIARIO** | **AVANCADO**

**Status HTTP:**

* `200 OK`
* `400 Bad Request`

**Exemplo:**

```json
{
  "content": [
    {
      "id": 1,
      "titulo": "Trilha Backend Java",
      "descricao": "Caminho para se tornar dev backend",
      "nivel": "INICIANTE"
    }
  ],
  "totalElements": 10,
  "totalPages": 1,
  "currentPage": 0,
  "size": 12,
  "numberOfElements": 1
}
```

---

### GET `/api/v1/roadmaps/{id}`

**Descrição:** Retorna detalhes de um roadmap específico.

**Path Params:**

* `id`

**Status HTTP:**

* `200 OK`
* `404 Not Found`

**Exemplo:**

```json
{
  "id": 1,
  "titulo": "Trilha Backend Java",
  "descricao": "Caminho para se tornar dev backend",
  "nivel": "INICIANTE",
  "conteudos": [
    {
      "ordem": 1,
      "conteudo": {
        "id": 1,
        "titulo": "Lógica de Programação"
      }
    }
  ]
}
```

---

## Referências

### GET `/api/v1/referencias`

**Descrição:** Lista referências externas com paginação.

**Status HTTP:**

* `200 OK`
* `400 Bad Request`

**Exemplo:**

```json
{
  "content": [
    {
      "id": 1,
      "titulo": "Documentação Spring",
      "descricao": "Documentação oficial",
      "link": "https://spring.io"
    }
  ],
  "totalElements": 10,
  "totalPages": 1,
  "currentPage": 0,
  "size": 12,
  "numberOfElements": 1
}
```

---

## Equipe

### GET `/api/v1/equipe`

**Descrição:** Lista membros da equipe.

**Status HTTP:**

* `200 OK`

**Exemplo:**

```json
[
  {
    "id": 1,
    "nome": "Natália Gomes",
    "cargo": "Backend Developer",
    "fotoUrl": "https://...",
    "linkedinUrl": "https://...",
    "githubUrl": "https://..."
  }
]
```

---

## Padrão de erro

**Exemplo (400 / 404):**

```json
{
  "timestamp": "2026-04-09T15:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Parâmetro 'size' inválido",
  "path": "/api/v1/conteudos"
}
```
