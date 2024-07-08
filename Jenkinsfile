pipeline {
  agent any 
  stages {
    stage('Compile') {
        steps {
            script {
                sh 'mvn clean compile'
            }
        }
    } stage('Build') {
        steps {
            script {
                sh 'mvn install'
            }
        }
    } stage('Test') {
        steps {
            script {
                sh 'mvn test'
            }
        }
    } stage('Deploy') {
        steps {
            script {
                echo "Deployment in progress"
            }
        }
    }
  }
}
      
