pipeline {
  agent any 
  stages {
    stage('Compile') {
        steps {
            withMaven(maven : 'apache-maven-3.6.1') {
                bat 'mvn clean compile'
            }
        }
    } stage('Build') {
        steps {
            withMaven(maven : 'apache-maven-3.6.1') {
              bat 'mvn install'
            }
        }
    } stage('Test') {
        steps {
            withMaven(maven : 'apache-maven-3.6.1') {
                bat 'mvn test'
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
      
