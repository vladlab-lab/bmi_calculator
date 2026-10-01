plugins {
    // Замість alias використовуємо id, щоб точно працювало без файлу toml
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.laba6"
    compileSdk = 34 // Використовуємо стабільну версію (36 - це ще прев'ю)

    defaultConfig {
        applicationId = "com.example.laba6"
        minSdk = 26 // Змінив з 36 на 24, щоб працювало на більшості телефонів
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17 // Для XML проектів зазвичай вистачає Java 8
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    // ВАЖЛИВО: Вимикаємо Compose і вмикаємо ViewBinding (опціонально)
    buildFeatures {
        compose = false
        viewBinding = true
    }
}

dependencies {
    // --- ОСНОВНІ БІБЛІОТЕКИ ДЛЯ XML (КЛАСИЧНИЙ ANDROID) ---

    // Core
    implementation("androidx.core:core-ktx:1.12.0")

    // Appcompat - потрібен для AppCompatActivity
    implementation("androidx.appcompat:appcompat:1.6.1")

    // Material Design - потрібен для кольорів та кнопок (Theme.MaterialComponents)
    implementation("com.google.android.material:material:1.11.0")

    // Layouts
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // --- ТЕСТУВАННЯ (БЕЗ COMPOSE) ---
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}