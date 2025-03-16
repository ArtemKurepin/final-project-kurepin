# Avito Project: Automation Framework

Автоматизированный тестовый фреймворк для комбинированного тестирования (API + UI) с использованием современных инструментов и паттернов.

## 🛠 Технологии
- **Язык**: Java 21
- **Тестирование API**: REST Assured, Gson
- **Тестирование UI**: Selenium WebDriver
- **Тест-раннер**: JUnit 5
- **Логирование**: SLF4J + Logback
- **Паттерны**: Page Factory, Singleton
- **Сборка**: Maven
- **Отчеты**: Allure Framework

## 📦 Используемые библиотеки

### Тестирование API
- **REST Assured** `5.5.1` - Фреймворк для тестирования REST API
- **Gson** `2.12.1` - Сериализация/десериализация JSON
- **JSON Java** `20210307` - Работа с JSON-объектами
- **Jackson Databind** `2.18.3` - Парсинг сложных JSON-структур

### Тестирование UI
- **Selenium Java** `4.29.0` - Автоматизация браузеров
- **WebDriverManager** `5.5.3` - Автоматическое управление драйверами браузеров

### Тест-фреймворки
- **JUnit Jupiter API** `5.11.4` - Основной фреймворк для модульного тестирования
- **JUnit Platform Engine** `1.11.0` - Запуск тестов на платформе JUnit 5
- **AssertJ** `4.0.0-M1` - Продвинутые assertions для тестов

### Логирование
- **SLF4J API** `2.0.7` - Интерфейс для логирования
- **Logback Classic** `1.4.11` - Реализация системы логирования

### Отчетность
- **Allure JUnit5** `2.25.0` - Интеграция Allure с JUnit 5
- **Allure Maven Plugin** `2.12.0` - Генерация Allure-отчетов

---
## 📂 Структура проекта
```text
📦Ru_Avito
┣━━ 📂src/test/java
┃ ┣━━ 📂PageObjectsUI
┃ ┃ ┣━━ 📜LoginPage.java 
┃ ┃ ┣━━ 📜MainPage.java 
┃ ┃ ┣━━ 📜Utils.java
┃ ┃ ┣━━ 📜Singleton.java 
┃ ┃ ┣━━ 📜ApiAssertions.java 
┃ ┃ ┣━━ 📜AvitoJsonUtils.java 
┃ ┃ ┣━━ 📜AvitoSoftlyAssert.java
┃ ┃ ┗━━ 📜LogSaver.java 
┃ ┣━━ 📂AvitoAPITest/ApiTests
┃ ┃ ┣━━ 📜TestGetRequestAPI.java
┃ ┃ ┗━━ 📜TestPostRequestAPI.java
┃ ┗━━ 📂AvitoUITest
┃ ┣━━ 📜TestLoginPage.java 
┃ ┗━━ 📜TestMainPage.java 
┗━━ 📂resources
```
📊 Генерация отчетов
После запуска тестов сгенерируйте Allure-отчет.
📧 Контакты
Автор: Artem Kurepin

Email: your.email@example.com

GitHub: ArtemKurepin
