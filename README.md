# Projeto Loja de Carros

Bem-vindo ao repositório do projeto de Loja de Carros! Este é um projeto colaborativo de um grupo de amigos interessados em criar um banco de dados para uma loja de carros e desenvolver algumas interfaces gráficas usando JavaFX e Maven.

## Objetivos

O objetivo deste projeto é criar um sistema de banco de dados eficiente para uma loja de carros, onde poderemos armazenar informações sobre veículos, clientes, vendas, e muito mais. Além disso, implementamos interfaces gráficas amigáveis usando JavaFX para interagir com o banco de dados.

## Tecnologias Utilizadas

- Java
- JavaFX
- Banco de dados MySQL/MariaDB
- Maven

## Estrutura do Repositório

O repositório está organizado da seguinte maneira:

- `src/main/java/com/example/javafxproject/Controller`: Contém os arquivos fonte Java para a implementação das telas JavaFX e a lógica do sistema.
- `src/main/java/com/example/javafxproject/EstruturaBanco`: Contém scripts SQL para criação e manutenção do banco de dados.
- `src/main/resources/com/example/javafxproject`: Contém todos os scripts das telas em FXML

## Como executar

1. Fazer o clone do repositório.
```bash
git clone https://github.com/lojadecarro/javafx-project.git
```

<!-- Remover a pasta .git:
```bash
rm -rf .git
```

Iniciar um repositório git, fazer commits e enviar para o seu repositório remoto. -->

2. Após a clonagem, vá em `src/main/java/com/example/javafxproject/Conexao/Conexao.java`: é necessário comentar/descomentar os imports desnecessários/necessários para a sua máquina(vai depender se você está usando MariaDB ou MySQL). Modifique também a URL de acordo com seu servidor.

3. Vá em `src/main/java/com/example/javafxproject/Main.java`: Este método testa a conexão com o servidor do banco de dados. Repita o que foi feito no passo 2. Ao final do método principal, você encontrará comandos para criar as tabelas, inserir valores e removê-las. É necessário criar as tabelas e realizar os inserts para que funcione normalmente.

Nos laboratórios de informática do IFSP é necessário configurar o proxy para o funcionamento do maven:

```bash
export JAVA_TOOL_OPTIONS="-Djava.net.useSystemProxies=true"
```

No mesmo terminal que foi configurado o proxy executar o comando:
```bash
./mvnw javafx:run
```



## Contatos

Se você tiver alguma pergunta ou precisar de ajuda, não hesite em entrar em contato com os membros deste projeto:

- [Tro Armen](https://github.com/troarmen)
- [Rodrigo Mariano](https://github.com/Rodriomariano)
- [Letícia Brandão](https://github.com/lleleal)
- [Paulo Henrique](https://github.com/paulohernrique)

Esse é o sistema da nossa loja de carros, desenvolvido na matéria de LP1 no IFSP!


