pipeline {
    agent any
      tools {
         maven "MAVEN_LOCAL"
    }
    stages {
        stage ('Build BackEnd'){
            steps{
                sh 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Unit Tests'){
            steps{
                sh 'mvn test'
            }
        }
         stage ('Sonar Analysis'){
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps{
                withSonarQubeEnv('SONAR_LOCAL') {
                    sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=sqp_f96d223bbf8ede63e2683953c1b228f30d233ad1 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**/Aplication.java"
                }
            }
        }
        stage ('Quality Gate'){
            steps{
                echo 'analisando quality gate'
                // sleep(10)
                // timeout(time: 1, unit: 'MINUTES') {
                //     waitForQualityGate abortPipeline: true
                //}
            }
        }
         stage ('Deploy BackEnd'){
            steps{
                deploy adapters: [tomcat9(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8081/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war '
            }
        }
          stage ('API Test'){
            steps{
                dir('api-test') {
                    git credentialsId: 'github_login', url: 'https://github.com/CarlosBotelho42/tastk-api-test.git'
                    sh 'mvn test'
                }
            }
        }
         stage ('Deploy FrontEnd'){
            steps{
                dir('frontend') {
                    git credentialsId: 'github_login', url: 'https://github.com/CarlosBotelho42/tasks-frontend.git'
                    sh 'mvn clean package'
                    deploy adapters: [tomcat9(credentialsId: 'TomcatLogin', path: '', url: 'http://localhost:8081/')], contextPath: 'tasks', war: 'target/tasks.war '
                }
            }
        }
         stage ('Deploy Prod'){
            steps{
                sh 'docker-compose up -d'
            }
        }
    }
}