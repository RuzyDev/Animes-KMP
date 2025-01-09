############################
# Regras gerais do Kotlin #
############################

# Mantenha as classes do Kotlin padrão
-keep class kotlin.** { *; }
-dontwarn kotlin.**

# Retém informações sobre anotações no código (necessário para bibliotecas como Ktor e SQLDelight)
-keepattributes RuntimeVisibleAnnotations

# Classes relacionadas ao Kotlin Multiplatform
-keep class kotlinx.** { *; }
-dontwarn kotlinx.**

# Serialização do Kotlin (se estiver usando kotlinx.serialization)
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class ** {
    @kotlinx.serialization.* <fields>;
}

##################################
# Regras para o Ktor (Client) #
##################################

# Retém as classes necessárias para o cliente Ktor funcionar
-keep class io.ktor.** { *; }
-dontwarn io.ktor.**

# Permite o uso de anotações e métodos específicos usados no Ktor
-keep class io.ktor.client.plugins.** { *; }
-keep class io.ktor.utils.io.** { *; }
-keep class io.ktor.http.** { *; }

# Retém as classes usadas para log e depuração (se aplicável)
-keepattributes Signature
-keepattributes Exceptions
-keepattributes InnerClasses

# Garante que os pipelines do Ktor não sejam removidos
-keep class io.ktor.client.engine.** { *; }
-keepclassmembers class io.ktor.client.engine.** { *; }

###################################
# Regras para SQLDelight #
###################################

# Retém classes do SQLDelight e seus geradores
-keep class com.squareup.sqldelight.** { *; }
-dontwarn com.squareup.sqldelight.**

# Retém o código gerado pelo SQLDelight
-keepnames class **Database
-keepnames class **.Queries
-keep class **.sq.** { *; }

######################################
# Regras opcionais para outras libs #
######################################

# Firebase (Crashlytics, Analytics, etc.)
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**

# Coroutines (necessário para Ktor e Kotlin Multiplatform)
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**

######################################
# Regras de segurança e otimização #
######################################

# Evita remoção de classes serializáveis
-keepnames class **Kt

# Preserva a classe principal do aplicativo Android
-keep class br.com.arcom.apparcom.android.MainActivity { *; }

# Desativa avisos irrelevantes
-dontwarn org.jetbrains.annotations.**
-dontwarn javax.annotation.**

# Suprime avisos sobre classes ausentes no SLF4J
-dontwarn org.slf4j.impl.StaticLoggerBinder
-dontwarn org.slf4j.impl.StaticMDCBinder