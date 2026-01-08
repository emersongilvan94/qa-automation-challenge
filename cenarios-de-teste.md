🧩 Prova Técnica – Analista de Automação de Testes (Java / Selenium / RestAssured / PostgreSQL)
Objetivo: Avaliar a capacidade analítica, técnica e prática em automação de testes e validação de cenários de banco de dados, incluindo fundamentos teóricos, raciocínio de QA, automação e SQL.

🧠 Parte 0 – Teoria e Conceitos Fundamentais (25 pts)
Instruções: Responda de forma objetiva e fundamentada.
1.Tipos de teste:
Explique a diferença entre teste de unidade, teste de integração e teste de ponta a ponta (E2E), e indique qual deles deve ser priorizado em um pipeline CI/CD.
Resposta: ___
2.Automação e ROI:
Cite três vantagens e três riscos da automação de testes em projetos corporativos.
Resposta: ___
3.Teste de integração em microserviços:
Quais são os principais desafios para automação de testes de integração em uma arquitetura de microserviços?
Cite dois exemplos de boas práticas para lidar com dependências externas.
Resposta: ___
4.Idempotência e consistência:
Como garantir a idempotência dos testes automatizados que interagem com um banco de dados PostgreSQL?
Resposta: ___
5.Mecanismos de simulação:
Diferencie Mock, Stub e Spy.
Cite uma biblioteca Java capaz de implementar cada conceito.
Resposta: ___
6.Boas práticas de automação:
Quais são os princípios básicos de um bom projeto de testes automatizados em Java (estrutura de pacotes, nomeação, isolamento e reuso de código)?
Resposta: ___
7.Gestão de dados de teste:
Como você controlaria e versionaria os dados de teste usados em ambientes compartilhados (dev/test/staging)?
Resposta: ___

🧩 Parte A – Análise e Planejamento de Testes (25 pts)
O candidato deve extrair cenários de teste funcionais e negativos a partir de um sistema de login com perfis (ADMIN, USER, VISITOR), considerando redirecionamentos, mensagens de erro e tempos de carregamento de até 5 segundos.
Tarefas: 1. Documentar pelo menos 8 cenários de teste (positivos, negativos e de segurança);
2. Classificar os tipos de teste;
3. Definir quais serão automatizados na camada UI, API ou manualmente;
4. Descrever a estrutura proposta para o projeto de automação.
Respostas / Análise:

Parte A – Análise e Planejamento de Testes
Sistema: Login com perfis (ADMIN, USER, VISITOR)
Contexto
O sistema possui autenticação por login, com controle de acesso baseado em perfis.
Após o login, o usuário pode ser redirecionado para áreas diferentes conforme seu perfil.
O tempo máximo aceitável de carregamento é de até 5 segundos.
Este documento descreve os cenários funcionais, negativos e de segurança, bem como a estratégia de automação.

Cenário 1 – Login válido com perfil USER
Objetivo: Garantir que um usuário USER consiga acessar o sistema com credenciais válidas.
Tipo de teste: Funcional / Positivo
Camada: UI + API
Automação: Sim
Passos: 
Acessar a tela de login
Informar usuário e senha válidos
Clicar em “Entrar”
Critérios de aceite:
API retorna HTTP 200
Token de autenticação é gerado
Usuário é redirecionado para /dashboard
Página carrega em até 5 segundos

Cenário 2 – Login com senha inválida
Objetivo: Validar tratamento para credenciais inválidas.
Tipo de teste: Funcional / Negativo
Camada: UI + API
Automação: Sim
Passos:
Informar usuário válido e senha incorreta
Tentar realizar login
Critérios de aceite:
API retorna HTTP 401
Mensagem de erro exibida na UI
Nenhuma sessão é criada

Cenário 3 – Login com usuário inexistente
Objetivo: Validar comportamento quando usuário não existe.
Tipo de teste: Funcional / Negativo
Camada: API
Automação: Sim
Critérios de aceite: API retorna HTTP 401
Nenhuma informação sensível é exposta

