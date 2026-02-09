pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                echo 'Code checkout from GitHub'
            }
        }

        stage('Compile') {
            steps {
                bat '''
                echo public class HelloCI { > HelloCI.java
                echo public static void main(String[] args) { >> HelloCI.java
                echo System.out.println("Mini CI Project Success"); >> HelloCI.java
                echo } >> HelloCI.java
                javac HelloCI.java
                '''
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: '*.class', fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'CI PIPELINE SUCCESS'
        }
        failure {
            echo 'CI PIPELINE FAILED'
        }
    }
}
