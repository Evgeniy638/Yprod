## Деплой вручную
Команды для ручного деплоя (нужно обязательно запустить докер демона) (выполнять все в корне):

`./mvnw -D"maven.test.skip=true" package`

`heroku login`

`heroku container:login`

`heroku container:push -a yprod web`

`heroku container:release -a yprod web`

После сборки удалите `package-lock.json`
