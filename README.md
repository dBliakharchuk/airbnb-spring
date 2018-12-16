# airbnb-spring

##### Ports
Gateway : 8080 <br/>
User Module : 8081 <br/>
Message Module : 8082 <br/>
Bank Module : 8083 <br/>
Apartment Module : 8084 <br/>
Reservation Module : 8085 <br/>

##### User Module
| Url  | Method   | Parameters    | Body | Description                  | Returns                                   |
|:-----| ---------|:--------------|:-----|:-----------------------------|:------------------------------------------|
|user/ | GET      |               |      | get users                    | list of users (can be empty)              |
|user/ | GET      | email         |      | get user by email            | user with given email or empty response   |
|user/ | GET      | name surname  |      | get user by name and surname | list of users with given name and surname |
|user/ | POST     |               | user | update existing user         | true if user was updated and false if not |
|user/ | PUT      |               | user | create or update user        | updated user                              |
|user/ | DELETE   | email         |      | delete user                  | true if succeeded and false if not        |
|user/ | DELETE   |               | user | delete user                  | true if succeeded and false if not        |

##### Bank Module 
| Url  | Method   | Parameters    | Body        | Description                  | Returns                                     |
|:-----| ---------|:--------------|:------------|:-----------------------------|:--------------------------------------------|
|bank/ | POST     |               | PaymentInfo | validate credit card         | Response.200 (true) or Response.402 (false) |

##### User Module
| Url  | Method   | Parameters    | Body | Description                  | Returns                                   |
|:-----| ---------|:--------------|:-----|:-----------------------------|:------------------------------------------|
|user/ | GET      | email         |      | getNewestMessagesByEmaill    | newest messages                           |
|user/ | GET      | email selectedUser|      | getConversation | messages between users |
|user/ | PUT      |               | message | createMessage       | create message                              |

