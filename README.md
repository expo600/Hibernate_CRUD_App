# Console_CRUD_Application

Описание
Необходимо реализовать консольное CRUD приложение, которое имеет следующие сущности:

* Developer (id, firstName, lastName, List<Skill> skills, Specialty specialty)
* Skill
* Specialty
* Status (enum ACTIVE, DELETED)
  
Developer -> List<Skill> skills + Specialty specialty

Каждая сущность имеет поле Status. 
В момент удаления, мы не удаляем запись из файла, а меняем её статус на DELETED.

В качестве хранилища данных необходимо использовать текстовые файлы:
developers.json, skills.json, specialties.json

Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.

# Слои:
- model - POJO клаcсы
- repository - классы, реализующие доступ к текстовым файлам
- controller - обработка запросов от пользователя
- view - все данные, необходимые для работы с консолью

# Инструкция по запуску:
  
1.Скачать приложение

2.Перейти в репозиторий по ссылке https://github.com/expo600/Console_CRUD_Application/tree/master

3.Кликнуть зеленую кнопку "Clone or Download" в правой верхней части страницы.

4.Распаковать архиватором

5.Открыть проект в intellij idea

6.Запустить класс main.java.com.ryzhov_andrey.crud.Application.java
