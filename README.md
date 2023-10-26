# REST API для учета продаж коллекционных монет из драгоценных металлов

API позволяет добавлять, удалять, обновлять и считывать драгоценные коллекционные монеты, которые есть в базе. Монеты сделаны из драгоценных металлов и имеют коллекционную ценность. Через API можно рассчитать коллекционную составляющую цены монеты на заданную дату исходя из курсов драгоценных металлов на сайте Центрального Банка РФ (https://www.cbr.ru/hd_base/metall/metall_base_new).
Для модели взят формат монет представленных на сайте - https://www.centrinvest.ru/for-individuals/sale-of-coins

## Методы REST API приложения

API получает и отдает данные в XML формате. Пример формата ответа/запроса:

<pre>
    &lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;
    &lt;Coin&gt;
        &lt;name&gt;Алмазный-22&lt;/name&gt;
        &lt;denomination&gt;25&lt;/denomination&gt;
        &lt;metal&gt;серебро 925/1000&lt;/metal&gt;
        &lt;weight&gt;155.00&lt;/weight&gt;
        &lt;catalogNumber&gt;5115-0159&lt;/catalogNumber&gt;
        &lt;cost&gt;21500&lt;/cost&gt;
    &lt;/Coin&gt;
</pre>

### Получение монеты

Форма запроса: /coins/get/{id}

### Получить коллекционную составляющую цены монеты на заданную дату исходя из курсов драгоценных

Форма запроса: /collector_value/{id}?day={day}&month={month}&year={year}

### Для получения практического опыта, добавлена работа с добавлением запроса и его обработка из очереди через RabbitMQ
  
-Отправить в очередь запрос на получение Монеты: /coins/rabbit?key=get

-Получить Монету по запросу из очереди: /resultGetCoin

-Отправить в очередь запрос на получение всех Монет: /coins/rabbit?key=getAll

-Получить Монету по запросу из очереди: /resultGetAllCoin

### Получение всех монет

Форма запроса: /coins/get/all

### Добавить новую монету

Форма запроса: /coins/add

### Добавить новую монету через XML файл

Форма запроса: /coins/add_from_file

### Удалить монету из базы

Форма запроса: /coins/delete/{id}

### Обновить значения полей монеты

Форма запроса: /coins/patch/{id}

### Получить монеты по значению каталожного номера (Раздел, Металл, Номинал)

Форма запроса: /coins/section_denomination_metal/{id}.

