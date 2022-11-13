pipeline {
    agent any
 

    
    stages{
        stage("git pull"){
            steps{
              
                git branch: 'oussemasb', 
                credentialsId: '80545ef7-d86b-4f53-b772-91adbe1617f8', 
                url: 'https://github.com/asma-abessi/achat-devops.git'
                    
                }
                
            }
            
        stage('MVN COMPILE') {
                steps {
                sh 'mvn clean compile'
                    
                }
                
            }
        stage('clean'){
                steps{
                sh 'mvn clean package'
                    
                }
                
            }  
        stage('MVN TEST') {
                steps {
                sh 'mvn clean test'
                    
                }
                
            }  
        stage('build'){
            steps{
                sh 'mvn install package'
            }
         }
         
    
    
	    

	}

}
