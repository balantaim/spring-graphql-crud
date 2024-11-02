# Spring, GraphQL CRUD project

## Basic example how to create CRUD functionalities with GraphQL

### Software
<b>Tools:</b> Spring, GraphQL, H2, Lombok, Data JPA

### GraphQL endpoint

http://localhost:8080/graphiql?path=/graphql

### H2 console endpoint

http://localhost:8080/h2-console

### Test script

```graphql
query FindAllCustomers {
    findAllCustomers {
        id
        userName
        fullName
        email
        enabled
        gender
        address
        payments {
            id
            currency
            created
            finalized
        }
    }
}

query FindCustomer {
    findCustomer(id: 1) {
        id
        userName
        fullName
        email
        enabled
        gender
        address
        payments {
            id
            currency
            created
            finalized
        }
    }
}

mutation CreateCustomer {
    createCustomer(userName: "newName", fullName: "UserFullName", email: "email3", gender: "", address: "") {
        id
        userName
        fullName
        email
    }
}

mutation UpdateCustomer {
    updateCustomer(id: 1, userName: "newName", fullName: "UserFullName", email: "email3", gender: "", address: "") {
        id
        userName
        fullName
        email
    }
}

mutation AddPayment {
    addPayment(id: 1, currency: 33.31) {
        id
        userName
        fullName
    }
}

mutation DeleteCustomer {
    deleteCustomer(id: 1){
        id
        userName
    }
}
```
