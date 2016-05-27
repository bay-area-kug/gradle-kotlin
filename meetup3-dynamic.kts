/**
 * No more dynamic language features.
 */

import org.gradle.api.plugins.ApplicationPluginConvention
import org.gradle.script.lang.kotlin.configure
import org.gradle.script.lang.kotlin.dependencies

/**
 * One of the largest challenges is going to be DomainObjectCollection and other dynamic objects/mixins
 * Largely Groovy missing method based, ASM generated _Decorated classes
 * That means, no more dynamic properties on objects.
 */

// That means, no more dynamic properties on objects. For example, configuration containers

configurations.getByName("compile")

// Or dynamic configuration names on dependency handlers. Currently implemented as operator extension functions on String

dependencies {
    "testCompile"("junit:junit:4.12")
}


// Most obvious side-effect is to extensions/conventions, must configure explicitly and be aware of type name

configure<ApplicationPluginConvention> {
    applicationName = "myapp"
}
