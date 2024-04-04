resource "aws_security_group" "allow_service_port" {
  name        = "allow_service_port"
  description = "Allow 30001-30005 inbound traffic and all outbound traffic"
  vpc_id      = data.aws_vpc.default.id

  tags = {
    Name = "allow_30001_to_30005"
  }
}

data "aws_vpc" "default" {
  default = true
}

resource "aws_vpc_security_group_ingress_rule" "allow_service_port_ipv4" {
  security_group_id = aws_security_group.allow_service_port.id
  cidr_ipv4         = "0.0.0.0/0"

  from_port         = 30001
  ip_protocol       = "tcp"
  to_port           = 30005
}

output "vpc_id" {
  value = data.aws_vpc.default.id
}