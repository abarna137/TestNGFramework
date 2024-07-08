pipeline {
  agent any
  environment {
    PATH = "opt/maven3/bin:$PATH"
  }
  options {
    retry(3)
  }
  stages {
    stage("Checkout SCM") {
        steps {
            git branch: 'main', credentialsId: '7d1ace5b-9743-4c46-93c1-758115b60473', url: 'https://github.com/abarna137/TestNGFramework'
        }
    }
    stage("Compile") {
        steps {
            bat "mvn clean compile"
        }
    }
    stage("Build") {
        steps {
            bat "mvn install"
        }
    }
    stage("Test") {
        steps {
            bat "mvn test"
        }
    }
    stage("Approve") {
        steps {
            timeout(time: 15, unit: "MINUTES") {
                input message: 'Do you want to approve the deployment?', ok: 'Yes'
            }
        }
    }
    stage("Deploy") {
        steps {
            bat "mvn package"
        }
    }
  }
}
      
