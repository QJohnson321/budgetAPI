pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build + Unit Tests') {
      steps {
        bat 'mvn -B clean test'
      }
    }
  }
}