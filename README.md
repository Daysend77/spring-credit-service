### Сервис приема заявок 


#### 1. Сборка проекта
     1.1 Скачать zip-архив и распаковать его.
        
     1.2 В папке spring-credit-service-master с проектом открыть терминал(консоль), прописать следующие команды:
            
            $ mvn package
            
            $ java -jar target/credit-0.0.1-SNAPSHOT.jar
            
         Сервер запустится (localhost:8080).
            
     1.3 Для остановки сервера необходимо использовать комбинацию клавиш CTRL+C.
        
        
#### 2. Запросы к сервису 
    - /claim  (PUT) - подача заявки на кредит
        Параметры в теле запроса:
            - amount - сумма (int) (обязательный),
            - term - срок (int) (обязательный),
            - firstName - имя клиента (String) (обязательный),
            - lastName - фамилия клиента (String) (обязательный),
            - clientId - личный идентификатор клиента (long) (обязательный),
            - country - название страны (String) (обязательный).
            
        По умолчанию все заявки одобрены (confirmed = true). Если клиент заблокирован (blocked = true), 
        то заявка не добавится в таблицу `claims`, появится сообщение "Пользователь заблокирован, заявка отклонена".
            
            
    - /claims (GET) - получить все одобренные заявки
        
        
    - /claims/client/{clientId}  (GET) - получить все заявки данного клиента
          clientId - личный идентификатор клиента (long)
          
          
    - /limit (POST) - изменить лимит заявок для конкретной страны
        Параметры в теле запроса:
             - country - название страны (String) (обязательный),
             - limit - размер лимита заявок (int) (обязательный)
             
        Если страны нет в таблице `countries`, появится сообщение "Неверное название страны".