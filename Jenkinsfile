pipeline {
	agent any

	stages {
		stage('Checkout') {
			steps {
				checkout scm
			}
		}

		stage('Build') {
			steps {
				script {
					if (isUnix()) {
						sh './gradlew build -x test'
					} else {
						bat 'gradlew.bat build -x test'
					}
				}
			}
		}

		stage('Test') {
			steps {
				script {
					if (isUnix()) {
						sh './gradlew test'
					} else {
						bat 'gradlew.bat test'
					}
				}
			}
		}

		stage('Package') {
			steps {
				script {
					if (isUnix()) {
						sh './gradlew bootJar'
					} else {
						bat 'gradlew.bat bootJar'
					}
				}
			}
		}

		stage('Archive Artifact') {
			steps {
				archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
			}
		}
	}

	post {
		success {
			echo '✅ Build et packaging terminés !'
		}
		failure {
			echo '❌ Build échoué.'
		}
	}
}
