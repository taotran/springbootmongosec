package contracts.hello

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return 2 users"

    request {
        url "/api/v1/users"
        method GET()
    }

    response {
        status 200
        headers {
            contentType applicationJson()
        }
        body(file("response.json"))
    }

}