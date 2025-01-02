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
                }
            }
        }
    }
}