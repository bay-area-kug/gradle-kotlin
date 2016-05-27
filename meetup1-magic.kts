/**
 * How it works.
 */

// 1. gradle.ktscfg.xml configures a super-type for kts scripts, and a super-class parameter to be passed

// 2. The super-type delegates to the project, thus:

group = "com.netflix"
version = "1.0"

// 3. Currently, the IntelliJ project is manually patched to add a gradle-ide-support module so code completion works

// 4. Imports are also required to get both the extension functions and the classes to configure against

