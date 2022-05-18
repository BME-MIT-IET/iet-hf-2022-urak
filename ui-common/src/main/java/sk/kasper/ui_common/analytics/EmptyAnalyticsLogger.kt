package sk.kasper.ui_common.analytics

class EmptyAnalyticsLogger: AnalyticsLogger {

    override fun log(event: String, attributes: Map<String, String>) {
        // This empty analytics logger is probably used for testing purposes, as a mock/dummy?
    }

}