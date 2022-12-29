# Console_CRUD_Application

Описание
Необходимо реализовать консольное CRUD приложение, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями:

* Developer (id, firstName, lastName, List<Skill> skills, Specialty specialty)
* Skill
* Specialty
* Status (enum ACTIVE, DELETED)
  
Developer -> List<Skill> skills + Specialty specialty

Каждая сущность имеет поле Status. 
В момент удаления, мы не удаляем запись из файла, а меняем её статус на DELETED.

В качестве хранилища данных необходимо использовать MySQL

Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.

# Требования:

Придерживаться шаблона MVC (пакеты model, repository, service, controller, view)

Для миграции БД использовать https://www.liquibase.org/

Сервисный слой приложения должен быть покрыт юнит тестами (junit + mockito).

Для импорта библиотек использовать Maven

# Технологии:
Java, MySQL, JDBC, Maven, Liquibase, JUnit, Mockito

# Инструкция по запуску:
  
1. Перейти в репозиторий по ссылке https://github.com/expo600/Console_CRUD_App_02

2. Кликнуть зеленую кнопку "Clone or Download" в правой верхней части страницы.

3. Скачать приложение  
  
4. Распаковать архиватором 

5. Открыть проект в intellij idea
  
6. Подключить свою базу данных 

7. Запустить класс main.java.com.ryzhov_andrey.crud.Application.java
