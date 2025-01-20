import jenkins.model.Jenkins

static String hello() {
   /* Exemple d'appel API Jenkins */

   /* https://javadoc.jenkins-ci.org/jenkins/model/Jenkins.html */
   int numberOfBuilds = Jenkins.instance
      /* https://javadoc.jenkins-ci.org/hudson/model/View.html */
      .getView('All')
      /* https://javadoc.jenkins-ci.org/hudson/util/RunList.html */
      .getBuilds()
      .size();

   return 'Hello World. ' +  numberOfBuilds + ' builds'
}

static int add(int i, int j) {
   return i + j;
}