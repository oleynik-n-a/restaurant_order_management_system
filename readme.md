Система управления заказами в ресторане
=======================================
Краткое описание проекта
------------------------
Данное приложение позволяет посетителям и сотрудникам ресторана взаимодействовать с заказами и меню.
Посетитель ресторана может делать заказы, выбирая разные блюда из меню, дожидаться их изготовления, оплачивать и
публиковать отзывы.
Сотрудник ресторана (администратор) имеет возможность изменять меню, блюда в нем, добавляя новые, меняя текущие и удаляя
старые, добавлять новых администраторов.

Как собрать проект
------------------
Проект готов к сборке. Достаточно только загрузить его с гита и нажать пуска приложения.

Как использовать проект
-----------------------
Данная программа рассчитана как на одноразовый, так и на многоразовый запуск. Между запусками данные в программе
записываются в файлы, расположенные в папке `DataBase`.

Запуская проект, наблюдаем меню с выбором:

```
Good morning!

  1. Sign in as visitor.
  2. Sign up as visitor.
  3. Sign in as admin.
  4. Exit system.

Your choice: 
```
Данное меню позволяет выбрать тип пользователя для дальнейшего взаимодействия с программой. Второй пункт позволяет
создать аккаунт посетителя, если он отсутствует.

Зайдем в существующий аккаунт от лица посетителя ресторана:

```
Your choice: 1
Input login: Bogdan
Input password: 1234
```
В программе по умолчанию есть 3 аккаунта: 1 для администратора, 2 для посетителей. Их логины в соответствии: `"Nikita"`,
`"Bogdan"` и `"Artem"`, пароль у всех трех аккаунтов одинаковые для удобства - `"1234"`.

Итак, мы зашли от лица пользователя. Тут появляется следующее меню:

```
Choose option:
  1. Make an order.
  2. Change order.
  3. Cancel order.
  4. Pay for bills.
  5. Log out.
  6. Exit system.

Your choice: 
```
Нажав на 5, посетитель может выйти из аккаунта, чтобы получить возможность войти в другой. При нажатии на 6 происходит
полный выход из системы.

Пункты 1, 2, 3 интуитивно понятны по логике своего действия.

Рассмотрим пункт 4. Если мы нажнем на него сейчас, мы увидим, что на данный нет заказов, которые было бы нужно оплатить: 

```
Your choice: 4

All bills are paid.
```
Это связано с тем, что пользователь еще ничего не заказал, а следовательно ему нечего и оплачивать! Итак, сделаем заказ
и попробуем его оплатить, подождав при этом времени его изготовления. За одно на данном примере рассмотрим процесс
написания отзыва на блюдо:

```
Choose option:
  1. Make an order.
  2. Change order.
  3. Cancel order.
  4. Pay for bills.
  5. Log out.
  6. Exit system.

Your choice: 1
Current order:

Choose dishes (0 while ready):

Menu:
  1. name: ChocoPie, cost: 10, cooking time: 5, type: dessert.
  2. name: Yashkino, cost: 5, cooking time: 3, type: dessert.
  3. name: Soup, cost: 200, cooking time: 30, type: first course.
  4. name: Pasta, cost: 150, cooking time: 15, type: second course.

Your choice: 1

Current order:
name: ChocoPie, cost: 10, cooking time: 5, type: dessert.

Choose dishes (0 while ready):

Menu:
  1. name: ChocoPie, cost: 10, cooking time: 5, type: dessert.
  2. name: Yashkino, cost: 5, cooking time: 3, type: dessert.
  3. name: Soup, cost: 200, cooking time: 30, type: first course.
  4. name: Pasta, cost: 150, cooking time: 15, type: second course.

Your choice: 2

Current order:
name: ChocoPie, cost: 10, cooking time: 5, type: dessert.
name: Yashkino, cost: 5, cooking time: 3, type: dessert.

Choose dishes (0 while ready):

Menu:
  1. name: ChocoPie, cost: 10, cooking time: 5, type: dessert.
  2. name: Yashkino, cost: 5, cooking time: 3, type: dessert.
  3. name: Soup, cost: 200, cooking time: 30, type: first course.
  4. name: Pasta, cost: 150, cooking time: 15, type: second course.

Your choice: 0
```
И вот ровно в этот момент (момент нажатия 0) создается поток обработки заказа и запускается процесс готовки заказа
методом `pushOrder`, суммарное время которого (в секундах) будет равняться суммарному значению полей `_cookingTime` у
блюд:
```
public void pushOrder(Order order) {
    CookThread cookThread = new CookThread(order);
    cookThread.start();
}
```
Блюдо начали готовить, пошли дальше:

```
Choose option:
  1. Make an order.
  2. Change order.
  3. Cancel order.
  4. Pay for bills.
  5. Log out.
  6. Exit system.

Your choice: 4
Choose one of unpaid bills to pay for it (0 to return back):
  1. Order:
  1. name: ChocoPie, cost: 10, cooking time: 5, type: dessert.
  2. name: Yashkino, cost: 5, cooking time: 3, type: dessert.


Your choice: 1
Bill successfully paid.

Type 'review' if you want to make review on dishes: review
Choose dish to make review (0 to return back):
  1. name: ChocoPie, cost: 10, cooking time: 5, type: dessert.
  2. name: Yashkino, cost: 5, cooking time: 3, type: dessert.

Your choice: 2

Input rating (1 - 5): 4
Input comment: That was good, but very salty! 
Choose dish to make review (0 to return back):
  1. name: ChocoPie, cost: 10, cooking time: 5, type: dessert.
  2. name: Yashkino, cost: 5, cooking time: 3, type: dessert.

Your choice: 0
```
Заметим, что возможно оплатить блюдо у нас появилась только после того, как оно было готово.

Разбирать оставшиеся функции программы подробно не будем для экономии времени при чтении мануала, затронем лишь ключевые
особенности программы.

Паттерны
--------
В программе реализованы 2 паттерна, которые упростили работу с кодом: Builder и Singleton.

Паттерн Builder используется для создания объектов из иерархии пользователей и из иерархии блюд. В этом участвует
интерфейс `IBuilder` и классы `DishBuilder`, `DessertBuilder`, `FirstCourceBuilder`, `SecondCourceBuilder`,
`AdminBuilder`, `VisitorBuilder`, `UserBuilder`.

Паттерн Singleton реализован в виде классов `Kitchen` и `Validator`. Первый иллюстрирует единый объект - "кухню"
ресторана, в которой проходит приготовление заказов. Второй - объект валидатор, который является обработчиком процесса
авторизации пользователей.

Особенности программы
---------------------
* Каждый пользователь имеет уникальный логин;
* Каждое блюдо имеет уникальное название;
* Хранения данных производится путем сериализации/десериализации данных в файлы папки DataBase;
* Если очистить содержимое файлов DataBase, программа автоматически создаст 2-х пользователей и 1-го администратора по 
  умолчанию;

Требования
----------
Убедимся, что все требования к приложению выполнены:

1. Были соблюдены все принципы ООП и SOLID для структурирования кода.
2. Весь код написан согласно кодстайлу.
3. Только что Вы прочитали readme-файл с инструкцией, как пользоваться
   приложением.
4. Программа имеет качественный, интуитивно понятный интерфейс.

Дополнительно реализован следующий функционал:

1. Добавление новых администраторов текущим администратором.
2. Ведение нескольких аккаунтов пользователем.
3. Возможность выходить из одного аккаунта и заходить в другой без выхода и нового входа в приложение.
4. Сохранение данных даже в процессе приготовления заказа (при завершении программы сохраняется оставшееся время до
   окончания выполнения заказа).