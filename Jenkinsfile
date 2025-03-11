pipeline {
    environment {
        registry = "shurand/jenkins-test"
        registryCredential = 'dockerhub'
        dockerImage = ''
    }
    agent any
    triggers {
        pollSCM '*/2 * * * *'
    }
    stages {
        stage('Build') {
            steps {
                echo "Building.."
                sh '''
                chmod +x gradlew
                ./gradlew build
                '''
            }
        }
        stage('Test') {
            steps {
                echo "Testing.."
            }
        }
        stage('Building our image') {
            steps{
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }
        stage('Ansible start container') {
                    steps{
                        sh ''' ansible-playbook start-container.yml '''
                    }
                }
//         stage('Deploy our image') {
//             steps{
//                 script {
//                     docker.withRegistry( '', registryCredential ) {
//                         dockerImage.push()
//                     }
//                 }
//             }
//         }
//         stage('Cleaning up') {
//             steps{
//                 sh "docker rmi $registry:$BUILD_NUMBER"
//             }
//         }
    }
    post {
            always {
                archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
            }
    }
}