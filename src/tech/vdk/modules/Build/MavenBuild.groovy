withMaven(maven: CFG.'maven.tool', mavenSettingsConfig: CFG.'maven.settings_config') {
    sh "mvn ${CFG.'maven.extra_args'} -Dmaven.test.failure.ignore clean deploy"
}
