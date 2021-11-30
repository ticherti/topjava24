[![Codacy Badge](https://app.codacy.com/project/badge/Grade/04d1953ffd084bd2a895ddbee2a985c2)](https://www.codacy.com/gh/ticherti/topjava24/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ticherti/topjava24&amp;utm_campaign=Badge_Grade)

Java Enterprise Online Project
===============================
Разработка полнофункционального Spring/JPA Enterprise приложения c авторизацией и правами доступа на основе ролей с использованием наиболее популярных инструментов и технологий Java: Maven, Spring MVC, Security, JPA(Hibernate), REST(Jackson), Bootstrap (css,js), datatables, jQuery + plugins, Java 8 Stream and Time API и хранением в базах данных Postgresql и HSQLDB.

Наиболее востребованные технологии /инструменты / фреймворки Java Enterprise:
Maven/ Spring/ Security/ JPA(Hibernate)/ REST(Jackson)/ Bootstrap(CSS)/ jQuery + plugins.

- [Вступительное занятие](https://github.com/JavaOPs/topjava)
- [Описание и план проекта](https://github.com/JavaOPs/topjava/blob/master/description.md)
- [Wiki](https://github.com/JavaOPs/topjava/wiki)
- [Wiki Git](https://github.com/JavaOPs/topjava/wiki/Git)
- [Wiki IDEA](https://github.com/JavaOPs/topjava/wiki/IDEA)
- [Демо разрабатываемого приложения](http://topjava.herokuapp.com/)

### 30.09: Старт проекта
- Начало проверки [вступительного задания](https://github.com/JavaOPs/topjava#-Домашнее-задание-hw0)

####  05.10 Дедлайн на сдачу HW0
### 07.10: 1-е занятие
####  07.10 Дедлайн подачи заявки на [дипломную программу](https://javaops.ru/view/register/diploma)
- Разбор домашнего задания вступительного занятия (вместе с Optional)
- Обзор используемых в проекте технологий. Интеграция ПО
- Maven
- WAR. Веб-контейнер Tomcat. Сервлеты
- Логирование
- Уровни и зависимости логгирования. JMX
- Домашнее задание 1-го занятия (HW1 + Optional)

### 14.10: 2-е занятие
- Разбор домашнего задания HW1 + Optional
- Библиотека vs Фреймворк. Стандартные библиотеки Apache Commons, Guava
- Слои приложения. Создание каркаса приложения
- Обзор Spring Framework. Spring Context
- Пояснения к HW2. Обработка Autowired
- Домашнее задание (HW2 + Optional)

### 21.10: 3-е занятие
- Разбор домашнего задания HW2 + Optional
- Жизненный цикл Spring контекста
- Тестирование через JUnit
- Spring Test
- Базы данных. Обзор NoSQL и Java persistence solution без ORM
- Настройка Database в IDEA
- Скрипты инициализации базы. Spring Jdbc Template
- Тестирование UserService через AssertJ
- Логирование тестов
- Домашнее задание (HW3 + Optional)

### 28.10: 4-е занятие
- Разбор домашнего задания HW3 + Optional
- Методы улучшения качества кода
- Spring: инициализация и популирование DB
- Подмена контекста при тестировании
- ORM. Hibernate. JPA
- Поддержка HSQLDB
- Домашнее задание (HW4 + Optional)
#### Начало выполнения [выпускного проекта](https://github.com/JavaOPs/topjava/blob/master/graduation.md)

### 04.11: 5-е занятие
- Обзор JDK 9/17. Миграция Topjava с 1.8 на 17
- Разбор вопросов
- Разбор домашнего задания HW4 + Optional
- Транзакции
- Профили Maven и Spring
- Пул коннектов
- Spring Data JPA
- Spring кэш
- Домашнее задание (HW5 + Optional)

### 11.11: 6-е занятие
- Разбор домашнего задания HW5 + Optional
- Кэш Hibernate
- Spring Web
- JSP, JSTL, internationalization
- Динамическое изменение профиля при запуске
- Конфигурирование Tomcat через maven plugin. Jndi-lookup
- Spring Web MVC
- Spring Internationalization
- Домашнее задание (HW6 + Optional)

#### Большое ДЗ + выпускной проект + начинаем [курс BootJava](https://javaops.ru/view/bootjava) + подтягиваем "хвосты".

### 25.11: 7-е занятие
- Разбор домашнего задания HW6 + Optional
- Автогенерация DDL по модели
- Тестирование Spring MVC
- Миграция на JUnit 5
- Принципы REST. REST контроллеры
- Тестирование REST контроллеров. Jackson
- jackson-datatype-hibernate. Тестирование через матчеры
- Тестирование через SoapUi. UTF-8
- Домашнее задание (HW7 + Optional)

### 02.12: 8-е занятие
- Разбор домашнего задания HW7 + Optional
- WebJars. jQuery и JavaScript frameworks
- Bootstrap
- AJAX. Datatables. jQuery
- jQuery notifications plugin
- Добавление Spring Security
- Домашнее задание (HW8 + Optional)

### 09.12: 9-е занятие
- Разбор домашнего задания HW8 + Optional
- Spring Binding
- Spring Validation
- Перевод DataTables на Ajax
- Форма login / logout
- Реализация собственного провайдера авторицазии
- Принцип работы Spring Security. Проксирование
- Spring Security Test
- Cookie. Session
- Домашнее задание (HW9 + Optional)

### 16.12: 10-е занятие
- Разбор домашнего задания HW10 + Optional
- Кастомизация JSON (@JsonView) и валидации (groups)
- Рефакторинг: jQuery конверторы и группы валидации по умолчанию
- Spring Security Taglib. Method Security Expressions
- Интерсепторы. Редактирование профиля. JSP tag files
- Форма регистрации
- Обработка исключений в Spring
- Encoding password
- Миграция на Spring 5
- Защита от межсайтовой подделки запросов (CSRF)
- Домашнее задание (HW10)

### 23.12: 11-е занятие
- Разбор домашнего задания HW10 + Optional
- Локализация datatables, ошибок валидации
- Защита от XSS (Cross Site Scripting)
- Обработка ошибок 404 (NotFound)
- Доступ к AuthorizedUser
- Ограничение модификации пользователей
- Деплой [приложения в Heroku](http://topjava.herokuapp.com)
- Собеседование. Разработка ПО
- Возможные доработки приложения
- Домашнее задание по проекту: составление резюме

### 27.12: Миграция на Spring-Boot
- Основы Spring Boot. Spring Boot maven plugin
- Lombok, база H2, ApplicationRunner
- Spring Data REST + HATEOAS
- Миграция приложения подсчета калорий на Spring Boot

### 16.01.22: Дедлайн на сдачу [выпускного проекта](https://github.com/JavaOPs/topjava/blob/master/graduation.md)
### 26.01.22: Получение дипломов для участников [Дипломной программы](https://javaops.ru/view/register/diploma)

-  <a href="http://www.mscharhag.com/2014/02/java-8-datetime-api.html">Java 8 Date and Time API</a>
-  <a href="http://web.archive.org/web/20201128101944/https://tproger.ru/translations/algorithms-and-data-structures/">Алгоритмы и структуры данных для начинающих: сложность алгоритмов</a>
-  [Головач: сложность алгоритмов в теме коллекций](https://www.youtube.com/watch?v=Ek9ijOiplNE&feature=youtu.be&t=778)
-  <a href="https://drive.google.com/file/d/0B9Ye2auQ_NsFNEJWRFJkVDA3TkU/view?usp=sharing&resourcekey=0-MPCuoLVdSLiSc7hlE2jefQ">Time complexity</a>
-  <a href="https://ru.wikipedia.org/wiki/Временная_сложность_алгоритма">Временная сложность алгоритма</a>
-  <a href="https://ru.wikipedia.org/wiki/Вычислительная_сложность">Вычислительная сложность</a>

#### ВНИМАНИЕ: варианты Optional делайте в `UserMealsUtil` в одной ветке в разных методах. Проще делать, проще проверять

### Optional (Java 8 Stream API)
```
Реализовать метод `UserMealsUtil.filteredByStreams` через Java 8 Stream API.
```
-  <a href="http://www.youtube.com/watch?v=_PDIVhEs6TM">Видео: Доступно о Java 8 Lambda</a>
-  <a href="https://devcolibri.com/java-8-killer-features-%D1%87%D0%B0%D1%81%D1%82%D1%8C-1/">Java 8: Lambda выражения</a>
-  <a href="https://devcolibri.com/java-8-killer-features-%D1%87%D0%B0%D1%81%D1%82%D1%8C-2/">Java 8: Потоки</a>
-  <a href="https://javadevblog.com/polnoe-rukovodstvo-po-java-8-stream.html">Pуководство по Java 8 Stream</a>
-  [Полное руководство по Java 8 Stream API в картинках и примерах](https://annimon.com/article/2778)    
-  [7 способов использовать groupingBy в Stream API](https://habrahabr.ru/post/348536)
-  <a href="http://habrahabr.ru/post/224593/">Лямбда-выражения в Java 8</a>
-  <a href="https://github.com/winterbe/java8-tutorial">A Guide to Java 8</a>
-  <a href="http://habrahabr.ru/company/luxoft/blog/270383/">Шпаргалка Java Stream API</a>
-  <a href="https://www.youtube.com/watch?v=hEyCK4ueBlc">Алексея Владыкин: Элементы функционального программирования в Java</a>
-  <a href="https://www.youtube.com/watch?v=iD8H7cmxw_w">Yakov Fain о новом в Java 8</a>
-  <a href="http://stackoverflow.com/questions/28319064/java-8-best-way-to-transform-a-list-map-or-foreach">stream.map vs forEach</a
-  Дополнительно
   - [Сергей Куксенко — Stream API, часть 1](https://www.youtube.com/watch?v=O8oN4KSZEXE)
   - [Сергей Куксенко — Stream API, часть 2](https://www.youtube.com/watch?v=i0Jr2l3jrDA)

### Optional 2 (+5 бонусов, только после выполнения базового и Optional задания!)
Сделать реализацию со сложностью O(N) (обратите внимание на п.13 замечаний)  
Решение должно быть рабочим в общем случае (работать в приложении с многими пользователями, не только при запуске main)  
Нельзя 2 раза проходить по исходному списку (в том числе его отфильтрованной или преобразованной копии)
- циклом за 1 проход по `List<UserMeal>`
    - без циклов по другим коллекциям/массивам (к ним также относим методы коллекций `addAll()/removeAll()`)
- через Stream API за 1 проход по исходному списку `meals.stream()`
    - нельзя использовать внешние коллекции, не являющиеся частью коллектора
    - возможно дополнительные проходы по частям списка, при этом превышение должно считаться один раз для всего подсписка. Те например нельзя разбить список на на 2 подсписка с четными и нечетными датами и затем их объединить, с подсчетом превышения для каждого элемента.

Ресурсы:
- [Java 8 Stream API, часть шестая: собственный коллектор](https://easyjava.ru/java/language/java-8-stream-api-chast-shestaya-sobstvennyj-kollektor)
- [Руководство по Java 8 Stream API: Collector](https://annimon.com/article/2778#collector)

### Замечания по использованию Stream API:
- Когда встречаешь что-то непривычное, приходится перестраивать мозги. Например, переход с процедурного на ООП программирование дается непросто. Те, кто не знает шаблонов (и не хотят учить) также их встречают плохо. Хорошая новость в том, что если это принять и начать использовать, то начинаешь получать от этого удовольствие. И тут главное не впасть в другую крайность:
  - [Используйте Stream API проще (или не используйте вообще)](https://habrahabr.ru/post/337350/)
- Если вас беспокоить производительность стримов, обязательно прочитайте про оптимизацию 
    - ["Что? Где? Когда?"](http://optimization.guide/intro.html)
    - [Перформанс: что в имени тебе моём?](https://habrahabr.ru/company/jugru/blog/338732/)
    - [Performance это праздник](https://habrahabr.ru/post/326242/)
    
При использовании Stream API производительность улучшиться только на больших задачах, где возможно распараллеливание.
Еще - просто так запустить и померять скорость JVM нельзя (как минимум дать прогреться и запустить очень большое число раз). Лучше использовать какие-нибудь бенчмарки, например [JMH](http://tutorials.jenkov.com/java-performance/jmh.html), который мы юзаем на другом проекте (Mastejava).
  
## ![error](https://cloud.githubusercontent.com/assets/13649199/13672935/ef09ec1e-e6e7-11e5-9f79-d1641c05cbe6.png) Замечания к HW0
- 1: Код проекта менять можно! Одна из распространенных ошибок как в тестовых заданиях на собеседовании, так и при работе на проекте, что ничего нельзя менять. Конечно при правках в рабочем проекте обязательно нужно проконсультироваться/проревьюироваться у авторов кода (находится по истории VCS)
- 2: Наследовать `UserMealWithExcess` от `UserMeal` нельзя, т.к. это разные сущности: Transfer Object и Entity. Мы будет их проходить на 2м уроке. Это относится и к зависимости.
- 3: Правильная реализация должна быть простой и красивой, можно сделать 2-мя способами: через стримы и через циклы. Сложность должна быть O(N), т.е. без вложенных стримов и циклов.
- 4: При реализации через циклы посмотрите в `Map` на методы `getOrDefault` или `merge`
- 5: **При реализации через `Stream` заменяйте `forEach` оператором `stream.map(..)`**
- 6: Объявляйте переменные непосредственно перед использованием (если возможно - сразу с инициализацией). При объявлении коллекций используйте тип переменной - интерфейс (Map, List, ..)
- 7: Если IDEA предлагает оптимизацию (желтым подчеркивает), например заменить лямбду на метод-референс, соглашайтесь (Alt+Enter)
- 8: Пользуйтесь форматированием кода в IDEA: `Alt+Ctrl+L`
- 9: Перед check-in проверяйте чендж-лист (курсор на файл и Ctrl+D): не оставляйте в коде ничего лишнего (закомментированный код, TODO и пр.). Если файл не меняется (например только пробелы или переводы строк), не надо его чекинить, делайте ему `revert` (Git -> Revert / `Ctrl+Alt+Z`).
- 10: `System.out.println` нельзя делать нигде, кроме как в `main`. Позже введем логирование.
- 11: Результаты, возвращаемые `UserMealsUtil.filteredByStreams` мы будем использовать [в нашем приложении](http://topjava.herokuapp.com/) для фильтрации по времени и отображения еды правильным цветом.
- 12: Обращайте внимание на комментарии к вашим коммитам в git. Они должны быть короткие и информативные (лучше на english)
- 13: Не полагайтесь в решении на то, что список еды будет подаваться отсортированным. Такого условия нет.
-----
## [Пример 7-го занятия онлайн стажировки, несколько видео открыто](https://github.com/JavaOPs/topjava/blob/master/doc/lesson07.md)

>  - ДЗ первого урока будет связано с созданием небольшого [CRUD](https://ru.wikipedia.org/wiki/CRUD) приложения (в памяти, без базы данных) на JSP и сервлетах
>  - основы JavaSсript необходимы для понимания проекта, начиная с 8-го занятия

### Полезные ресурсы
#### HTML, JavaScript, CSS 
- [Basic HTML and HTML5](https://learn.freecodecamp.org/responsive-web-design/basic-html-and-html5/say-hello-to-html-elements/)
- [Справочник по WEB](https://developer.mozilla.org/ru/)
- [Видео по WEB технологиям](https://www.youtube.com/user/WebMagistersRu/playlists)
- [Изучение JavaScript в одном видео уроке за час](https://www.youtube.com/watch?v=QBWWplFkdzw)
- <a href="http://www.w3schools.com/default.asp">HTML, CSS, JAVASCRIPT, SQL, JQUERY, BOOTSTRAP</a>
- <a href="https://www.youtube.com/watch?v=j0ycGQKqMT4">Введение в программирование на JavaScript</a>
- <a href="http://anton.shevchuk.name/javascript/html-css-javascript-standarts/">Стандарты кодирования для HTML, CSS и JavaScript’a</a>
- <a href="http://www.intuit.ru/studies/courses/1102/134/info">Основы работы с HTML/CSS/JavaScript</a>
- <a href="http://itchief.ru/lessons/javascript/94-javascript-introduction">JavaScript - Основы</a>
- <a href="http://learn.javascript.ru/first-steps">Основы JavaScript</a>
- <a href="http://itchief.ru/lessons/bootstrap-3/19-introduction-to-twitter-bootstrap-3">Bootstrap 3 - Основы</a>
- <a href="http://anton.shevchuk.name/jquery/">jQuery для начинающих</a>

#### Java (базовые вещи)
- <a href="http://www.intuit.ru/studies/courses/16/16/info">Интуит. Программирование на Java</a>
- <a href="https://github.com/JavaOPs/masterjava#Первое-занятие-многопоточность">1й урок MasterJava: Многопоточность</a>
- [Основы Java garbage collection](http://web.archive.org/web/20180831013112/https://ggenikus.github.io/blog/2014/05/04/gc)
- <a href="https://habrahabr.ru/post/134102/">Размер Java объектов</a>
- <a href="http://www.quizful.net/post/java-reflection-api">Введение в Java Reflection API</a>
- <a href="https://habrahabr.ru/users/tarzan82/topics/">Структуры данных в картинках</a>
- <a href="https://habrahabr.ru/company/luxoft/blog/157273/">Обзор java.util.concurrent.*</a>
- <a href="http://web.archive.org/web/20200808064416/http://www.skipy.ru/technics/synchronization.html">Синхронизация потоков</a>
- <a href="http://java67.blogspot.ru/2014/08/difference-between-string-literal-and-new-String-object-Java.html">String literal pool</a>
- <a href="https://habrahabr.ru/post/132241/">Маленькие хитрости Java</a>
-  <a href="https://github.com/winterbe/java8-tutorial">A Guide to Java 8</a>

### Туториалы, разное
- [Открытый курс: Spring Boot + HATEOAS](https://javaops.ru/view/bootjava)
- [Что нужно знать о бэкенде новичку в веб-разработке](https://tproger.ru/translations/backend-web-development)
- [Туториалы: Spring Framework, Hibernate, Java Core, JDBC](http://proselyte.net/tutorials/)

#### Сервлеты
-  <a href="https://devcolibri.com/как-создать-servlet-полное-руководство/">Как создать Servlet? Полное руководство.</a>
-  [Сервлеты](https://metanit.com/java/javaee/4.1.php)

#### JDBC, SQL
-  <a href="https://habrahabr.ru/post/123636/">Основы SQL на примере задачи</a>
-  <a href="https://www.youtube.com/playlist?list=PLIU76b8Cjem5qdMQLXiIwGLTLyUHkTqi2">Уроки по JDBC</a>
-  <a href="https://www.codecademy.com/learn/learn-sql">Learn SQL</a>
-  <a href="http://www.intuit.ru/studies/courses/5/5/info">Интуит. Основы SQL</a>
-  <a href="http://campus.codeschool.com/courses/try-sql/contents">Try SQL</a>
-  <a href="https://stepic.org/course/Введение-в-базы-данных-551">Курс "Введение в базы данных"</a>

#### Разное
-  <a href="http://javaops.ru/view/test">Вопросы по собеседованию, ресурсы для подготовки</a>
-  <a href="http://jeeconf.com/materials/intellij-idea/">Эффективная работа с кодом в IntelliJ IDEA</a>
-  <a href="http://www.quizful.net/test">Quizful- тесты онлайн</a>
-  <a href="https://stepic.org/course/Введение-в-Linux-73">Введение в Linux</a>

#### Книги
-  <a href="http://www.ozon.ru/context/detail/id/24828676/">Джошуа Блох: Java. Эффективное программирование. Второе издание</a>
-  <a href="http://www.labirint.ru/books/87603/">Гамма, Хелм, Джонсон: Приемы объектно-ориентированного проектирования. Паттерны проектирования</a>
-  <a href="http://www.bookvoed.ru/book?id=639284">Редмонд Э.: Семь баз данных за семь недель. Введение в современные базы данных и идеологию NoSQL</a>
-  <a href="http://www.ozon.ru/context/detail/id/3174887/">Brian Goetz: Java Concurrency in Practice</a>
-  <a href="http://bookvoed.ru/book?id=2593572">G.L. McDowell: Cracking the Coding Interview</a>
