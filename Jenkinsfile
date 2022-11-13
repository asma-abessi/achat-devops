pipeline {
  agent any
  stages {
  stage ('SCM CHECKOUT') {
    steps {
      git branch: 'youssef', url: 'https://github.com/asma-abessi/achat-devops.git' }}
   
        stage ('Initialize') {
             steps {
                 
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                   
                '''
            
        	}
        }
        
        stage ('compile and test ') {
		   steps {
		     withMaven(maven: 'M2_HOME'){
		         sh 'mvn -version'
		         sh 'mvn compile'}
		      }
		}
		
		stage('package'){
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        
        
} }