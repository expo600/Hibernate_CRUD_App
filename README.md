# Hibernate_CRUD_App

Описание
Необходимо реализовать консольное CRUD приложение, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями:

* Developer (id, firstName, lastName, List<Skill> skills, Specialty specialty, Status status)
* Skill (id, name, Status status)
* Specialty (id, name, Status status)
* Status (enum ACTIVE, DELETED)
  
Каждая сущность имеет поле Status. 
В момент удаления, мы не удаляем запись из файла, а меняем её статус на DELETED.

# Требования:

Все CRUD операции для каждой из сущностей
  
Придерживаться подхода MVC
  
Для сборки проекта использовать Maven
  
Для взаимодействия с БД - Hibernate
  
Для конфигурирования Hibernate - аннотации
  
Инициализация БД должна быть реализована с помощью flyway
  
Сервисный слой приложения должен быть покрыт юнит тестами (junit + mockito)

# Технологии:
 Java, PostgreSQL, Hibernate, Maven, Flyway, JUnit, Mockito

# Инструкция по запуску:
  
1. Перейти в репозиторий по ссылке https://github.com/expo600/Hibernate_CRUD_App

2. Кликнуть зеленую кнопку "Clone or Download" в правой верхней части страницы.

3. Скачать приложение  
  
4. Распаковать архиватором 

5. Открыть проект в intellij idea
  
6. Подключить свою базу данных 

7. Запустить класс main.java.com.ryzhov_andrey.crud.Application.java
