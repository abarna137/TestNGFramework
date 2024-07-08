pipeline {
  agent any
  options {
    retry(3)
  }
  stages {
    stage('Compile') {
        steps {
            git url: 'https://github.com/abarna137/TestNGFramework'
            withMaven {
                sh 'mvn clean compile'
            }
        }
    }
    stage('Build') {
        steps {
            script {
                sh 'mvn install'
            }
        }
    }
    stage('Test') {
        steps {
            script {
                sh 'mvn test'
            }
        }
    }
    stage('Deploy') {
        steps {
            script {
                sh 'mvn package'
            }
        }
    }
  }
}
      
