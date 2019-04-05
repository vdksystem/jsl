def call(body) {
    // Init the MPL library
    MPLInit()

    // Executing the pipeline without additional configuration
    pipeline {  // Declarative pipeline
        agent {
            label 'master'
        }
        stages {
            stage('Checkout') {
                steps {
                    MPLModule()
                }
            }
            stage('Analyze') {
                steps {
                    MPLModule('Analyze', [
                            sonarqube: [
                                    server: 'sonar_server'
                            ]
                    ])
                }
            }
            stage('Build') {
                steps {
                    MPLModule('Build', [ // Using overriden Maven Build
                                         maven: [
                                                 tool           : 'M3',
                                                 settings_config: 'nexus_maven_settings',
                                                 extra_args     : '-DskipTests'
                                         ]
                    ])
                }
            }
        }
    }
}