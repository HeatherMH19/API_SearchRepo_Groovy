class Tools {

    static createConnection(params = "?q=test", requestMethod = "GET") {

        def host = ("https://api.github.com/search/repositories")
        def endpoint = new URL(host + params)
        def connection = endpoint.openConnection()
        connection.setRequestMethod(requestMethod)

        return connection
    }

}
