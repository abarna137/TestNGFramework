pipeline {
  agent any 
  stages {
    stage('Compile') {
      withMaven(maven : 'apache-maven-3.6.1') {
        bat 'mvn clean compile'
      }
    } stage('Build') {
    withMaven(maven : 'apache-maven-3.6.1') {
      bat 'mvn install'
      }
    } stage('Test') {
    withMaven(maven : 'apache-maven-3.6.1') {
      bat 'mvn test'
      }
    } stage('Approve') {
      timeout(time: 15, unit: "MINUTES") {
        input message: 'Do you want to approve the deployment?', ok: 'Yes'
      }
    } stage('Deploy') {
      echo "Deployment in progress"
    }
  }
}
      
