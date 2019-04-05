node {
   def mvnHome
   stage('Preparation') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/vdksystem/spring-petclinic.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.
      mvnHome = tool 'M3'
   }
   stage('SonarQube analysis') {
    withSonarQubeEnv('sonar_server') {
      sh "'${mvnHome}/bin/mvn' org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar"
    }
  }
   stage('Build') {
      // Run the maven build
      if (isUnix()) {
         sh "'${mvnHome}/bin/mvn' -DskipTests -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
   stage('Results') {
       echo 'saving results'
      //junit '**/target/surefire-reports/TEST-*.xml'
      //archiveArtifacts 'target/*.jar'
   }
   stage('Publish') {
       withMaven(maven: 'M3', mavenSettingsConfig: 'my-maven-settings') {
           sh "mvn -DskipTests -Dmaven.test.failure.ignore clean deploy"
       }
   }
}