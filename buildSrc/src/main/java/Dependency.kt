object Dependency {
    object GradlePlugins {
        const val ANDROID_APPLICATION = "com.android.application"
        const val ANDROID_LIBRARY = "com.android.library"
        const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    }

    object AndroidX {
        const val KOTLIN_CORE = "androidx.core:core-ktx:${Versions.Kotlin}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.Appcompat}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.Constraint}"
        const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Versions.ActivityKtx}"
        const val SPLASH_CORE = "androidx.core:core-splashscreen:${Versions.Splash}"
    }

    object Compose {
        const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Versions.ActivityCompose}"
        const val COMPOSE = "androidx.compose.ui:ui:${Versions.Compose}"
        const val COMPOSE_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${Versions.Compose}"
        const val COMPOSE_MATERIAL = "androidx.compose.material:material:${Versions.Compose}"
        const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling:${Versions.Compose}"
        const val COMPOSE_MANIFEST = "androidx.compose.ui:ui-test-manifest:${Versions.Compose}"
        const val COMPOSE_LIVEDATA = "androidx.compose.runtime:runtime-livedata:${Versions.Compose}"
        const val COMPOSE_NAV = "androidx.navigation:navigation-compose:${Versions.ComposeNav}"
        const val COMPOSE_ICON = "androidx.compose.material:material-icons-extended:${Versions.Compose}"
        const val COMPOSE_PAGER = "com.google.accompanist:accompanist-pager:${Versions.ComposePager}"
        const val PAGER_INDICATORS = "com.google.accompanist:accompanist-pager-indicators:${Versions.ComposePager}"
    }

    object ComposeTest {
        const val COMPOSE_JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.Compose}"
    }

    object Libraries {
        const val GAUTH =  "com.github.GSM-MSG:GAuth-Signin-Android:${Versions.Gauth}"
    }

    object Hilt {
        const val HILT_ANDROID_GRADLE = "com.google.dagger:hilt-android-gradle-plugin:${Versions.Hilt}"
        const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.Hilt}"
        const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.Hilt}"
    }

    object Retrofit {
        const val RETROFIT_KT = "com.squareup.retrofit2:retrofit:${Versions.Retrofit}"
        const val RETROFIT_GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.Gson}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.Okhttp}"
        const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.Okhttp}"
    }

    object LifeCycle {
        const val LIFECYCLE = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Lifecycle}"
        const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LifecycleRuntime}"
        const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Lifecycle}"
    }

    object Google {
        const val MATERIAL = "com.google.android.material:material:${Versions.Material}"
    }

    object Test {
        const val JUNIT = "junit:junit:${Versions.Junit}"
    }

    object AndroidTest {
        const val TEST_JUNIT = "androidx.test.ext:junit:${Versions.TestJunit}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.Espresso}"
    }

    object Coil {
        const val COIL = "com.github.skydoves:landscapist-coil:${Versions.Coil}"
    }
}