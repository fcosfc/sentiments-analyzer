# Sentiments analyzer

This is a proof of concept for an application that analyze sentiments in a paragraph.

Natural Language Processing open source libraries provided by Stanford CoreNLP (https://stanfordnlp.github.io/CoreNLP/)

## Deploying using Docker

To build the Dockerized version of the project, run

```
docker build . -t sentimentsanalyzer:latest
```

Once the Docker image is correctly built, you can test it locally using

```
docker run -p 8080:8080 sentimentsanalyzer:latest
```
