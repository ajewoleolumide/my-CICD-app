def get_print_welcome(name){
    println("Welcome to the team $name")
}

pipeline{
    agent any
    stages{
        stage('This is the first stage'){
           steps{
               script{
                   def name = 'Olumide'
                   println(name)
               }
            } 
        }
        stage('This is the second stage'){
            steps{
                script{
                    get_print_welcome('Mide')
                }
            }
        }
    }
    post{
        always{
            println('This job is completed')
        }
    }
}