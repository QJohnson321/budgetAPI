pipeline {
  agent any

  tools {
    maven 'Maven'   // Jenkins must have a Maven tool named "Maven"
    jdk 'JDK17'     // Jenkins must have a JDK tool named "JDK17"
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build + Unit Tests') {
      steps {
        bat 'mvn clean test'
      }
    }

    stage('Package') {
      steps {
        bat 'mvn -DskipTests package'
      }
    }
  }

  post {
    always {
      junit 'target/surefire-reports/*.xml'
    }
  }
}