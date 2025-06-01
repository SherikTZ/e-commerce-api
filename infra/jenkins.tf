resource "aws_instance" "jenkins-instance" {
    ami = "ami-0953476d60561c955"
    instance_type = "t2.micro"
    key_name = aws_key_pair.generated_key.key_name

    tags = {
        Name = "Jenkins-Server"
    }

    vpc_security_group_ids = [aws_security_group.app_access_sg.id]

    user_data = file("scripts/jenkins/installJenkins.sh")
}