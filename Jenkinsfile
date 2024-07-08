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
    } stage('Approve') {
        steps {
            timeout(time: 15, unit: "MINUTES") {
                input message: 'Do you want to approve the deployment?', ok: 'Yes'
            }
        }
    } stage('Deploy') {
          steps {
            echo "Deployment in progress"
          }
    }
  }
}
      