Cenário 4 – Login com perfil VISITOR sem permissão
Objetivo: Garantir que usuários VISITOR não acessem áreas restritas.
Tipo de teste: Segurança / Negativo
Camada: UI + API
Automação: Sim
Passos:
Realizar login com usuário VISITOR
Tentar acessar o dashboard
Critérios de aceite:
API retorna HTTP 403
UI exibe mensagem de acesso negado
Usuário não é redirecionado

Cenário 5 – Bloqueio após 3 tentativas inválidas
Objetivo: Validar bloqueio automático da conta.
Tipo de teste: Segurança
Camada: API + DB + UI
Automação: Sim
Passos:
Executar 3 tentativas de login com senha incorreta
Tentar login com senha correta
Critérios de aceite: API retorna HTTP 423
Usuário marcado como bloqueado no banco
UI exibe mensagem de conta bloqueada

Cenário 6 – Tempo de carregamento do dashboard
Objetivo: Garantir boa experiência do usuário.
Tipo de teste: Não funcional (Performance)
Camada: UI
Automação: Sim
Critérios de aceite: Dashboard carrega em até 5 segundos


Cenário 7 – Concorrência de logins
Objetivo: Validar comportamento com múltiplos acessos simultâneos.
Tipo de teste: Integração / Concorrência
Camada: API
Automação: Sim
Critérios de aceite: Todas as requisições retornam status esperado
Nenhum erro de integridade ocorre


Cenário 8 – Integridade da auditoria
Objetivo: Garantir que não existam registros órfãos na auditoria.
Tipo de teste: Integração / Dados
Camada: Banco de dados
Automação: Sim
Critérios de aceite: Nenhum registro de auditoria aponta para usuário inexistente



Estrutura proposta para o projeto de automação

src/test/java
 ├── ui
 │   ├── pages
 │   └── tests
 ├── api
 ├── utils
src/test/resources
 └── test.properties

Tecnologias:
Selenium WebDriver
RestAssured
JUnit 5
Maven

Boas práticas
Page Object Model
Esperas explícitas (WebDriverWait)
Testes independentes
Dados externos via properties
Separação entre UI e API

Estratégia de automação
Camada	Tipo de teste
UI	Fluxos principais e validações visuais
API	Validação de status, contratos e regras
Manual	Testes exploratórios e usabilidade

Observação sobre BDD
Embora o desafio não exija explicitamente a escrita em BDD, os cenários abaixo também foram representados em formato Gherkin como forma de demonstrar organização, clareza de comportamento e alinhamento com práticas de automação orientadas a negócio, facilitando futura integração com frameworks como Cucumber.

Cenários em BDD – Sistema de Login

Feature: Login no sistema com controle de perfis
  Como usuário do sistema
  Quero realizar login
  Para acessar funcionalidades conforme meu perfil


Cenário 1 - Login com credenciais válidas para usuário USER
Cenario: Login com credenciais válidas para usuário USER

Dado que estou na página de login
E possuo um usuário com perfil USER e credenciais válidas
Quando informo usuário e senha corretos
E clico em "Entrar"
Então devo ser redirecionado para o dashboard
E o dashboard deve carregar em até 5 segundos
E devo visualizar meu nome na tela


Cenário 2 – Login com senha inválida
Cenario: Login com senha incorreta

Dado que estou na página de login
E  possuo um usuário válido
Quando informo uma senha incorreta
E clico em "Entrar"
Então devo visualizar a mensagem "Credenciais inválidas"
E  o sistema não deve criar uma sessão


Cenário 3 – Login com usuário inexistente
Cenario: Login com usuário inexistente

Dado que estou na página de login
Quando informo um usuário inexistente
E informo qualquer senha
Entao devo receber uma mensagem de erro genérica
E nenhuma sessão deve ser criada


Cenário 4 – Login com perfil VISITOR sem permissão
Cenario: Usuário VISITOR tenta acessar área restrita

