pipeline {
    agent any

    stages {
        stage('checkout') {
            steps {
                git branch: 'main', credentialsId: 'da6f6042-ce09-4a44-9145-357085d53474', url: 'https://github.com/vijay2340025/employee-attendance-ms.git'
            }
        }
    }
}
