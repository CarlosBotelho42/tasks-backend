pipeline {
    agent any
      tools {
         maven "M2_HOME"
    }
    stages {
        stage ('Build Backend'){
            steps{
                sh 'mvn clean package -DskipTests=true'
            }
        }
    }
}
