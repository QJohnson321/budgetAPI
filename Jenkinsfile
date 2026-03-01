pipeline {
  agent any

  tools {
    maven 'Maven'
  }

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