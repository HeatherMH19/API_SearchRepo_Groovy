import groovy.json.JsonSlurper as JsonSlurper
import groovy.test.GroovyTestCase

class PagingTest extends GroovyTestCase{

    def tools = new GroovyScriptEngine('src').with{
        loadScriptByName('API_SearchRepo_Tools.groovy')
    }

    //Verify default pagination (first page, 30 items per page)
    void testOnFirstPageWith30Items() {
        def conn = tools.createConnection()

        def responseText = conn.getInputStream().text
        def responseJson = new JsonSlurper().parseText(responseText)

        def responseHeaders = conn.getHeaderFields()
        assert responseHeaders.Link.toString().contains('<https://api.github.com/search/repositories?q=test&page=2>; rel="next"')
        assert responseJson.items.size() == 30
    }

    //Verify ability to select a specific page
    void testOnSecondPage(){
        def conn = tools.createConnection('?q=test&page=2')

        def responseHeaders = conn.getHeaderFields()
        assert responseHeaders.Link.toString().contains('<https://api.github.com/search/repositories?q=test&page=3>; rel="next"')
        assert responseHeaders.Link.toString().contains('<https://api.github.com/search/repositories?q=test&page=1>; rel="prev"')
    }

    //Verify ability to change how many repos are returned per page
    void testPageSizeIs100(){
        def conn = tools.createConnection('?q=test&per_page=100')

        //Puts the response text into json format
        def responseText = conn.getInputStream().text
        def responseJson = new JsonSlurper().parseText(responseText)

        assert responseJson.items.size() == 100
    }

}