WebServiceTestForNutonInvest(SpringBoot:2.5.0, Swagger2, elasticsearch:7.17.0, mongoDB)

Приложение настроено на локальный port:8088, elasticsearch port:9200, mongoDB port:27017 
и реализует следующие API: 

POST api/folder/add - сохраняет папку в базе данных(id генерируется автоматически, поэтому на сервер достаточно отправить только название)

{
"name":"folder"
}

GET api/folder/getAll - возвращает весь список папок из базы данных
POST api/request/add - сохраняет запрос в базу данных, при отправке json, заполняются только length, text, date

{
"length":1,
"text":"hello",
"modified date":"1"
}

POST api/request/getAll - возвращает весь список запросов из базы данных
POST api/request/getRequestByTag/{tagId} - получение запроса по id тега
POST api/request/getRequestByFolder/{folderId} - получение запроса по id папки
POST api/request/linkTag/{requestId}/{tagId} - связывает запрос и тег, чтобы в дальнешем можно было получить запросы по тегу(1 запрос связан максимум с 10 тегами)
POST api/request/linkFolder/{requestId}/{folderId} - связывает папку и реквест, чтобы в дальнейшем можно было получить запрос в папке(1 запрос связан максимум с 1 папкой)
POST api/request/getRequestByFieldText/{text} - поиск запроса по полю text при помощи технологии полнотекстового поиска elasticsearch

POST api/tags/add - сохраняет тег в базе данных(id генерируется автоматически, поэтому на сервер достаточно отправить только название)
GET api/tags/getAll - возвращает весь список тегов из базы данных

