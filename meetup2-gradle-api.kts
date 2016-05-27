/**
 * Gradle APIs.
 */

import org.gradle.script.lang.kotlin.*

import org.gradle.api.Action
import org.gradle.api.artifacts.ComponentSelection
import org.gradle.api.file.DuplicatesStrategy

/**
 * Gradle provided extensions.
 */

// Extension functions on the Gradle Project shadow methods where closure based configuration has traditionally been used

// Required because they don't expose an Action<*> or similar SAM interface, instead only a getter and a Groovy closure based configuration

repositories {
    jcenter()
}

/**
 * Actions.
 *
 * Action based signatures work, however they're not as clean as working with the extensions or closures in Groovy.
 *
 * I'd expect that the Gradle folks will add methods for any that accept groovy.lang.Closure to also match a lambda type signature.
 */

// 1. Slightly clumsy, and problematic with nested closures

copy {
    it.from(files("build.gradle.kts"))
    it.duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

// 2. Better, but for repeated calls a little messy

copy { spec ->
    spec.from(files("build.gradle.kts"))
    spec.duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

// 3. Better still, but nesting is messy too

copy {
    it.run {
        from(files("build.gradle.kts"))
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}

// 4. Best right now. It's not possible to perform this conversion implicitly, but we can do this generically with:

fun <U> action(exec: U.() -> Unit) = Action<U> { exec(it) }

copy(action {
    from(files("build.gradle.kts"))
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
})

/**
 * Issues with Object overloads
 */

// Methods in the DSL that have Object overloads are problematic. Kotlin selects the least specific signature (https://youtrack.jetbrains.com/issue/KT-11128)

val resolutionStrategy = configurations.getByName("compile").resolutionStrategy
val componentSelection = resolutionStrategy.componentSelection

// TODO uncomment to demonstrate
//componentSelection.all { action ->
//
//}

// Hinting that the generic type doesn't help, this still selects ComponentSelectionRules.all(Object) and will fail at runtime

componentSelection.all { action: ComponentSelection ->
    println(action)
}

// So instead, you have to be a little more heavy handed and implement the Action

componentSelection.all(Action<ComponentSelection> { selection ->
    println(selection.candidate.version)
})
