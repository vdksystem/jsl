def call(body) {
    // Init the MPL library
    MPLInit()

    MPLPipeline {
        agent_label = 'master'
        modules.Deploy = null
        modules.Test = null
        modules.Analyze = [:]
        maven = [
                tool           : 'M3',
                settings_config: 'nexus_maven_settings',
                extra_args     : '-DskipTests'
        ]
        sonarqube = [
                server: 'sonar_server'
        ]
    }

}