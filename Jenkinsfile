pipeline {
  agent any 
  stages {
    stage('Compile') {
        steps {
            script {
                withMaven(maven : 'apache-maven-3.6.1') {
                    bat 'mvn clean compile'
                }
            }
        }
    }
  }
}
      
