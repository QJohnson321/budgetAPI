pipeline {
  agent any

  tools {
    maven 'Maven'
    jdk 'JDK17'
  }

  stages {

    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build + Unit Tests') {
      steps {
        bat 'mvn -B clean package'
      }
    }

    stage('Build Docker Image') {
      steps {
        bat 'docker build -t budgetapi:latest .'
      }
    }
  }
}