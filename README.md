# Серверное приложение для Системы Адаптации Сотрудников

Серверное приложение написано на Java 17 версии. Приложение использует Spring Security с использованием JWT токенов, само приложение общается по REST API. 
Для запуска используется docker-compose файл. Для приложения и БД
### Установка
```bash
# Клонирование репозитория
git clone <repository-url>
cd AdaptationFlow

# Сборка для продакшена
docker-compose up
```

## 🔌 API интеграция

### Endpoints приложения
![Главная страница](https://github.com/DorofeeVDaniil1/AdaptationFlow/blob/main/photos/img.png)

### Формат данных сотрудника:
```typescript
POST http://localhost:8080/api/auth/register
    Content-Type: application/json

{
    "login": "",
    "password": "",
    "firstName": "",
    "lastName": "",
    "email": ""
}
```
![Главная страница](https://github.com/DorofeeVDaniil1/AdaptationFlow/blob/main/photos/img_1.png)


## БД и сущности
![Главная страница](https://github.com/DorofeeVDaniil1/AdaptationFlow/blob/main/photos/img_2.png)


## 🔮 Планы развития

- [ ] Формирование отчетов
- [ ] Игровые механики
- [ ] Система уведомлений
- [ ] База знаний сотрудников
- [ ] 
