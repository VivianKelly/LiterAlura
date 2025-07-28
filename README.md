# LiterAlura 📚

<p align="center">
  
<p align="center">
  <img src="https://raw.githubusercontent.com/VivianKelly/LiterAlura/Main/Badge-Literalura.png" width="250" alt="Badge LiterAlura" />
</p>


Aplicação Java com Spring Boot desenvolvida como parte de um **desafio da Alura no programa Oracle Next Education (ONE)**. O projeto consome a API do [Gutendex](https://gutendex.com/) para registrar livros, autores e consultar informações literárias de forma prática via terminal.

## 🔧 Tecnologias utilizadas

- Java 17
- Spring Boot
- JPA / Hibernate
- PostgreSQL
- API Rest (Gutendex)
- Maven

## 📌 Funcionalidades

- Buscar livro pelo título
- Registrar livro e seus autores no banco de dados
- Listar livros registrados
- Listar autores registrados
- Listar autores vivos em um determinado ano
- Listar livros por idioma

## 💡 Como executar

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```

2. Configure o banco de dados PostgreSQL no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
```

3. Execute a aplicação:

- Pela sua IDE (IntelliJ, Eclipse etc) rodando a classe `LiterAluraApplication.java`
- Ou via terminal com:

```bash
./mvnw spring-boot:run
```

4. O menu será exibido no terminal com as opções interativas.

## 🗃️ Estrutura do Projeto

```
src
└── main
    └── java
        └── com.alura.literAlura
            ├── model        # Entidades JPA
            ├── repository   # Interfaces de acesso ao banco
            ├── services     # Camada de lógica e consumo de API
            └── principal    # Classe com a lógica do menu
```

## 📝 Observações

- A busca dos livros utiliza apenas o primeiro resultado retornado pela API.
- A aplicação lida com autores que não têm ano de nascimento ou falecimento informado.
- Caso os dados estejam inconsistentes, é possível limpar o banco com comandos SQL descritos na conversa anterior.

## ✨ Autor

Projeto desenvolvido por **Vivian Kelly** como parte do desafio da Alura no programa **Oracle Next Education (ONE)**. 🚀
