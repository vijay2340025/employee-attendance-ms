pipeline {
    agent any

    stages {
        stage('install') {
            steps {
                sh 'mvn clean install -P burner'
            }
        }

        stage('docker') {
            steps {
                withDockerRegistry(url: '', credentialsId: 'docker') {
                    sh 'cd access-control-api && docker build -t vijay2340025/access-control-api:latest .'
                    sh 'docker push vijay2340025/access-control-service:latest'

                    sh 'cd gateway-server && docker build -t vijay2340025/api-gateway:latest .'
                    sh 'docker push vijay2340025/api-gateway:latest'

                    sh 'cd report-api && docker build -t vijay2340025/report-api:latest .'
                    sh 'docker push vijay2340025/report-api:latest'

                    sh 'cd jwt && docker build -t vijay2340025/jwt:latest .'
                    sh 'docker push vijay2340025/jwt:latest'
                }
            }
        }

        stage('clean') {
            steps {
                cleanWs()
            }
        }
    }
}
