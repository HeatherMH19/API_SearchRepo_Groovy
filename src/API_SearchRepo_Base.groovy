import groovy.test.GroovyTestCase

class BaseTest extends GroovyTestCase{

    def tools = new GroovyScriptEngine('src').with{
        loadScriptByName('API_SearchRepo_Tools.groovy')
    }

    //Verify base repo search is functional
    void testConnectionSuccess() {
        def conn = tools.createConnection()

        //Able to access repo search
        assert conn.getResponseCode() == 200
    }

    //Verify cannot get repos without a search query
    void testConnectionFailNoQuery() {
        def conn = tools.createConnection('')

        assert conn.getResponseCode() == 422

    }
}
