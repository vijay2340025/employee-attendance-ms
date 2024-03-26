pipeline {
    agent any

    stages {
        stage('checkout') {
            steps {
                git branch: 'main', credentialsId: 'da6f6042-ce09-4a44-9145-357085d53474', url: 'https://github.com/vijay2340025/employee-attendance-ms.git'
            }
        }

        stage('init') {
            steps {
                sh "sed -i '3s/.*/  access_key=\"$param.access_key\"/' ec2_instance.tf\n"
                sh "sed -i '4s/.*/  secret_key=\"$param.secret_key\"/' ec2_instance.tf\n"
                sh 'cat pipeline/infra/ec2_instance.tf'
                sh 'cd pipeline/infra/ && terraform init'
            }
        }

        stage('plan') {
            steps {
                sh 'cd pipeline/infra/ && terraform plan'
            }
        }

        stage('apply') {
            steps {
                sh 'cd pipeline/infra/ && terraform apply -auto-approve'
            }
        }

        stage('clean') {
            steps {
                cleanWs()
            }
        }
    }
}
