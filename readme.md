### **README для проекта "Petsitter"**

#### **Цель тестирования**

Обеспечить стабильность и высокое качество функционала веб\-сайта Petsitter:

* Регистрация пользователей и администраторов.  
* Поиск услуг и исполнителей.  
* Бронирование услуг.  
* Управление профилями.

---

#### **Типы и методы тестирования**

**Типы:**

* Дымовое тестирование: Проверка основных функций после сборки.  
* Функциональное тестирование: Оценка ключевых возможностей.  
* Юзабилити-тестирование: Проверка удобства использования.  
* Регрессионное тестирование: Проверка исправленных багов.

**Методы:**

* Ручное тестирование: Для новых сценариев и юзабилити.  
* Автоматизированное тестирование: Для регрессии и критических путей.  
* Black-box тестирование: Анализ функциональности без знания кода.

---

#### **Тестовое окружение**

* **Браузеры:** Chrome, Firefox, Edge (актуальные версии).  
* **ОС:** Windows 10/11.  
* **Инструменты:** Selenium, Postman, Rest Assured, IntelliJ IDEA, MySQL, Jenkins.

---

#### **Установка и запуск тестов**

1. **Клонирование проекта:**
```
		git clone https://github.com/ZhS171177/Pet\_Sitter\_Cucumber\_Gradle\_Junit.git  
	
		cd Pet\_Sitter\_Cucumber\_Gradle\_Junit
```

**2\. Подключение к Postman:**

* Импортируйте коллекцию API из `/documentation`.  
* Проверьте эндпоинты вручную.

**3\. Настройка UI-тестов:**

* Установите WebDriver для выбранного браузера.  
* Проверьте настройки в `test.properties`.

**4\. Запуск автоматических тестов:**

**./gradlew test**

**5\. Нагрузочное тестирование:**

* Импортируйте JMeter-скрипты из `/performance_tests`.  
  * Проверьте сервер при 50, 100, 500 одновременных запросах.

---

#### **Примеры тестов**

1. **API: Позитивный тест на авторизацию**  
   * Описание: Регистрация пользователя, авторизация с использованием тех же данных.  
   * Ожидаемый результат: Ответ с кодом 200\.
```
     @Test

     public void loginPositiveCheckStatusTest() {

		Response response \= given()

			.contentType(ContentType. JSON)

			.body (requestDTO)

			.when()

			.post("/auth/login")

			.then()

			.assertThat()

			.statusCode(200).extract().response();

     System.out.println("Пользователь залогинился response: " \+ response.asString());

     Assert.assertNotNull(response.asString()); }
```
   

**2\. API: Добавление сервиса**

* Описание: Добавление нового сервиса через токен авторизации.  
* Ожидаемый результат: Код ответа 200, данные сервиса.

### `gradle regression`

### `gradle smoke`

```
  @Test

  public void addNewServicesPositiveTest() {

	responseToken \= getTokenAfterLogin(email, password);

	SoftAssert softAssert \= new SoftAssert();

	NewServiceDTO response \= given()

		.header(AUTH, "Bearer " \+ responseToken)

		.contentType(ContentType. JSON)

		.body (serviceNew)

		.when ()

		.post("/services")

		.then()

		.assertThat()

		.statusCode(200)

		.extract().response().as(New viceDTO.class);
```

![Example](Jenkins.jpg)

**3\. Проект включает также тесты с использованием фреймворка Cucumber:**

### `gradle cucumberRegressionChrome`
### `gradle cucumberSmokeChrome`

### `gradle cucumberRegressionFirefox`
### `gradle cucumberSmokeFirefox`

### `gradle cucumberRegressionEdge`
### `gradle cucumberSmokeEdge`

### Пример успешного прохождения тестов Cucumber:

![Example](Cucumber-Chrome.jpg)


**2.1. Пример теста для домашней страницы**

Этот тест проверяет действия пользователя на домашней странице с использованием Cucumber шагов.

### Описание:

* Given: Пользователь запускает браузер.  
* When: Пользователь открывает домашнюю страницу.  
* Then: Проверяется, что заголовок домашней страницы отображается.

```
	public class HomePageSteps {

	@Given("Пользователь запускает браузер")

		public void userLaunchesBrowser(){

		new HomePage(driver).init();

	}

	@And ("Пользователь закрывает браузер")

		public void userCloseBrowser() {

		new HomePage(driver).quitBrowser();

	}

	@When("Пользователь открывает домашнюю страницу petSitter")

		public void UserOpensHomePage() {

		new HomePage(driver).openHomePage();

	}

	@Then("Проверить, что заголовок домашней страницы отображается")

		public void verifyHomePageTitlePresent() {

		Assert.assertTrue(new HomePage(driver).isHomePageTitlePresent());
	}
```

#### **Структура проекта**

* `/backend` — Серверная часть.  
* `/frontend` — Пользовательский интерфейс.  
* `/test` — Автоматические тесты (API, UI, нагрузочные).  
* `/documentation` — Инструкции, чек-листы и Postman-коллекции.

---

#### **Критерии завершения тестирования**

* 100% покрытие критических сценариев.  
* Закрытие всех дефектов с высоким и средним приоритетом.  
* Полный успех функциональных и интерфейсных тестов.


---

#### **Заключение**

Проект ориентирован на обеспечение качества через комбинированный подход ручного и автоматизированного тестирования. Внедрение CI/CD и использование мощных инструментов позволяет поддерживать стабильность продукта.

* 
#### **Примеры описания багов в Jira**
1.
![Example](jira_2_1.jpg)
![Example](jira_2_2.jpg)
![Example](jira_2_3.jpg)

2.
![Example](Bug4_1.jpg)
![Example](Bug4_2.jpg)