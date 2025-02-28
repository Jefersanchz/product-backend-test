pipeline {
    agent {
        docker {
            image 'docker:latest'
            args '--privileged -v /var/run/docker.sock:/var/run/docker.sock'
            user 'root'
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

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t product-backend-test-1 .'
            }
        }

        stage('Remove Existing Container') {
            steps {
                script {
                    sh '''
                    if [ "$(docker ps -aq -f name=product-backend-test)" ]; then
                        docker rm -f product-backend-test
                    fi
                    '''
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                sh '''
                docker run -d -p 9000:9000 --name product-backend-test \
                --network=host \
                -e SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL \
                -e SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME \
                -e SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD \
                product-backend-test-1
                '''
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
