import groovy.json.JsonSlurper as JsonSlurper
import groovy.test.GroovyTestCase

class SortingTest extends GroovyTestCase {

    def tools = new GroovyScriptEngine('src').with{
        loadScriptByName('API_SearchRepo_Tools.groovy')
    }

    //Verify sort by stars with default desc order
    void testSortByStarsDesc(){
        def conn = tools.createConnection('?q=test&sort=stars')

        def responseText = conn.getInputStream().text
        def responseJson = new JsonSlurper().parseText(responseText)

        //Save the items array into its own variable for ease of use
        def responseItems = responseJson.items

        def currentStars = responseItems[0].stargazers_count
        for (Map mapTemp : responseItems) {
            //Allows skip of first iteration and in place in case of two repos having equal stars
            if (currentStars == mapTemp.stargazers_count) {
                continue
            }
            //Verify previous item has more stars than current item
            //When pass, saves the current item as currentStars for next iteration
            assert currentStars > mapTemp.stargazers_count
            currentStars = mapTemp.stargazers_count
        }
    }
    //Verify sort by forks with asc order
    //NOTE: This test is current failing assertion as there appears to be a bug in github's 'sort by forks' functionality
    void testSortByForksAsc(){
        //Making sure we only get repos with a good amount of forks to avoid getting a million pages full of repos with identical fork numbers
        def conn = tools.createConnection('?q=test+forks:>20&sort=forks&order=asc')

        def responseText = conn.getInputStream().text
        def responseJson = new JsonSlurper().parseText(responseText)

        //Save the items array into its own variable for ease of use
        def responseItems = responseJson.items

        def currentForks = responseItems[0].forks_count
        for (Map mapTemp : responseItems) {
            //Allows skip of first iteration and in place in case of two repos having equal forks
            if (currentForks == mapTemp.forks_count) {
                continue
            }
            //Verify previous item has fewer forks than current item
            //When pass, saves the current item as currentForks for next iteration
            assert currentForks < mapTemp.forks_count
            currentForks = mapTemp.forks_count
        }
    }
}