# Projeto de Automação de Testes

Este repositório contém a solução do desafio técnico para a vaga de Analista de Automação de Testes.

---

## Conteúdo

* Testes automatizados UI (Selenium)
* Testes automatizados API (RestAssured)
* Cenários de teste e análise em `cenarios-de-teste.md`
* Respostas SQL documentadas

---

## Tecnologias

* Java 17
* Maven
* Selenium WebDriver
* RestAssured
* JUnit 5
* PostgreSQL

---

## Estrutura

```
src/test/java
 ├── ui
 ├── api
 ├── pages
 ├── utils
 └── db
```

---

## Execução dos Testes

Executar todos os testes:

```bash
mvn clean test
```

Executar somente UI:

```bash
mvn clean test -Dtest=LoginUITest
```

---

## Configurações

As configurações estão em:

```
src/test/resources/test.properties
```

Podem ser sobrescritas por variáveis de ambiente.

---

## Relatórios

Os resultados são gerados no formato JUnit XML, compatível com pipelines CI/CD.

---

## Observações

Os testes seguem boas práticas de:

* Page Object Model
* Esperas explícitas
* Isolamento de dados
* Testes determinísticos

---

Projeto elaborado para avaliação técnica de QA Automação.
