pipeline {
    agent any

    stages {
        stage('init') {
            steps {
                sh "sed -i '2s/.*/  default=\"$params.access_key\"/'  pipeline/infra/variables.tf"
                sh "sed -i '6s/.*/  default=\"$params.secret_key\"/'  pipeline/infra/variables.tf"
                sh 'cat pipeline/infra/variables.tf'
                sh 'cd pipeline/infra/ && terraform init'
            }
        }

        stage('plan') {
            steps {
                sh 'cd pipeline/infra/ && terraform plan -var-file=dev.tfvars'
            }
        }

        stage('apply') {
            steps {
                sh 'cd pipeline/infra/ && terraform apply -auto-approve -var-file=dev.tfvars'
            }
        }

        stage('clean') {
            steps {
                cleanWs()
            }
        }
    }
}
