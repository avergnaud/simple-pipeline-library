# simple-pipeline-library

## Besoin

Développer une [Jenkins shared library](https://www.jenkins.io/doc/book/pipeline/shared-libraries/) :

 * Compiler en local (syntaxe groovy, imports de jenkins-core ou de plugins Jenkins)
 * Tester en local (JUnit)

## Problématique de l'IDE

On n'explique pas comment ajouter le support pour Groovy dans tel ou tel IDE (VSCode, IntelliJ...)

## Solution

### Démos

 * Compiler en local, syntaxe Groovy : [démo 1](./docs/demo1_compile.mp4)
 * Compiler en local, import depuis jenkins-core : [démo 2](./docs/demo2_compile.mp4)
 * Tester en local (TU) : [démo 3](./docs/demo3_TU.mp4)
 * Build d'un [projet](https://github.com/avergnaud/nodejs-hello-world) depuis un jenkins local, en appelant ce pipeline library : [démo 4](./docs/demo4_build_jenkins_local.mp4)

## Détails de la solution

Pour compiler le groovy on a besoin de :

 * Fichier pom.xml
```
<configScript>${project.basedir}/groovy-compiler-config.groovy</configScript>
```
 * Fichier groovy-compiler-config.groovy

Pour tirer la dépendance à jenkins-core on a besoin de :
 * Fichier pom.xml
```
<dependency>
    <groupId>org.jenkins-ci.main</groupId>
    <artifactId>jenkins-core</artifactId>
    <version>2.479.3</version>
</dependency>
```
(la version doit correspondre à celle du runtime Jenkins)
 * Fichier settings.xml. 
 
 Attention : le fichier settings.xml ne doit pas a priori être placé à la racine du repo. C'est le cas ici pour faciliter la démo.

 Build local, exécuter :
 ```
 mvn clean package --settings ./settings.xml
 ```

TU local, exécuter :
 ```
 mvn clean test --settings ./settings.xml
 ```

---

### Détails de la démo

### étape 1 : vérifier la syntaxe groovy à la compilation

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

### étape 2 : compiler avec les API Jenkins

Important : CPS [https://www.jenkins.io/doc/book/pipeline/cps-method-mismatches/](https://www.jenkins.io/doc/book/pipeline/cps-method-mismatches/)

[https://www.jenkins.io/doc/book/pipeline/shared-libraries/](https://www.jenkins.io/doc/book/pipeline/shared-libraries/)

[https://javadoc.jenkins.io/](https://javadoc.jenkins.io/)

[https://hub.docker.com/_/jenkins](https://hub.docker.com/_/jenkins)

Pour exécuter un Jenkins en local :

```
sudo docker run -p 8080:8080 -p 50000:50000 --restart=on-failure -v $(pwd)/local_jenkins:/var/jenkins_home --dns 1.1.1.1 --dns 8.8.8.8 jenkins/jenkins:lts
```

### install initiale de jenkins

![install jenkins step 1](./docs/install_jenkins_step1.png?raw=true)

sudo docker container exec -it 54d0f8ef5748 /bin/bash

Lors de l'install, on accepte les "suggested plugins"

![install jenkins step 2](./docs/install_jenkins_step2.png?raw=true)

### plugin Node.JS

Ajouter le plugin Node.JS (parce-que notre repo d'exemple build du node)

![install jenkins step 3](./docs/install_jenkins_step3.png?raw=true)

Configurer le plugin

Ensuite [http://localhost:8080/manage/configureTools/](http://localhost:8080/manage/configureTools/)

![install jenkins step 4](./docs/install_jenkins_step4.png?raw=true)

### job qui build l'app node.JS

Ensuite configurer le job

![setup build step 1](./docs/setup_build_step1.png?raw=true)

![setup build step 2](./docs/setup_build_step2.png?raw=true)

### configuration pipeline library

![configure shared library](./docs/configure_shared_library_1.png?raw=true)

### étape 3 TU

[https://www.tutorialspoint.com/groovy/groovy_unit_testing.htm](https://www.tutorialspoint.com/groovy/groovy_unit_testing.htm)