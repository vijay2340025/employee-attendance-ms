provider "aws" {
  region     = "ap-south-1"
  access_key = ""
  secret_key = ""
}

resource "aws_instance" "this" {
  ami                     = "ami-007020fd9c84e18c7"
  instance_type           = "t2.micro"
  
  tags = {
    Name = "mini-server"
  }
}