Dado que estou autenticado como usuário VISITOR
Quando tento acessar o dashboard
Então devo visualizar a mensagem "Acesso negado"
E o sistema não deve permitir o acesso


Cenário 5 – Bloqueio após 3 tentativas inválidas
Cenario: Bloqueio de conta após três tentativas inválidas

Dado que possuo um usuário ativo
Quando realizo três tentativas de login com senha incorreta
E tento realizar login com a senha correta
Então devo visualizar a mensagem "Usuário bloqueado"
E o sistema deve retornar status 423


Cenário 6 – Tempo de carregamento do dashboard
Cenario: Tempo máximo de carregamento do dashboard

Dado  que realizo login com sucesso
Qaundo  sou redirecionado para o dashboard
Então o dashboard deve carregar em até 5 segundos


Cenário 7 – Concorrência de logins
Cenario: Múltiplos logins simultâneos

Dado que múltiplos usuários realizam login ao mesmo tempo
Quando  as requisições são processadas
Então todas devem retornar resposta válida
E nenhuma inconsistência deve ocorrer nos dados



Cenário 8 – Integridade da auditoria
Cenario: Auditoria sem registros órfãos

Dado que existem registros de auditoria de login
Quando verifico os relacionamentos com usuários
Então não deve existir auditoria vinculada a usuário inexistente



💻 Parte B – Automação UI (Selenium) (20 pts)
Implementar cenários de: - Login válido (usuário USER);
- Login de perfil sem acesso (VISITOR);
- Bloqueio após 3 tentativas inválidas.
Usar Selenium, boas práticas (Page Object), esperas explícitas (WebDriverWait) e propriedades configuráveis.
Respostas / Código:

Objetivo
Automatizar os cenários:
1.Login válido (USER)
2.Login VISITOR sem acesso
3.Bloqueio após 3 tentativas inválidas

Usando:
Selenium WebDriver
Page Object Model
WebDriverWait
Configurações externas

Estrutura:
src/test/java
 └── ui
     ├── pages
     │    └── LoginPage.java
     └── tests
          └── LoginUITest.java

src/test/resources
 └── test.properties


test.properties:
base.url=https://sistema-exemplo.com/login
browser=chrome
timeout=10

LoginPage.java:
package ui.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By inputUser = By.id("username");
    private By inputPassword = By.id("password");
    private By btnLogin = By.id("loginBtn");
    private By message = By.id("message");

    public LoginPage(WebDriver driver, int timeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public void open(String url) {
        driver.get(url);
    }

    public void fillUser(String user) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputUser)).sendKeys(user);
    }

    public void fillPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }

    public void submit() {
        driver.findElement(btnLogin).click();
    }

    public String getMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(message)).getText();
    }

    public void waitDashboard() {
        wait.until(ExpectedConditions.urlContains("/dashboard"));
    }
}

LoginUITest.java:
package ui.tests;

import ui.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.InputStream;
import java.util.Properties;

public class LoginUITest {

    private WebDriver driver;
    private LoginPage loginPage;
    private Properties props;

