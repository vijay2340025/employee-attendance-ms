provider "aws" {
  region     = var.region
  access_key = var.access_key
  secret_key = var.secret_key
}

resource "aws_eks_cluster" "demo" {
  name     = "demo"
  role_arn = aws_iam_role.eks-cluster-role.arn

  vpc_config {
    subnet_ids = [
      // add id's of subnets
    ]
  }
  depends_on = [aws_iam_role.eks-cluster-role]
}

resource "aws_eks_node_group" "worker-nodes" {
  cluster_name    = aws_eks_cluster.demo.name
  node_group_name = "worker-nodes"
  node_role_arn   = aws_iam_role.eks-nodegrp-role.arn

  subnet_ids = [
    // add id's of subnets
  ]

  capacity_type  = "ON_DEMAND"
  instance_types = ["t3.medium"]

  scaling_config {
    desired_size = 1
    max_size     = 1
    min_size     = 1
  }

  labels = {
    node = "kubenode"
  }

  depends_on = [
    aws_iam_role.eks-nodegrp-role
  ]
}