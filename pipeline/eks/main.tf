provider "aws" {
  region     = var.region
}

resource "aws_eks_cluster" "demo" {
  name     = "demo"
  role_arn = aws_iam_role.eks-cluster-role.arn

  vpc_config {
    subnet_ids = [
      data.aws_subnet.az_1.id,
      data.aws_subnet.az_2.id
    ]
  }
  depends_on = [aws_iam_role.eks-cluster-role]
}

resource "aws_eks_node_group" "worker-nodes" {
  cluster_name    = aws_eks_cluster.demo.name
  node_group_name = "worker-nodes"
  node_role_arn   = aws_iam_role.eks-nodegrp-role.arn

  subnet_ids = [
    data.aws_subnet.az_1.id,
    data.aws_subnet.az_2.id
  ]

  capacity_type  = "ON_DEMAND"
  instance_types = [var.instance_type]

  scaling_config {
    desired_size = var.desired_size
    max_size     = var.max_size
    min_size     = var.min_size
  }

  labels = {
    node = "kubenode"
  }

  depends_on = [
    aws_iam_role.eks-nodegrp-role
  ]
}