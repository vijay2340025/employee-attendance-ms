pipeline {
    agent any

    stages {
        stage('init') {
            when {
                expression { params.initialize == true }
            }
            steps {
                sh "sed -i '2s/.*/  default=\"$params.access_key\"/'  pipeline/infra/variables.tf"
                sh "sed -i '6s/.*/  default=\"$params.secret_key\"/'  pipeline/infra/variables.tf"
                sh 'cat pipeline/infra/variables.tf'
                sh 'cd pipeline/infra/ && terraform init'
            }
        }

        stage('plan') {
            when {
                expression { params.state == "start" }
            }
            steps {
                sh 'cd pipeline/infra/ && terraform plan -var-file=dev.tfvars'
            }
        }

        stage('apply') {
            when {
                expression { params.state == "start" }
            }
            steps {
                sh 'cd pipeline/infra/ && terraform apply -auto-approve -var-file=dev.tfvars'
            }
        }

        stage('destroy') {
            when {
                expression { params.state == "stop" }
            }
            steps {
                sh 'cd pipeline/infra/ && terraform destroy -auto-approve -var-file=dev.tfvars'
            }
        }

        stage('clean') {
            when {
                expression { params.state == "stop" }
            }
            steps {
                cleanWs()
            }
        }
    }
}
