import groovy.test.*
import junit.textui.TestRunner

static suiteSearchRepo() {
    def suite = new AllTestSuite()
    def gts = new GroovyTestSuite()
    suite.addTestSuite(gts.compile("API_SearchRepo_Base.groovy"))
    suite.addTestSuite(gts.compile("API_SearchRepo_ResponseModel.groovy"))
    suite.addTestSuite(gts.compile("API_SearchRepo_Paging.groovy"))
    suite.addTestSuite(gts.compile("API_SearchRepo_Sorting.groovy"))
    suite.addTestSuite(gts.compile("API_SearchRepo_User.groovy"))
    return suite
}

TestRunner.run(suiteSearchRepo())