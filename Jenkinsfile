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
        
        stage ('compile') {
		   steps {
		     withMaven(maven: 'M2_HOME'){
		         sh 'mvn -version'
		         sh 'mvn compile'}
		      }
		}
		
		stage('verify package'){
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        
        stage('test unitaire'){
            steps {
                sh 'mvn test'
            }
	    }
        
        stage("sonarqube"){
        steps {
                withSonarQubeEnv ( installationName: 'sonarqube-8.9.7'){
                sh 'mvn sonar:sonar'
                }
        	}
        }
        
        stage("Publish to Nexus Repository Manager") {
            steps {
                 nexusArtifactUploader artifacts: [
                     [
                         artifactId: 'achat',
                         classifier: '',
                         file: 'target/achat-1.0.jar',
                         type: 'jar']], 
	                     credentialsId: 'Nexus_CD',
	                     groupId: 'tn.esprit.rh', 
	                     nexusUrl: '192.168.100.40:8081', 
	                     nexusVersion: 'nexus3', 
	                     protocol: 'http',
	                     repository: 'SpringAchat',
	                     version: '1.0'
            	}
            }
            
            stage('Build docker image'){
				 steps{
				 script{
				 sh 'docker build -t youssefboulahia/springprojet .'
				 }
				 }
				 }
				
				 stage('Docker login') {
				
				 steps {
					 sh 'echo "login Docker ...."'
					sh 'docker login -u youssefboulahia -p youssef98'
					  }  }
					 stage('Docker push') {
					 steps {
					 sh 'echo "Docker is pushing ...."'
					sh 'docker push youssefboulahia/springprojet'
				 }  }
        
} }