    @BeforeEach
    void setup() throws Exception {
        props = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("test.properties");
        props.load(input);

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver, Integer.parseInt(props.getProperty("timeout")));
        loginPage.open(props.getProperty("base.url"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void loginValidoUsuarioUSER() {
        loginPage.fillUser("user_teste");
        loginPage.fillPassword("senha123");
        loginPage.submit();
        loginPage.waitDashboard();

        Assertions.assertTrue(driver.getCurrentUrl().contains("/dashboard"));
    }

    @Test
    void loginPerfilVisitorSemAcesso() {
        loginPage.fillUser("visitor_teste");
        loginPage.fillPassword("senha123");
        loginPage.submit();

        Assertions.assertEquals("Acesso negado", loginPage.getMessage());
    }

    @Test
    void bloqueioAposTresTentativasInvalidas() {
        for(int i = 0; i < 3; i++) {
            loginPage.fillUser("user_teste");
            loginPage.fillPassword("senha_errada");
            loginPage.submit();
        }

        loginPage.fillUser("user_teste");
        loginPage.fillPassword("senha123");
        loginPage.submit();

        Assertions.assertEquals("Usuário bloqueado", loginPage.getMessage());
    }
}



🌐 Parte C – Automação API (RestAssured) (15 pts)
Testar o endpoint /api/login, validando: - 200: Retorna token e perfil de usuário;
- 401: Credenciais inválidas;
- 403: Acesso negado;
- 423: Usuário bloqueado.
Usar RestAssured e, opcionalmente, Cucumber (BDD).
Respostas / Código:
Objetivo: testar /api/login para:
200 → sucesso
401 → credenciais inválidas
403 → acesso negado
423 → usuário bloqueado

Estrutura:
src/test/java
 └── api
     └── LoginAPITest.java

Dependência no pom.xml:
XM:

<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.4.0</version>
    <scope>test</scope>
</dependency>

LoginAPITest.java
Java:

package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LoginAPITest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://sistema-exemplo.com";
    }

    @Test
    void loginComSucesso200() {
        given()
            .contentType(ContentType.JSON)
            .body("""
                {
                  "username": "user_teste",
                  "password": "senha123"
                }
            """)
        .when()
            .post("/api/login")
        .then()
            .statusCode(200)
            .body("token", notNullValue())
            .body("perfil", equalTo("USER"));
    }

    @Test
    void loginInvalido401() {
        given()
            .contentType(ContentType.JSON)
            .body("""
                {
                  "username": "user_teste",
                  "password": "errada"
                }
            """)
        .when()
            .post("/api/login")
        .then()
            .statusCode(401);
    }

    @Test
    void loginSemPermissao403() {
        given()
            .contentType(ContentType.JSON)
            .body("""
                {
                  "username": "visitor_teste",
                  "password": "senha123"
                }
            """)
        .when()
            .post("/api/login")
        .then()
            .statusCode(403);
    }

    @Test
    void loginUsuarioBloqueado423() {
        given()
            .contentType(ContentType.JSON)
            .body("""
                {
                  "username": "user_bloqueado",
                  "password": "senha123"
                }
            """)
        .when()
            .post("/api/login")
        .then()
            .statusCode(423);
    }
}



🔄 Parte D – Integração e Esperas (10 pts)
Simular comportamento assíncrono de carregamento do dashboard (até 5 segundos), validando a URL e o conteúdo da página.
Respostas / Estratégia:

