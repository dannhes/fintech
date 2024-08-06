Веб приложение для перевода фраз

Как запустить

1. Клонируйте репозиторий
    
git clone https://github.com/yourusername/translator-web-app.git

2. Соберите проект
   
cd translator/    
mvn clean install
    
3. Запустите приложение

mvn spring-boot:run
    
5. Получите доступ к приложению
    Откройте браузер и перейдите в http://localhost:8080/translate?text=Hello%20World&sourceLang=en&targetLang=es
   
Параметры запуска:
  - text: текст для перевода 
  - sourceLang: Исходный язык 
  - targetLang: Целевой язык 
Ответ: Переведенный текст

База данных: H2
