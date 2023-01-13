mvn verify
podman build -t quay.io/mdiscepo/my-app .
podman push quay.io/mdiscepo/my-app
oc apply -f config-map.yaml
oc apply -f deployment.yaml

