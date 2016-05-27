/**
 * Side-note, applyFrom doesn't currently work.
 */

import org.gradle.script.lang.kotlin.*

applyFrom("meetup3-dynamic.kts")

// Each kts script is treated the same way, causing:

val exception = """
Caused by: org.gradle.api.InvalidUserDataException: Cannot add task ':generateKtsConfig' as a task with that name already exists.
        at org.gradle.api.internal.tasks.DefaultTaskContainer.create(DefaultTaskContainer.java:74)
        at org.gradle.api.internal.tasks.DefaultTaskContainer.create(DefaultTaskContainer.java:97)
        at org.gradle.script.lang.kotlin.provider.KotlinScriptPlugin.registerBuiltinTasks(KotlinScriptPlugin.kt:59)
        at org.gradle.script.lang.kotlin.provider.KotlinScriptPlugin.apply(KotlinScriptPlugin.kt:41)
        at org.gradle.api.internal.plugins.DefaultObjectConfigurationAction.applyScript(DefaultObjectConfigurationAction.java:102)
        at org.gradle.api.internal.plugins.DefaultObjectConfigurationAction.access$000(DefaultObjectConfigurationAction.java:36)
        at org.gradle.api.internal.plugins.DefaultObjectConfigurationAction$1.run(DefaultObjectConfigurationAction.java:62)
        at org.gradle.api.internal.plugins.DefaultObjectConfigurationAction.execute(DefaultObjectConfigurationAction.java:136)
        at org.gradle.api.internal.project.AbstractPluginAware.apply(AbstractPluginAware.java:40)
        at org.gradle.script.lang.kotlin.KotlinBuildScript.apply(KotlinBuildScript.kt)
        at org.gradle.script.lang.kotlin.ProjectExtensionsKt.applyFrom(ProjectExtensions.kt:50)
        at Build_gradle.<init>(build.gradle.kts:18)
        at org.gradle.script.lang.kotlin.provider.KotlinScriptPlugin.instantiateScriptClass(KotlinScriptPlugin.kt:52)
        at org.gradle.script.lang.kotlin.provider.KotlinScriptPlugin.apply(KotlinScriptPlugin.kt:42)
"""
