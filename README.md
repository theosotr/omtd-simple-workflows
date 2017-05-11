# omtd-simple-workflows

   
**Step 1**: Clone omtd-simple-workflows by typing
  
```
git clone <repoURL>
```


**Step 2**: CD to omtd-simple-workflows directory that has been created. For building the projects type

```
mvn clean install 
```

**Step 3**: Create docker image

```
./omtd-simple-workflows-createDockerImg.sh 
```

**Step 4**: Use the following commands to  a) create a container from the image produced by the previous step b) start a container c) get a bash shell inside the container.

```
sudo docker create --name omtd-simple-workflows -t omtd-simple-workflows-docker
sudo docker start omtd-simple-workflows
sudo docker exec -i -t omtd-simple-workflows  /bin/bash
```

**Step 5**: Examples

```
# PDF2XMI with DKPRo PdfReader.
./Linux_runDKPro_PDF2XMI_example.sh 
```