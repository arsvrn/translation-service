# Translation Application

## Описание
Веб-приложение для перевода текста с использованием внешних API.

## Запуск приложения

1. Убедитесь, что у вас установлен Docker Compose.
2. Склонируйте репозиторий:
```bash
git clone https://github.com/yourusername/translation-application.git
cd translation-application
```
3. Создайте/отредактируйте файл `.env`:
```env
DB_URL=jdbc:h2:mem:testdb
DB_USERNAME=sa
DB_PASSWORD=password
DB_DRIVER=org.h2.Driver

TRANSLATE_API_URL=https://translate.api.cloud.yandex.net/translate/v2/translate
TRANSLATE_API_KEY=t1.9euelZqVkpzHjJKNzM2TjZrNjsmUjO3rnpWax4uPj4nNnM-Tyc_GkJSOkMrl8_diTUFK-e9-PiVx_N3z9yJ8Pkr5734-JXH8zef1656VmpvJjY_JyYrPm5mTkcibzJuL7_zF656VmpvJjY_JyYrPm5mTkcibzJuL._CyM0wmSNHptd3mePpP7RPEIhFYHdAK_8GETH929n-BYX1oqlqRoe_RnEXMWlpB9RYP8JaZgEqjXoXK9929mBQ
TRANSLATE_FOLDER_API=b1g9nnu6pmofv94dhflu
```
4. Запустите Docker Compose:
```bash
docker-compose up -d
```

## Использование

### Перевод текста
1. Отправьте POST-запрос на `/api/translate` с телом в формате JSON или воспользуйтесь [Swagger](http://localhost:8080/swagger-ui/index.html):
```json
{
"text": "Hello world",
"sourceLang": "en",
"targetLang": "ru"
}
```