Para validar comportamento assíncrono do dashboard, utilizamos WebDriverWait aguardando:
- mudança de URL para /dashboard
- presença de elemento-chave (#welcome)
O tempo máximo configurado é 5 segundos, conforme requisito. Caso ultrapasse, o teste falha automaticamente, garantindo validação de performance funcional.
Essa abordagem evita uso de Thread.sleep e garante estabilidade.

🧮 Parte E – SQL e Banco de Dados (PostgreSQL) (30 pts)
Avaliar entendimento e interpretação de consultas SQL, correção de erros e elaboração de cenários de teste baseados em dados.
Exemplo 1 – Propósito e correção
SELECT u.username, COUNT(a.id) AS total_logins
FROM usuarios u
LEFT JOIN auditoria_login a ON a.usuario_id = u.id AND a.sucesso = true
WHERE u.perfil = 'ADMIN'
GROUP BY u.username
HAVING COUNT(a.id) > 5
ORDER BY total_logins DESC;
Perguntas: 1. Qual o propósito dessa consulta?
2. Há erros lógicos ou sintáticos?
3. Que tipo de cenário de teste você derivaria a partir dela?
Resposta: 
1. Propósito da consulta
Listar usuários ADMIN que tiveram mais de 5 logins bem-sucedidos, ordenados do maior para o menor número de logins.
2.Há erros lógicos ou sintáticos?
Sintaticamente correta.
Possível erro lógico: se o usuário ADMIN nunca logou com sucesso, ele será ignorado pelo HAVING COUNT(a.id) > 5.
Isso é esperado dependendo da regra de negócio.
3. Cenário de teste derivado
| Cenário                                                |
| ------------------------------------------------------ |
| Usuário ADMIN com 6 logins bem-sucedidos deve aparecer |
| Usuário ADMIN com 5 ou menos não deve aparecer         |
| Usuário USER com 10 logins não deve aparecer           |
| Usuário ADMIN sem logins não deve aparecer             |

Exemplo 2 – Identificação de erro lógico
SELECT * FROM usuarios WHERE bloqueado = 'false';
Perguntas: 1. O que há de errado nesta consulta no PostgreSQL?
2. Como corrigir?
3. Como um teste automatizado poderia detectar essa falha?
Resposta: 
1.  O que há de errado nesta consulta no PostgreSQL?
Erro. Em PostgreSQL, bloqueado é boolean → não deve ser comparado com string.
2. Como corrigir?  SELECT * FROM usuarios WHERE bloqueado = false;
3. Como um teste automatizado poderia detectar essa falha?
Um teste SQL que valide:
SELECT COUNT(*) FROM usuarios WHERE bloqueado = false;
Se a query errada for usada, retornará 0 mesmo havendo usuários desbloqueados.

Exemplo 3 – Verificação de bloqueio
SELECT u.username, u.tentativas, 
  CASE WHEN u.tentativas >= 3 THEN 'BLOQUEADO' ELSE 'ATIVO' END AS status
FROM usuarios u;
Perguntas: 1. Descreva um cenário que resulte em “BLOQUEADO”;
2. Como validar a consistência após o teste;
3. Como limpar a base após o teste?
Resposta:
1. Cenário que resulta em BLOQUEADO
Usuário realiza 3 ou mais tentativas de login inválidas consecutivas.

2. Como validar consistência
Após 3 falhas:
SELECT status FROM ... WHERE username = 'x';
Deve retornar BLOQUEADO.
Também validar que o sistema bloqueia login.


3. Como limpar a base
DELETE FROM usuarios WHERE test_id = 'uuid';


Exemplo 4 – Erro conceitual em junção
SELECT u.username, a.data_evento
FROM usuarios u, auditoria_login a
WHERE u.id = a.usuario_id(+);
Perguntas: 1. Qual o erro na sintaxe?
2. Como reescrever corretamente?
3. Que erro o PostgreSQL retornaria?
Resposta: 

1.Erro
(+) é sintaxe Oracle, não existe no PostgreSQL.
2.Forma correta:
SELECT u.username, a.data_evento
FROM usuarios u
LEFT JOIN auditoria_login a 
ON u.id = a.usuario_id;

3. Erro retornado
ERROR: syntax error at or near "+"

Exemplo 5 – Integridade e dados órfãos
SELECT a.id, a.usuario_id
FROM auditoria_login a
WHERE a.usuario_id NOT IN (SELECT id FROM usuarios);
Perguntas: 1. Qual o propósito da consulta?
2. Como ela contribui para testes de integração?
3. Que cenário de teste validaria isso?
4. Como evitar o problema no banco?
Resposta: 
1. Propósito
Detectar registros de auditoria sem usuário correspondente.

2. Como contribui para testes
Garante integridade referencial entre tabelas.

3. Cenário de teste
Excluir um usuário e verificar se sua auditoria foi removida.

4. Como evitar
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id)
ON DELETE CASCADE;

📊 Parte F – Critérios de Avaliação
Critério	Peso
Clareza e abrangência da análise	15
Estratégia de automação	10
Qualidade do código	20
Diagnóstico SQL	20
Cenários baseados em dados	10
Documentação	10
Reprodutibilidade	15

📦 Instruções de Entrega
O candidato deve entregar um repositório Git (público ou zipado) contendo: - Código-fonte dos testes automatizados;
- Arquivo cenarios-de-teste.md com as análises;
- Respostas SQL documentadas neste arquivo;
- Instruções de execução no README.md.