Необходимо их врубить флагом `-ea` или `-enableassertions`.

[Гайд от Oracle](https://docs.oracle.com/javase/8/docs/technotes/guides/language/assert.html).

# Примеры использования

## Базы данных.
```java
    public final java.sql.Connection connection;
    // иные поля

    // Подключение к базе данных
    public StorageDatabase(... ...) throws SQLException {
        // Реализация создания коннекта.
        connection = ...;
    }

    public void init() throws SQLException {
        // Проверка существования подключения и вывод ошибки
        assert (connection != null) : "Не удалось проверить таблицы из-за отсутствия подключения с БД";
        // Остальной код
    }

    public void close() throws SQLException {
        // Проверка существования подключения и вывод ошибки
        assert (connection != null) : "Не удалось закрыть подключение с БД, так как его тупо нет";
        // Закрытие чего-то там...
        // Закрытие подключения допустим
        connection.close();
    }
```

## Конфигурации.
```java
    public final java.io.File file;
    public final org.bukkit.configuration.file.FileConfiguration configuration;

    // какой-то код...

    // ...

    public void save() throws Exception{
        // Проверка существования переменной и вывод ошибки
        assert (file != null) : "An error occurred while interacting with a file in the method save(). Reason: File is null.";
        // ...
        configuration.save(file);
    }

    public float getFloat(final String path, float def) {
        // Проверка существования типа данных в конфигурации
        assert !(configuration.get(path) instanceof Float) :
                "An error occurred while getting the value through the method getFloat(String, float). Path value: " + path + ", default value - " + def;
        // ...
        return org.bukkit.util.NumberConversions.toFloat(configuration.get(path, def));
    }
```