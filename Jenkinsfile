pipeline {
	agent any

	environment {
		// Définit le chemin du wrapper Gradle (facultatif si déjà exécutable)
		GRADLE_CMD = isUnix() ? './gradlew' : 'gradlew.bat'
	}

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
						sh "${GRADLE_CMD} build -x test"
					} else {
						bat "${GRADLE_CMD} build -x test"
					}
				}
			}
		}

		stage('Test') {
			steps {
				script {
					if (isUnix()) {
						sh "${GRADLE_CMD} test"
					} else {
						bat "${GRADLE_CMD} test"
					}
				}
			}
		}

		stage('Package') {
			steps {
				script {
					if (isUnix()) {
						sh "${GRADLE_CMD} bootJar"
					} else {
						bat "${GRADLE_CMD} bootJar"
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
			echo '✅ Build, tests et packaging terminés avec succès !'
		}
		failure {
			echo '❌ Build échoué, vérifie le log pour les erreurs.'
		}
	}
}
