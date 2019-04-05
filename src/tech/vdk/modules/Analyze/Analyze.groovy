withSonarQubeEnv(CFG.'sonarqube.server') {
    mvnHome = tool 'M3'
    sh "'${mvnHome}/bin/mvn' org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar"
}
