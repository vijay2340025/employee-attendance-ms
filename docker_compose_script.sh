docker rmi vijay2340025/api-gateway
docker rmi vijay2340025/access-control-service
docker rmi vijay2340025/report-service
docker rmi vijay2340025/access-control-service
docker rmi vijay2340025/jwt

docker compose -f docker-compose.yaml build

docker push vijay2340025/api-gateway
docker push vijay2340025/access-control-service
docker push vijay2340025/report-service
docker push vijay2340025/access-control-service
docker push vijay2340025/jwt