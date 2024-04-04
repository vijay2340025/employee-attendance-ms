data "aws_vpc" "default" {
  default = true
}

data "aws_subnet" "az_1" {
  availability_zone = var.az_1
}

data "aws_subnet" "az_2" {
  availability_zone = var.az_2
}