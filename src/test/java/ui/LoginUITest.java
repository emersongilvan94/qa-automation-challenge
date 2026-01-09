package ui;

import pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginUITest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
    }

    @Test
    void deveExibirErroParaLoginInvalido() {
        loginPage.acessar();
        loginPage.realizarLogin("user", "senhaErrada");

        String erro = loginPage.obterMensagemErro();
        Assertions.assertEquals("Credenciais inválidas", erro);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
