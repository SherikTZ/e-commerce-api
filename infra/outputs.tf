output "product_1_instance_id" {
  description = "ID of the EC2 instance"
  value       = aws_instance.product-instance-1.id
}

output "product_1_public_ip" {
  description = "Public IP address of the EC2 instance"
  value       = aws_instance.product-instance-1.public_ip
}

output "product_1_used" {
  description = "AMI ID used for the instance"
  value       = aws_instance.product-instance-1.ami
}

output "product_1_type_used" {
  description = "Instance type used"
  value       = aws_instance.product-instance-1.instance_type
}

output "jenkins_instance_id" {
  description = "ID of the Jenkins EC2 instance"
  value       = aws_instance.jenkins-instance.id
}

output "jenkins_public_ip" {
  description = "Public IP address of the Jenkins EC2 instance"
  value       = aws_instance.jenkins-instance.public_ip
}

output "jenkins_used" {
  description = "AMI ID used for the Jenkins instance"
  value       = aws_instance.jenkins-instance.ami
}

output "jenkins_type_used" {
  description = "Instance type used for the Jenkins instance"
  value       = aws_instance.jenkins-instance.instance_type
}