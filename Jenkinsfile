pipeline {
    agent any
    environment {
        dotnet = 'C:\\Program Files\\dotnet\\dotnet.exe'
    }
    stages {
         stage('Unit Testing') {
            steps {
                echo 'Unit Testing...'
                echo "Current workspace is ${WORKSPACE}"
                bat "dotnet test ${WORKSPACE}\\CertificationList\\CertificationList.sln"
            }
        }
        stage('Code Review') {
            steps {
                echo 'Code Review using Sonarqube'
                script {
                    def scannerHome = tool 'Sonar';
                    withSonarQubeEnv("Sonar") {
                    bat "${tool("Sonar")}/bin/sonar-scanner \
                    -Dsonar.projectKey=CertificationList \
                    -Dsonar.sources=. \
                    -Dsonar.css.node=. \
                    -Dsonar.exclusions=**/*.java,**/*.js,target/**/* \
                    -Dsonar.host.url=http://localhost:9000"
                        }
                    }
            }
        }
        stage('Build Stage') {
            steps {
                echo 'Build Stage...'
                bat "dotnet clean ${WORKSPACE}\\CertificationList\\CertificationList.sln"
                bat "dotnet build ${WORKSPACE}\\CertificationList\\CertificationList.sln"
            }
        }
        stage('Code Deploy') {
            steps {
                //Deploy application on IIS
                echo 'Code Deployment'
                bat 'iisreset /stop'
                bat "dotnet publish ${WORKSPACE}\\CertificationList\\CertificationList.sln -o C:\\inetpub\\wwwroot\\certifications"
                bat 'iisreset'
            }
        }
        stage('Automation Test') {
            steps {
                bat 'dir'
                bat 'mvn clean'
                bat 'mvn test'
            }
        }
    }
}