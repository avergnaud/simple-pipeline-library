import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

withConfig(configuration)  {
    ast(CompileStatic, value: TypeCheckingMode.SKIP)
}