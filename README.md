# airbnb-spring

##### User Module
| Url  | Method   | Parameters    | Body | Description                  | Returns                                   |
|:-----| ---------|:--------------|:-----|:-----------------------------|:------------------------------------------|
|user/ | GET      |               |      | get users [with passwords]   | list of users (can be empty)              |
|user/ | GET      | email         |      | get user by email            | user with given email or empty response   |
|user/ | GET      | name surname  |      | get user by name and surname | list of users with given name and surname |
|user/ | POST     |               | user | update existing user         | true if user was updated and false if not |
|user/ | PUT      |               | user | create or update user        | updated user                              |
|user/ | DELETE   | email         |      | delete user                  | true if succeeded and false if not        |
|user/ | DELETE   |               | user | delete user                  | true if succeeded and false if not        |
