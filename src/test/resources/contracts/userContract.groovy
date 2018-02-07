import org.springframework.cloud.contract.spec.Contract

Contract.make {
    //ignored()
    request {
        method 'GET'
        urlPath('/api/v1/users')
    }
    response {
        status 200
        body(
                '''
                {
                  "username": "admin",
                  "password": "admin123",
                  "age": 14,
                  "accountNonExpired": true,
                  "accountNonLocked": true,
                  "credentialsNonExpired": true,
                  "enabled": true
                }
                '''
        )
        headers {
            header('Content-Type', 'application/json;charset=UTF-8')
        }
    }
}