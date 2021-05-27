### Travel city advisor

Web приложение для управления собственным туристическим телеграм ботом. Телеграм бот выдает пользователю справочную информацию о введенном городе. Например, пользователь вводит: «Москва», чат-бот отвечает: «Не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить)))

Используемые технологии: SpringBoot, SpringData, Hibernate, Java 1.8, TelegramBot API.
Сборка проекта - Maven. Тесты - Mockito. БД - MySQL.

Инструкция для запуска приложения: 
1. Создать БД и заполнить, выполнив sql-скрипт из файлов **сreate_table.sql и fill_table.sql** из каталога external.
2. Установить настройки созданной БД в application.properties, а именно указать url, username,
password.
3. После установки настроек БД можно запустить приложение на локальной машине через среду разработки 
или с помощью команды ./mvnw spring-boot:run из командной строки. Также необходимо учитывать, 
что приложение будет запущено через порт **8081** и при необходимости его можно изменить в application.properties
4. Найти бота @city_advisor_bot в телеграме и можно проверить его работоспособность указав название города,
если такого города нету в БД, будет выведено сообщение "Requested resource not found".
5. CRUD-опреации для городов реализованы через REST WebService. Для совершения данных операции необходимо запустить Postman 
и импортировать в него коллекцию с запросами CityAdvisor.postman_collection.json из каталога external.

Для просмотра списка доступных городов боту можно отправить команду **/cities**