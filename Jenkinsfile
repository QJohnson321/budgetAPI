pipeline {
  agent any

  tools {
    maven 'Maven'
    jdk 'JDK17'
  }

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build + Unit Tests') {
      steps {
        bat 'java -version'
        bat 'mvn -B clean test'
      }
    }
  }
}