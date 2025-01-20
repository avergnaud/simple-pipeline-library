# simple-pipeline-library

## problématique de l'IDE

Exemple de VSCode : installer l'extension pour Java.

![VSCode extension](./docs/IDE_support_example.png?raw=true)

## étape 1 : vérifier la syntaxe groovy à la compilation

[https://docs.groovy-lang.org/latest/html/documentation/tools-groovyc.html#_maven_integration](https://docs.groovy-lang.org/latest/html/documentation/tools-groovyc.html#_maven_integration)

[https://groovy.github.io/GMavenPlus/index.html](https://groovy.github.io/GMavenPlus/index.html)

[https://mvnrepository.com/artifact/org.codehaus.gmavenplus/gmavenplus-plugin](https://mvnrepository.com/artifact/org.codehaus.gmavenplus/gmavenplus-plugin)

--> Dernière version : 4.1.1

[http://groovy.github.io/GMavenPlus/compile-mojo.html#targetBytecode](http://groovy.github.io/GMavenPlus/compile-mojo.html#targetBytecode)

--> Compatibilité GMavenPlus, Groovy, Java.

```
<targetBytecode>23</targetBytecode>
```

[http://groovy.github.io/GMavenPlus/compile-mojo.html#configScript](http://groovy.github.io/GMavenPlus/compile-mojo.html#configScript)

[http://groovy-lang.org/dsls.html#compilation-customizers](http://groovy-lang.org/dsls.html#compilation-customizers)

Démo : [démo groovy compile](./docs/1_groovy_compile.webm?raw=true)

## étape 2 : compiler avec les API Jenkins

Important : CPS [https://www.jenkins.io/doc/book/pipeline/cps-method-mismatches/](https://www.jenkins.io/doc/book/pipeline/cps-method-mismatches/)

[https://www.jenkins.io/doc/book/pipeline/shared-libraries/](https://www.jenkins.io/doc/book/pipeline/shared-libraries/)

[https://javadoc.jenkins.io/](https://javadoc.jenkins.io/)

[https://hub.docker.com/_/jenkins](https://hub.docker.com/_/jenkins)

podman container run -p 8080:8080 -p 50000:50000 -v ${PWD}/jenkins_local:/var/jenkins_home jenkins/jenkins:lts

sudo docker run -p 8080:8080 -p 50000:50000 --restart=on-failure -v $(pwd)/local_jenkins:/var/jenkins_home jenkins/jenkins:lts

![install jenkins step 1](./docs/install_jenkins_step1.png?raw=true)

sudo docker container exec -it 54d0f8ef5748 /bin/bash

Lors de l'install, on accepte les "suggested plugins"

![install jenkins step 2](./docs/install_jenkins_step2.png?raw=true)

