# airbnb-spring

##### User Module
| Url  | Method   | Parameters    | Body | Description                  | Returns                                   | Headers        |
|:-----| ---------|:--------------|:-----|:-----------------------------|:------------------------------------------|:---------------|
|user/ | GET      |               |      | get users                    | list of users (can be empty)              |                |
|user/ | GET      |               |      | get users [with passwords]   | list of users (can be empty)              | email password |
|user/ | GET      | email         |      | get user by email            | user with given email or empty response   | email password |
|user/ | GET      | name surname  |      | get user by name and surname | list of users with given name and surname | email password |
|user/ | POST     |               | user | update existing user         | true if user was updated and false if not | email password |
|user/ | PUT      |               | user | create or update user        | updated user                              | email password |
|user/ | DELETE   | email         |      | delete user                  | true if succeeded and false if not        | email password |
|user/ | DELETE   |               | user | delete user                  | true if succeeded and false if not        | email password |
