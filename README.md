# Todo API (Java/Spring)

As a part of our prep-work weeks we will be building a Todo API.

Throughout the first two weeks we built a Todo Client that consumed a Todo API in a docker container.

Now that we know some of the basics of Spring Web we can start building the Todo API ourselves.

# Process & Submission

1. fork this repo into your `cohort-#/prep-weeks/<name>/` group with the name `todo-api-spring`
2. clone the forked repo locally to your machine (using the `ssh` URL)
3. complete the tasks!

## Tasks

1. implement the `TodoItem` fields and `ITodoItem` interface methods
2. implement the `static` `TodoItem` store interaction methods
3. implement the `TodoItemsController` method handler for the `GET /todos` endpoint
4. implement the `TodoItemsController` method handler for the `POST /todos` endpoint
5. implement the `TodoItemsController` method handler for the `PATCH /todos` endpoint
6. implement the `TodoItemsController` method handler for the `DELETE /todos` endpoint

## Git Workflow

- each task must be completed in its own branch `task-#` (ex: `task-1`)
- you must commit whenever you reach a stable point (**no massive / multi-file commits!!**)
- after completing each task 1-5 you must `merge` the task feature branch back into `master`
- after merging into `master` you must push your changes up before branching into the next `task-#`
- **after completing task 6 you must open a merge request** (`task-6` into `master`) on GitLab and assign your instructor(s) as the reviewer

# Requirements

Implement the following classes to build a web API that can be consumed by the todo client front end:

- `TodoItem` Model class
- `TodoItemsController` class

## TodoItem Model

Your `TodoItem` Model is a class from which `todoItem` instances can be created. Each `todoItem` instance will hold in own _state_ encapsulating the following data:

- `id`: a unique identifier for the item
- `text`: the todo item text
- `completed` a boolean field indicating the item's completion state (default `false`)

You will need to implement the blueprint for this state using `private` fields and the appropriate getters/setters (be mindful of which fields should be editable _externally_). An interface `ITodoItem` is available to help guide what you need to implement.

> Managing unique IDs

To manage items and unique IDs you will need to either keep track internal to the class (using `static` fields / methods) or you may implement an external `TodoItemStore` class as a data structure container for the items.

In the starter code you will find an semi-complete implementation of the `TodoItem` class with the logic for automatically assigning the Ids internally. Feel free to use it as it, extend it or create an external store if you are looking for an extra challenge.

> Managing items in the store

A `static` `store` for holding `TodoItem` objects and a number of `static` methods for managing items in that store have been included. The methods are not much more than signatures for now. Use the signature and method name to guide the logic of your implementation.

## TodoItem Controller

Your `TodoItemController` class will be responsible for exposing the `TodoItem` data as a web API. As an API you will need to implement handler methods for the following **endpoints**

> recall that an **endpoint** is:

- the **unique pair** of an HTTP method (`GET, POST, DELETE` etc.) _and_ its URL path (`/the/path`).

> **example**: `GET /todos`

- **method**: `GET`
- **path**: `/todos`

### `GET /todos`

> **internal behavior**

1. gets all the todo items from the store

> **response body**

- returns the list of todo items

### `POST /todos`

> **request body**: a JSON body consisting of the todo item text

```json
{
  "text": "the text of the item"
}
```

> **internal behavior**

1. creates and stores a new todo item in the store

> **response body**: a JSON body consisting of the new todo item

- note that the order of the fields does not matter

```json
{
  "id": 1,
  "completed": false,
  "text": "the text of the item"
}
```

### `PATCH /todos/{todoId}`

> **request path variable**: the `todoId` is a path variable that must be captured in the handler (see the [@PathVariable Baeldung article](https://www.baeldung.com/spring-pathvariable))

> **internal behavior**

1. look up the todo item using the `todoId` path variable value
2. mark the item as complete

> **successful response body**: a JSON body consisting of the updated todo item

- if an item with the `todoId` is found

```json
{
  "id": 1,
  "completed": true,
  "text": "the text of the item"
}
```

> **unsuccessful response**: if an item with the `todoId` is **not found**

- return a `404` status code (Not Found) response

### `DELETE /todos/{todoId}`

> **request path variable**: the `todoId` is a path variable that must be captured in the handler

> **internal behavior**

- delete the todo item using the `todoId` path variable value

> **successful response**: if the `static` `deleteItem()` method returns `true`

- this indicates the item was found and successfully removed from the store
- return a `204` status code (No Content success) response

> **unsuccessful response**: if the `static` `deleteItem()` method returns `false`

- this indicates the item was not found and therefore could not be removed from the store
- return a `404` status code (Not Found) response

# References

- [the `@PathVariable` annotation](https://www.baeldung.com/spring-pathvariable)
- [the `ResponseEntity` handler method return type](https://www.baeldung.com/spring-response-entity) allows you to return a body and / or configure the response status code, headers etc.
