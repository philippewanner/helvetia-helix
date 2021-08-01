# Install (macOS)
`brew install --cask docker`

# Run
Compile source
`mvn clean package`

Build docker image
`docker build -t helix .`

Run docker image
`docker run -p8081:8081 -it helix`

Swagger is accessible under: `http://localhost:8081/swagger-ui/index.html`
