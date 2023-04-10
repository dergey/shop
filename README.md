## Модели:

### Product (Товар)

```json
{
  "id": 1,
  "title": "Title",
  "score": 4.2,
  "availability": "IN_STOCK", // Варианты  NOT_AVALIABLE, TO_ORDER
  "expectation_availability": null,
  "review_count": 1,
  "price": "100 BYN",
  "original_price": "150 BYN",
  "description": "Description",
  "images": [
    {
      "id": "1",
      "mime_type": "image/jpeg",
      "description": "Main view",
      "description_ru": "Общий вид"
    },
    {
      "id": "2",
      "description": "Side view",
      "description_ru": "Вид сбоку"
    },
    ...
  ],
  "catalog": {
    "id": 3,
    "name": "Bed",
    "name_ru": "Кровати",
    "parent": {
      "id": 2,
      "name": "Bed and matrasi",
      "name_ru": "Кровати и матрасы",
      "parent": {
        "id": 1,
        "name": "Bedroom furnitures",
        "name_ru": "Мебель для спальни"
      }
    }
  },
  "attributes": [
    {
      "type": "COLOR",
      "name": "Color",
      "name_ru": "Цвет",
      "value": "red",
      "value_ru": "Красный"
    },
    {
      "type": "SIZE",
      "name": "Size",
      "name_ru": "Размер",
      "value": "200x160",
      "value_ru": "200x160"
    },
    ...
  ],
  "link_products": [
    {
      "product_id": 2,
      "attibute_type": "COLOR",
      "attibute_value": "green"
    },
    {
      "product_id": 3,
      "attibute_type": "COLOR",
      "attibute_value": "blue"
    },
    ...
  ]
}
```

### Review (Отзыв):

```json
{
  "name": "John Doe",
  "score": 1,
  "create_at": "01-01-2011",
  "content": "I bought this shit and it doesn't work"
}
```  

### Catalog (Каталог):

_(Учесть ползунок для цен)_

```json
{
  "id": 3,
  "name": "Bed",
  "name_ru": "Кровати",
  "parent": {
    "id": 2,
    "name": "Bed and matrasi",
    "name_ru": "Кровати и матрасы",
    "parent": {
      "id": 1,
      "name": "Bedroom furnitures",
      "name_ru": "Мебель для спальни"
    }
  },
  "attributes": [
    {
      "type": "COLOR",
      "name": "Color",
      "name_ru": "Цвет",
      "values": [
        {
          "value": "Red",
          "value_ru": "Красный"
        },
        {
          "value": "Blue",
          "value_ru": "Синий"
        },
        {
          "value": "Green",
          "value_ru": "Зеленый"
        }
      ]
    },
    ...
  ]
}
```

### Корзина (Cart):

```json
{
  "amount": "100 BYN",
  "discount": "30 BYN",
  "products": [
    {
      "id": 1,
      "title": "Gwozi",
      "title_ru": "Гвозди",
      "price": "20 BYN",
      "count": 2,
      "amount": "40 BYN"
    },
    ...
  ]
}
```

### Order (Заказ):

История заказов, Оформляемый заказ (ввод адреса доставки, оплата или ее выбор, отображение

 ```json
{
  "id": 1,
  "status": "NEW", //Возможны [PROCESSING, PROCESSED, DELIVERING, COMPLETED, CANCELED]
  "delivery": "COURIER", // Возможны [SELF_DELIVERY, POST]
  "delivery_address": {
    "city": null,
    "street": "ул. Ецукена",
    "building": "53",
    "flat": "22",
    "flor": "1",
    "entrance": "6",
    "zip_code": null
  },
  "delivery_phone": "+375293333333",
  "delivery_recipient": "John Doe",
  "amount": "100 BYN",
  "products": [
    {
      "id": 1,
      "title": "Gwozi",
      "title_ru": "Гвозди",
      "price": "20 BYN",
      "count": 2,
      "amount": "40 BYN"
    }
  ],
  "create_at": "2023-01-21T07:48:28Z",
  "update_at": "2023-01-21T07:48:28Z",
  "complete_at": "2023-01-21T07:48:28Z"
}
```

### User (Пользователь):

Используется для отображения информации о профиле для пользователя

```json
{
  "name": "Сергей",
  "surname": "Журавлёв",
  "phone": "+375293333333",
  "addresses": [
    {
      "city": null,
      "street": "ул.Тест",
      "building": "36",
      "flat": "553",
      "flor": "2",
      "entrance": "3",
      "zip_code": null,
      "is_default": true
    }
  ]
}
```

