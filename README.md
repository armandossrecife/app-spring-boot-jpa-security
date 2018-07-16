# app-spring-boot-jpa-security
Aplicação web CRUD usando Spring MVC com Spring Boot, JPA e Spring Security.

Exemplo de projeto Spring MVC usando o framework Spring Boot integrado com JPA, Spring Security, BootStrap e Thymeleaf. 

A aplicação foi criada usando o framework Spring MVC 4 [1] e Spring Boot 1 [2] aproveitando toda a facilidade de auto-configuração fornecida pelo Spring Boot, ou seja, não precisa fazer as configurações de aplicação. Para isso, o Spring Boot usa o conceito de convenção sobre configuração.  

Na camada de visão foi usado o Thymeleaf [3] e o Bootstrap [4] em conjunto com o Jquery [5].

Na camada de acesso a dados foi usado o padrão JPA [6] integrado com Hibernate.

Além disso, também foi usado o framework Spring Security [7] para fazer o controle de acesso e permissões da aplicação. 

A aplicação é estruturada no padrão Maven para aplicações web.

Atualmente o projeto tem as seguintes funcionalidades:
1. Controle de Sessão de usuário
2. Login de usuário
3. Logout de usuário
4. O Administrador pode gerenciar (inserir, listar, alterar e remover) uma lista de convidados. 
5. O Administrador Insere usuário da aplicação
6. O Administrador Lista usuários da aplicação
7. O Usuário pode gerenciar seu perfil
8. A aplicação faz a gestão (updload e visualização) de imagens dos usuários. 
9. Páginas html responsivas com o uso do Bootstrap.

Para rodar a aplicação:
1. Baixe o projeto via clone da URL https://github.com/armandossrecife/app-spring-boot-jpa-security.git
2. Configure o projeto como MAVEN [8].
3. Atualize as dependências via Maven Update Project.
4. Crie um banco mysql [9] com nome listavip.
5. Cadastre pelo menos um usuário na tabela de usuários com permissão "ROLE_ADMIN".
6. Atualize seu JDK para 1.8 

Referências:

[1] Spring MVC 4 - Framework Java para Aplicações Web MVC - https://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html

[2] Spring Boot 1 - É um Framework Java (baseado na Plataforma Spring) para Aplicações web que usam inversão de contêiner de controle para a plataforma Java. https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-security

[3] Thymeleaf - É um engine de modelo Java XML / XHTML / HTML5 que pode funcionar tanto em ambientes da Web (baseados em Servlet) quanto em ambientes não-web. É mais adequado para servir XHTML / HTML5 na camada de visualização de aplicativos da web baseados em MVC, mas pode processar qualquer arquivo XML mesmo em ambientes off-line. Ele fornece integração completa do Spring Framework. https://www.thymeleaf.org

[4] Bootstrap - Framework para Aplicações Web responsiva - https://v4-alpha.getbootstrap.com/getting-started/introduction

[5] JQuery - Biblioteca de Funções JavaScript - https://jquery.com/

[6] ORM JPA - Abstarçaõ de Acesso a Dados - https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

[7] Spring Security - É um framework Java que prover uma estrutura de controle de acesso para aplicações Java/Java EE que fornece autenticação, autorização e outros recursos de segurança para aplicativos corporativos. https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/

[8] Maven - Gestão de Builds e Dependências - https://maven.apache.org

[9] Mysql 5 - Sistema de Gerenciamento de Banco de Dados - https://dev.mysql.com/downloads/mysql/

Dúvidas, sugestões ou críticas entre em contato via e-mail armando@ufpi.edu.br
