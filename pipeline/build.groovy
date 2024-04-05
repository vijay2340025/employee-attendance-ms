pipeline {
    agent any

    stages {
        stage('install') {
            steps {
                sh 'mvn clean install -P burner'
            }
        }

        stage('docker') {
            withDockerRegistry(credentialsId: 'docker') {
                sh 'docker build -t vijay2340025/access-control-service:latest -f ./access-control-api/Dockerfile'
                sh 'docker push vijay2340025/access-control-service:latest'
            }
        }

        stage('clean') {
            steps {
                cleanWs()
            }
        }
    }
}
