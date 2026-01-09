package api;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;

public class LoginApiTest {

    @BeforeAll
    static void setup() {
        baseURI = "https://site-exemplo"; // ajustar para o host real se existir
    }

    @Test
    void deveRetornar200QuandoLoginValido() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"user\":\"user\",\"password\":\"123\"}")
        .when()
            .post("/api/login")
        .then()
            .statusCode(200)
            .body("token", notNullValue())
            .body("perfil", equalTo("USER"));
    }

    @Test
    void deveRetornar401QuandoSenhaInvalida() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"user\":\"user\",\"password\":\"errada\"}")
        .when()
            .post("/api/login")
        .then()
            .statusCode(401);
    }

    @Test
    void deveRetornar403QuandoPerfilSemPermissao() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"user\":\"visitor\",\"password\":\"123\"}")
        .when()
            .post("/api/login")
        .then()
            .statusCode(403);
    }

    @Test
    void deveRetornar423QuandoUsuarioBloqueado() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"user\":\"bloqueado\",\"password\":\"123\"}")
        .when()
            .post("/api/login")
        .then()
            .statusCode(423);
    }
}
