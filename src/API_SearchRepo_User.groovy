import groovy.json.JsonSlurper as JsonSlurper
import groovy.test.GroovyTestCase

class UserTest extends GroovyTestCase{

    def tools = new GroovyScriptEngine('src').with{
        loadScriptByName('API_SearchRepo_Tools.groovy')
    }

    //Verify only public repos for the given username are returned
    //User being searched for is my own, has two repos - one public, one private
    void testSearchByUserOnlyReturnsPublicRepos() {
        def conn = tools.createConnection('?q=user:asi-hhughes')

        def responseText = conn.getInputStream().text
        def responseJson = new JsonSlurper().parseText(responseText)

        //Saves the items array into it's own variable for ease of use
        def responseItems = responseJson.items

        for (Map mapTemp : responseItems) {
            assert mapTemp.owner.login == 'asi-hhughes'
            assert mapTemp.private == false
        }
    }

    //Verify user search matches on full login, not partial
    void testSearchByPartial() {
        def conn = tools.createConnection('?q=user:hhughes')

        def responseText = conn.getInputStream().text
        def responseJson = new JsonSlurper().parseText(responseText)

        //Saves the items array into it's own variable for ease of use
        def responseItems = responseJson.items

        for (Map mapTemp : responseItems) {
            assert mapTemp.owner.login !== 'asi-hhughes'
        }

    }
}