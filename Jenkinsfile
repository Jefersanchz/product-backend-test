pipeline {
    agent {
        docker {
            image 'eclipse-temurin:17.0.12_7-jdk'
            args '-v /tmp/maven:/root/.m2 -e MAVEN_CONFIG=/root/.m2'
        }
    }

    environment {
        SPRING_DATASOURCE_URL = "jdbc:mysql://mysql-db:3307/producto"
        SPRING_DATASOURCE_USERNAME = "root"
        SPRING_DATASOURCE_PASSWORD = "mypassword"
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Jefersanchz/product-backend-test.git', branch: 'main'
            }
        }

        stage('Set Permissions') {
            steps {
                sh 'chmod +x mvnw'
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean install'
            }
        }

        stage('Run Tests') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t product-backend-test .'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'docker run -d -p 9000:9000 --name product-backend-test -e SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL -e SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME -e SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD product-backend-test'
            }
        }
    }

    post {
        success {
            echo 'Build and deployment completed successfully!'
        }
        failure {
            echo 'Build failed.'
        }
    }
}