pipeline {
    agent any
      tools {
         maven "MAVEN_LOCAL"
    }
    stages {
        stage ('Build Backend'){
            steps{
                sh 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Build Testes'){
            steps{
                sh 'mvn test'
            }
        }
    }
}
