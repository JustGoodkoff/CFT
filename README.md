# Инструкция по использованию утилиты фильтрации данных

Утилита предназначена для фильтрации данных из текстовых файлов, разделения их по типам (целые числа, вещественные числа, строки) и записи в соответствующие выходные файлы. Также утилита собирает и выводит статистику по обработанным данным.

## Сборка и запуск

### 1. Сборка проекта

Для сборки проекта используйте Gradle.


#### Сборка с Gradle:

```
./gradlew shadowjar
```

JAR-файл собирается с использованием плагина shadowjar.
Собранный JAR-файл будет находиться в папке `build/libs`.

### Используемые технологии

- Java 21.
- Gradle 8.10
- Плагин для сборки **com.github.johnrengelman.shadow 8.1.1** - https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow
- Библиотека для парсинга аргументов командной строки **commons-cli:commons-cli 1.9.0** - https://mvnrepository.com/artifact/commons-cli/commons-cli/1.9.0

### 2. Запуск утилиты

После сборки запустите утилиту с помощью команды:

```bash
java -jar <имя-jar-файла> [опции] [файлы]
```

## Опции командной строки

| Опция | Описание                                                                                                                      |
| ----- | ----------------------------------------------------------------------------------------------------------------------------- |
| `-o`  | Путь для выходных файлов. По умолчанию — текущая директория.                                                                  |
| `-p`  | Префикс для имен выходных файлов. По умолчанию — пустая строка.                                                               |
| `-a`  | Режим добавления данных в существующие файлы (по умолчанию файлы перезаписываются).                                           |
| `-s`  | Вывод краткой статистики (количество элементов каждого типа).                                                                 |
| `-f`  | Вывод полной статистики (дополнительно минимальное, максимальное значения, сумма и среднее для чисел; длина строк для строк). |

## Входные данные

Входные файлы содержат данные, разделенные переводом строки.

Данные могут быть:

- **Целыми числами** (например, `123`, `-456`).
- **Вещественными числами** (например, `789.01`, `-3.14e5`).
- **Строками** (все остальное).

## Выходные данные

Утилита создает три выходных файла:

- `<префикс>integers.txt` — для целых чисел.
- `<префикс>floats.txt` — для вещественных чисел.
- `<префикс>strings.txt` — для строк.

Если какой-то тип данных отсутствует, соответствующий файл не создается.

## Примеры использования

### 1. Базовый пример

```bash
java -jar data-filter.jar input1.txt input2.txt
```

- Обрабатывает файлы `input1.txt` и `input2.txt`.
- Создает файлы `integers.txt`, `floats.txt`, `strings.txt` в текущей директории.
- Выводит краткую статистику.

### 2. Указание выходной директории и префикса

```bash
java -jar data-filter.jar -o ./output -p result_ input1.txt
```

- Обрабатывает файл `input1.txt`.
- Создает файлы `./output/result_integers.txt`, `./output/result_floats.txt`, `./output/result_strings.txt`.
- Выводит краткую статистику.

### 3. Режим добавления данных

```bash
java -jar data-filter.jar -a input1.txt
```

- Обрабатывает файл `input1.txt`.
- Если выходные файлы существуют, данные добавляются в конец.
- Выводит краткую статистику.

### 4. Полная статистика

```bash
java -jar data-filter.jar -f input1.txt
```

- Обрабатывает файл `input1.txt`.
- Выводит полную статистику, включая минимальное, максимальное значения, сумму и среднее для чисел, а также длину строк.

### 5. Использование тестовых данных

Если входные файлы не указаны, утилита создает тестовый файл `test_data.txt` и использует его:

```bash
java -jar data-filter.jar -s
```

- Создает файл `test_data.txt` с тестовыми данными.
- Обрабатывает его и выводит краткую статистику.

## Формат статистики

### Краткая статистика (`-s`)

```
Integers: count=3
Floats: count=2
Strings: count=3
```

### Полная статистика (`-f`)

```
Integers: count=3, min=-456, max=123, sum=666, avg=222.00
Floats: count=2, min=-314000.00, max=789.01, sum=-313210.99, avg=-156605.50
Strings: count=3, min_length=5, max_length=11
```

- Если указать обе опции, будет выведена полная статистика.  
- Если не указать формат статистики, будет выведена краткая.

## Обработка ошибок

- Если входной файл не существует, программа выводит сообщение об ошибке и продолжает обработку остальных файлов.
- Если программа не может распознать аргументы командной строки, она выводит сообщение об ошибке и прекращает работу программы.
- Если выходной файл не может быть создан, программа выводит сообщение об ошибке.
- Ошибки выводятся в System.err.

## Пример тестового файла

Содержимое `test_data.txt`:

```
123
-123
0
qwertyuiop[]
-3.14e5
numbernumbernumber
0123
1234567890123456789012345678901234567890
1234567890123456789012345678901234567890.5
```

## Пример выходных файлов

Содержимое `integers.txt`:
```
123
-123
0
0123
1234567890123456789012345678901234567890
```

Содержимое `floats.txt`:
```
-3.14e5
1234567890123456789012345678901234567890.5
```

Содержимое `strings.txt`:
```
qwertyuiop[]
numbernumbernumber
```

## Исходный текст задачи
При запуске утилиты в командной строке подается несколько файлов, содержащих в
перемешку целые числа, строки и вещественные числа. В качестве разделителя
используется перевод строки. Строки из файлов читаются по очереди в соответствии с их
перечислением в командной строке.   
Задача утилиты записать разные типы данных в разные файлы. Целые числа в один
выходной файл, вещественные в другой, строки в третий. По умолчанию файлы с
результатами располагаются в текущей папке с именами integers.txt, oats.txt, strings.txt.
Дополнительно с помощью опции -o нужно уметь задавать путь для результатов. Опция -p
задает префикс имен выходных файлов. Например -o /some/path -p result_ задают вывод в
файлы /some/path/result_integers.txt, /some/path/result_strings.txt и тд.
По умолчанию файлы результатов перезаписываются. С помощью опции -a можно задать
режим добавления в существующие файлы.  
Файлы с результатами должны создаваться по мере необходимости. Если какого-то типа
данных во входящих файлах нет, то и создавать исходящий файл, который будет заведомо
пустым, не нужно.  
В процессе фильтрации данных необходимо собирать статистику по каждому типу данных.
Статистика должна поддерживаться двух видов: краткая и полная. Выбор статистики
производится опциями -s и -f соответственно. Краткая статистика содержит только
количество элементов записанных в исходящие файлы. Полная статистика для чисел
дополнительно содержит минимальное и максимальное значения, сумма и среднее.
Полная статистика для строк, помимо их количества, содержит также размер самой
короткой строки и самой длинной.  
Статистику по каждому типу отфильтрованных данных утилита должна вывести в консоль.
Все возможные виды ошибок должны быть обработаны. Программа не должна «падать».
Если после ошибки продолжить выполнение невозможно, программа должна сообщить об
этом пользователю с указанием причины неудачи. Частичная обработка при наличии
ошибок более предпочтительна чем останов программы. Код программы должен быть
«чистым». 